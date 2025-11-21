package com.idscodelabs.compose_form.form.fields.core.base

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.TextFieldValue
import com.idscodelabs.compose_form.form.core.FormViewModel
import com.idscodelabs.compose_form.form.model.FormBox
import com.idscodelabs.compose_form.validators.core.Validator
import kotlin.reflect.KProperty

@Composable
fun <Model, Value, Parameters : FormBox<Model, TextFieldValue>> FormViewModel<Model>.TextFieldFormFieldWrapper(
    modelProperty: KProperty<*>,
    initialValue: Value?,
    enabled: Boolean,
    validator: Validator?,
    updateModel: Model.(Value?) -> Unit,
    implementation: IFormFieldImplementation<Parameters>,
    valueToString: (Value?) -> String?,
    stringToValue: (String?) -> Value?,
    formImplementationMapper: FormBox<Model, TextFieldValue>.() -> Parameters,
    mapValue: (TextFieldValue) -> TextFieldValue = { it },
) = FormFieldWrapper(
    modelProperty = modelProperty,
    initialValue = valueToString(initialValue)?.let(::TextFieldValue),
    enabled = enabled,
    validator = validator,
    updateModel = {
        updateModel(stringToValue(it?.text))
    },
    implementation = implementation,
    formImplementationMapper = formImplementationMapper,
    valueToString = {
        it?.text?.ifBlank { null }?.trim()
    },
    stringToValue = {
        (it?.trim()?.ifBlank { null } ?: "").let { string ->
            TextFieldValue(string, TextRange(string.length))
        }
    },
    mapValue = mapValue,
)
