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

open class FormBox<Model, Value>(
    protected val enabledFlow: MutableStateFlow<Boolean>,
    protected var currentValidator: Validator?,
    val setModelProperty: Model.(String?) -> Unit,
    protected val valueToString: (Value?) -> String?,
    protected val stringToValue: (String?) -> Value,
    protected val stringValueFlow: MutableStateFlow<String>,
    protected val errorFlow: MutableStateFlow<Any?> = MutableStateFlow(null),
    protected val focusRequester: FocusRequester,
    protected val mapValue: (value: Value) -> Value,
) {
    constructor(field: FormBox<Model, Value>) : this(
        field.enabledFlow,
        field.currentValidator,
        field.setModelProperty,
        field.valueToString,
        field.stringToValue,
        field.stringValueFlow,
        field.errorFlow,
        field.focusRequester,
        field.mapValue,
    )

    val valueSnapshot: Value get() = valueFlow.value

    val value: Value @Composable get() {
        val result by collectValueAsState()
        return result
    }

    val enabled: Boolean @Composable get() {
        val enabled by collectEnabledAsState()
        return enabled
    }
    val error: String? @Composable get() {
        val result by collectErrorAsState()
        return result?.asDisplayString()
    }

    val valueFlow =
        stringValueFlow.mapSync {
            stringToValue(it)
        }

    fun validate(
        s: String?,
        otherFieldValues: Map<String, String?> = mapOf(),
    ): Boolean {
        val error = currentValidator?.validate(s, otherFieldValues)
        setError(error)
        return error == null
    }

    fun setError(error: Any?) {
        errorFlow.update { error }
    }

    fun setValidator(validator: Validator?) {
        this.currentValidator = validator
    }

    fun setValue(value: Value) {
        errorFlow.update { null }
        if (enabledFlow.value) {
            stringValueFlow.update {
                valueToString(mapValue(value)) ?: ""
            }
        }
    }

    fun getStringValue() = stringValueFlow.value

    fun setEnabled(enabled: Boolean) {
        enabledFlow.update { enabled }
    }

    @Composable
    fun collectStringValueAsState(): State<String> = stringValueFlow.collectAsState()

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
        override fun SaverScope.save(value: FormBox<Model, Value>): String = value.getStringValue()

        override fun restore(value: String): FormBox<Model, Value> =
            FormBox(
                MutableStateFlow(enabled),
                validator,
                setModelProperty,
                valueToString,
                stringToValue,
                MutableStateFlow(value),
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
                stringToValue,
                MutableStateFlow(""),
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
