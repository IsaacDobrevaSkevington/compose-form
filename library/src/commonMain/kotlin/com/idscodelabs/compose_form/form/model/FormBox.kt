package com.idscodelabs.compose_form.form.model

import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.SaverScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import com.idscodelabs.compose_form.form.fields.strings.asDisplayString
import com.idscodelabs.compose_form.validators.core.Validator
import io.github.vinceglb.filekit.PlatformFile
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

/**
 * Representation of a Form Box
 *
 * @param Model The Model the form outputs
 * @param Value The Value which is stored in this [FormBox]
 * @property enabledState A [MutableStateFlow] defining if this box is enabled
 * @property currentValidator The current [Validator] for this [FormBox]
 * @property setModelProperty Set the property on [Model]
 * @param valueToString Convert [Value] to a [String]. This will be used in [Validator]s and used for state saving
 * @property valueState A flow of the current value
 * @property errorState A flow of the current error
 * @property focusRequester A [FocusRequester] for this [FormBox]. Use [primaryFocusable] to specify this element which should gain focus
 * @property mapValue Map the value on set to another [Value], for example, to clean the input
 * @constructor Create empty Form box
 */
open class FormBox<Model, Value>(
    protected val enabledState: MutableStateFlow<Boolean>,
    protected var currentValidator: Validator<Value>?,
    var setModelProperty: Model.(String?) -> Unit,
    protected var valueToString: (Value?) -> String?,
    protected val valueState: MutableStateFlow<Value>,
    protected val errorState: MutableStateFlow<Any?> = MutableStateFlow(null),
    val focusRequester: FocusRequester,
    protected var mapValue: (value: Value) -> Value,
) {
    companion object {

        /**
         * Remember a [FormBox].
         * 
         * The [FormBox] will survive configuration changed
         *
         * @param initialValue Initial value the form box should have
         * @param enabled If the box is enabled
         * @param validator The validator for the box
         * @param setModelProperty Function to implement to set the model property
         * @param valueToString Convert the current value to string
         * @param stringToValue Convert the string value back to a value
         * @param mapValue Function to call just after setting the value to mutate the value actually set on the form box
         * @param key Key to remember. When this changes a new [FormBox] will be created
         * @return A [FormBox]
         */
        @Composable
        fun <Model, Value> remember(
            initialValue: Value?,
            enabled: Boolean,
            validator: Validator<Value>?,
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
                        Saver(
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

            rememberSaveable(initialValue) {
                if (initialValue != null) {
                    formBox.setValue(initialValue)
                }
                0
            }
            LaunchedEffect(validator) {
                formBox.currentValidator = validator
            }
            LaunchedEffect(enabled) {
                formBox.enabledState.value = enabled
            }
            LaunchedEffect(valueToString) {
                formBox.valueToString = valueToString
            }
            LaunchedEffect(mapValue) {
                formBox.mapValue = mapValue
            }
            LaunchedEffect(setModelProperty) {
                formBox.setModelProperty = setModelProperty
            }
            return formBox
        }
    }

    /**
     * Use this constructor to copy a form box, or when inheriting to avoid needing access to non-visible properties
     */
    constructor(field: FormBox<Model, Value>) : this(
        field.enabledState,
        field.currentValidator,
        field.setModelProperty,
        field.valueToString,
        field.valueState,
        field.errorState,
        field.focusRequester,
        field.mapValue,
    )

    /**
     * Snapshot of the current value.
     *
     * Don't use this in Composable functions due to the risk of unnecessary recomposition, or stale values.
     * Instead, use [value] or [collectValueAsState]
     */
    val valueSnapshot: Value get() = valueState.value

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
        val error = currentValidator?.validate(valueSnapshot, getStringValue())
        setError(error)
        return error == null
    }

    /**
     * Set the error of this [FormBox]
     *
     * @param error The new error to set. [asDisplayString] can be called in the UI to display the error
     */
    open fun setError(error: Any?) {
        errorState.update { error }
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
        errorState.update { null }
        if (enabledState.value) {
            valueState.update {
                mapValue(value)
            }
        }
    }

    /**
     * Get the current string value
     */
    fun getStringValue() = valueToString(valueState.value)

    /**
     * Collect value as state
     *
     * @param context [CoroutineContext] to use for collecting.
     * @return A [State] of the current value
     */
    @Composable
    fun collectValueAsState(context: CoroutineContext = EmptyCoroutineContext): State<Value> = valueState.collectAsState(context)

    /**
     * Collect enabled as state
     *
     * @param context [CoroutineContext] to use for collecting.
     * @return A [State] of the current value of enabled
     */
    @Composable
    fun collectEnabledAsState(context: CoroutineContext = EmptyCoroutineContext): State<Boolean> = enabledState.collectAsState(context)

    /**
     * Collect error as state
     *
     * @param context [CoroutineContext] to use for collecting.
     * @return A [State] of the current error
     */
    @Composable
    fun collectErrorAsState(context: CoroutineContext = EmptyCoroutineContext): State<Any?> = errorState.collectAsState(context)

    @OptIn(FlowPreview::class)
    suspend fun onFieldValueChanged(
        debounceMillis: Long = 0,
        block: suspend Value.() -> Unit,
    ) {
        valueState.debounce(debounceMillis).distinctUntilChanged().collectLatest(block)
    }

    /**
     * On field string value changed
     *
     * @param debounceMillis Debounce for the value to allow the value to settle
     * @param block Called when the string value of the field changes
     * @receiver
     */
    @OptIn(FlowPreview::class)
    suspend fun onFieldStringValueChanged(
        debounceMillis: Long = 0,
        block: suspend String?.() -> Unit,
    ) {
        valueState
            .debounce(debounceMillis)
            .map { valueToString(it) }
            .distinctUntilChanged()
            .collectLatest(block)
    }

    /**
     * Field value changed effect.
     *
     * Use this in composables to cause side effects when the field value changes
     *
     * @param context [CoroutineContext] to use for collecting.
     * @param debounceMillis Debounce for the value to allow the value to settle
     * @param block Called when the field's value changes
     */
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
        val validator: Validator<Value>?,
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

    /**
     * Add this chainable modifier when implementing custom form boxes
     *
     * It should mark which part of the field should receive focus when the focus requester is called
     */
    fun Modifier.primaryFocusable(): Modifier = focusRequester(focusRequester)
}

