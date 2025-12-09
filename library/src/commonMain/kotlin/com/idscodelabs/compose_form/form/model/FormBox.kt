package com.idscodelabs.compose_form.form.model

import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.SaverScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import com.idscodelabs.compose_form.form.fields.strings.asDisplayString
import com.idscodelabs.compose_form.utils.mapSync
import com.idscodelabs.compose_form.validators.core.Validator
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

/**
 * Representation of a Form Box
 *
 * @param Model The Model the form outputs
 * @param Value The Value which is stored in this [FormBox]
 * @property enabledFlow A [MutableStateFlow] defining if this box is enabled
 * @property currentValidator The current [Validator] for this [FormBox], can be changed using [setValidator]
 * @property setModelProperty Set the property on [Model]
 * @param valueToString Convert [Value] to a [String]. This will be used in [Validator]s and used for state saving
 * @property valueFlow A flow of the current value
 * @property errorFlow A flow of the current error
 * @property focusRequester A [FocusRequester] for this [FormBox]. Use [primaryFocusable] to specify this element which should gain focus
 * @property mapValue Map the value on set to another [Value], for example, to clean the input
 * @constructor Create empty Form box
 */
open class FormBox<Model, Value>(
    protected val enabledFlow: MutableStateFlow<Boolean>,
    protected var currentValidator: Validator<Value>?,
    val setModelProperty: Model.(String?) -> Unit,
    protected val valueToString: (Value?) -> String?,
    protected val valueFlow: MutableStateFlow<Value>,
    protected val errorFlow: MutableStateFlow<Any?> = MutableStateFlow(null),
    val focusRequester: FocusRequester,
    protected val mapValue: (value: Value) -> Value,
) {
    /**
     * Use this constructor to copy a form box, or when inheriting to avoid needing access to non-visible properties
     */
    constructor(field: FormBox<Model, Value>) : this(
        field.enabledFlow,
        field.currentValidator,
        field.setModelProperty,
        field.valueToString,
        field.valueFlow,
        field.errorFlow,
        field.focusRequester,
        field.mapValue,
    )

    /**
     * Snapshot of the current value.
     *
     * Don't use this in Composable functions due to the risk of unnecessary recomposition, or stale values.
     * Instead, use [value] or [collectValueAsState]
     */
    val valueSnapshot: Value get() = valueFlow.value

    /**
     * State aware representation of the current value. Will trigger recomposition when [value] changes
     *
     * This should be used in the UI to display the current value of the field.
     */
    val value: Value @Composable get() {
        val result by collectValueAsState()
        return result
    }

    /**
     * State aware representation of the current enabled value. Will trigger recomposition when [enabled] changes
     *
     * This should be used in the UI to enable or disable the entry
     */
    val enabled: Boolean @Composable get() {
        val enabled by collectEnabledAsState()
        return enabled
    }

    /**
     * State aware representation of the current error value. Will trigger recomposition when [error] changes.
     * The error will be converted to a [String] using [asDisplayString]
     *
     * This should be used in the UI to display the error to the user
     */
    val error: String? @Composable get() {
        val result by collectErrorAsState()
        return result?.asDisplayString()
    }

    /**
     * Validate this [FormBox]
     *
     * @param s The string representation of the current value of the form box
     * @param otherFieldValues The values of the other fields in the form.
     * @return `true` if the field was valid, `false` otherwise
     * @see [Validator]
     */
    fun validate(): Boolean {
        val error = currentValidator?.validate(valueToString, otherFieldValues)
        setError(error)
        return error == null
    }

    /**
     * Set the error of this [FormBox]
     *
     * @param error The new error to set. [asDisplayString] can be called in the UI to display the error
     */
    open fun setError(error: Any?) {
        errorFlow.update { error }
    }

    /**
     * Set validator for the [FormBox]
     *
     * @param validator The new [Validator], can be `null` to clear the [Validator]
     */
    fun setValidator(validator: Validator?) {
        this.currentValidator = validator
    }


    /**
     * Set the value of this [FormBox]
     *
     * If the value has not changed, or the field is not [enabled], then the value will not be set.
     *
     * This should be used in the UI whenever the user changes the value. It can also be used to override the value
     *
     * @param value The new value
     */
    open fun setValue(value: Value) {
        errorFlow.update { null }
        if (enabledFlow.value) {
            valueFlow.update {
                mapValue(value)
            }
        }
    }

    fun getStringValue() = valueToString(valueFlow.value)

    open fun setEnabled(enabled: Boolean) {
        enabledFlow.update { enabled }
    }

    @Composable
    fun collectValueAsState(): State<Value> = valueFlow.collectAsState()

    @Composable
    fun collectEnabledAsState(): State<Boolean> = enabledFlow.collectAsState()

    @Composable
    fun collectErrorAsState(): State<Any?> = errorFlow.collectAsState()

    @OptIn(FlowPreview::class)
    suspend fun onFieldValueChanged(
        debounceMills: Long = 0,
        block: suspend Value.() -> Unit,
    ) {
        valueFlow.debounce(debounceMills).collectLatest(block)
    }

    @Composable
    fun FieldValueChangedEffect(
        context: CoroutineContext = EmptyCoroutineContext,
        debounceMillis: Long = 0,
        block: suspend Value.() -> Unit,
    ) {
        LaunchedEffect(Unit) {
            if (context == EmptyCoroutineContext) {
                onFieldValueChanged(debounceMillis, block)
            } else {
                withContext(context) { onFieldValueChanged(debounceMillis, block) }
            }
        }
    }

    class Saver<Model, Value>(
        val enabled: Boolean,
        val validator: Validator?,
        val setModelProperty: Model.(String?) -> Unit,
        val valueToString: (Value?) -> String?,
        val stringToValue: (String?) -> Value,
        val focusRequester: FocusRequester,
        val mapValue: (value: Value) -> Value,
    ) : androidx.compose.runtime.saveable.Saver<FormBox<Model, Value>, String> {
        override fun SaverScope.save(value: FormBox<Model, Value>): String = value.getStringValue() ?: ""

        override fun restore(value: String): FormBox<Model, Value> =
            FormBox(
                MutableStateFlow(enabled),
                validator,
                setModelProperty,
                valueToString,
                MutableStateFlow(stringToValue(value)),
                MutableStateFlow(null),
                focusRequester,
                mapValue,
            )
    }

    fun Modifier.primaryFocusable(): Modifier = focusRequester(focusRequester)
}

@Composable
fun <Model, Value> rememberFormBox(
    enabled: Boolean,
    validator: Validator?,
    setModelProperty: Model.(String?) -> Unit,
    valueToString: (Value?) -> String?,
    stringToValue: (String?) -> Value,
    mapValue: (value: Value) -> Value,
    key: Any? = Unit,
): FormBox<Model, Value> {
    val focusRequester =
        remember(key) {
            FocusRequester()
        }

    val formBox =
        rememberSaveable(
            key,
            saver =
                FormBox.Saver(
                    enabled,
                    validator,
                    setModelProperty,
                    valueToString,
                    stringToValue,
                    focusRequester,
                    mapValue,
                ),
        ) {
            FormBox(
                MutableStateFlow(enabled),
                validator,
                setModelProperty,
                valueToString,
                MutableStateFlow(stringToValue("")),
                MutableStateFlow(null),
                focusRequester,
                mapValue,
            )
        }
    LaunchedEffect(validator) {
        formBox.setValidator(validator)
    }
    LaunchedEffect(enabled) {
        formBox.setEnabled(enabled)
    }
    return formBox
}
