package com.idscodelabs.compose_form.form.fields.core.base

import androidx.compose.runtime.*
import androidx.compose.ui.focus.FocusRequester
import com.idscodelabs.compose_form.form.core.FormScope
import com.idscodelabs.compose_form.form.fields.strings.asDisplayString
import com.idscodelabs.compose_form.form.model.FormBox
import com.idscodelabs.compose_form.validators.core.Validator
import kotlin.reflect.KProperty

@Composable
fun <Model, Value, Stored, Parameters : AbstractFormFieldImplementationParameters<Stored>> FormScope<Model>.FormFieldWrapper(
    modelProperty: KProperty<Value?>,
    initialValue: Value?,
    enabled: Boolean,
    validator: Validator?,
    updateModel: Model.(Value?) -> Unit,
    implementation: IFormFieldImplementation<Parameters>,
    valueToStored: (Value?) -> Stored?,
    storedToString: (Stored?) -> String?,
    stringToValue: (String?) -> Value?,
    formImplementationMapper: FormFieldImplementationParameters<Stored>.() -> Parameters,
    rememberState: @Composable (input: Any) -> MutableState<Stored>,
    mapValue: (value: Stored) -> Stored = { it },
) {
    val (value, _setValue) = rememberState(modelProperty.name)

    LaunchedEffect(initialValue) {
        if (initialValue != null) {
            valueToStored(initialValue)?.let(_setValue)
        }
    }

    val (error, setError) =
        remember {
            mutableStateOf<Any?>(null)
        }
    val focusRequester = remember { FocusRequester() }

    val setValue = { it: Stored ->
        if (enabled) {
            _setValue(mapValue(it))
        }
        setError(null)
    }

    val params =
        FormFieldImplementationParameters(
            value = value,
            _setValue = setValue,
            error = error?.asDisplayString(),
            enabled = enabled,
            focusRequester = focusRequester,
        ).formImplementationMapper()

    implementation(params)

    FormBox<Model>(
        validator,
        {
            updateModel(stringToValue(it?.ifBlank { null }))
        },
        { storedToString(value)?.ifBlank { null } },
        setError,
        focusRequester,
    ).addToForm(modelProperty)
}