/**
 * Remember a [FormBox].
 *
 * The [FormBox] will survive configuration changed
 *
 * @param initialValue Initial value the form box should have
 * @param enabled If the box is enabled
 * @param validator The validator for the box
 * @param setModelProperty Function to implement to set the model property
 * @param valueToString Convert the current value to string
 * @param stringToValue Convert the string value back to a value
 * @param mapValue Function to call just after setting the value to mutate the value actually set on the form box
 * @param key Key to remember. When this changes a new [FormBox] will be created
 * @return A [FormBox]
 */
@Composable
fun <Model, Value> rememberFormBox(
    initialValue: Value?,
    enabled: Boolean,
    validator: Validator<Value>?,
    setModelProperty: Model.(String?) -> Unit,
    valueToString: (Value?) -> String?,
    stringToValue: (String?) -> Value,
    mapValue: (value: Value) -> Value,
    key: Any? = Unit,
): FormBox<Model, Value> =
    FormBox.remember(
        initialValue,
        enabled,
        validator,
        setModelProperty,
        valueToString,
        stringToValue,
        mapValue,
        key,
    )

/**
 * Set single item value on a form box containing list of items
 */
fun <Item> FormBox<*, List<Item>>.setValue(item: Item) {
    setValue(listOf(item))
}

/**
 * Add single item value to a form box containing list of items
 */
fun <Item> FormBox<*, List<Item>>.add(item: Item) {
    val current = valueSnapshot.toMutableList()
    current.add(item)
    setValue(current)
}

/**
 * Remove single item value from a form box containing list of items
 */
fun <Item> FormBox<*, List<Item>>.remove(item: Item) {
    val current = valueSnapshot.toMutableList()
    current.remove(item)
    setValue(current)
}
