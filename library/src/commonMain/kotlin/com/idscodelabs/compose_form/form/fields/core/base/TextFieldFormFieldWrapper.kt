package com.idscodelabs.compose_form.form.fields.core.base

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.TextFieldValue
import com.idscodelabs.compose_form.form.core.FormScope
import com.idscodelabs.compose_form.validators.core.Validator
import kotlin.reflect.KProperty

@Composable
fun <Model, Value, Parameters : AbstractFormFieldImplementationParameters<TextFieldValue>> FormScope<Model>.TextFieldFormFieldWrapper(
    modelProperty: KProperty<Value?>,
    initialValue: Value?,
    enabled: Boolean,
    validator: Validator?,
    updateModel: Model.(Value?) -> Unit,
    implementation: IFormFieldImplementation<Parameters>,
    valueToString: (Value?) -> String?,
    stringToValue: (String?) -> Value?,
    formImplementationMapper: FormFieldImplementationParameters<TextFieldValue>.() -> Parameters,
    mapValue: (TextFieldValue) -> TextFieldValue = { it },
) = FormFieldWrapper(
    modelProperty = modelProperty,
    initialValue = initialValue,
    enabled = enabled,
    validator = validator,
    updateModel = updateModel,
    implementation = implementation,
    formImplementationMapper = formImplementationMapper,
    valueToStored = {
        valueToString(it)?.trim()?.let { string ->
            TextFieldValue(string, TextRange(string.length))
        }
    },
    storedToString = { it?.text?.trim() },
    stringToValue = stringToValue,
    rememberState = {
        rememberSaveable(it, stateSaver = TextFieldValue.Saver) {
            mutableStateOf(TextFieldValue(""))
        }
    },
    mapValue = mapValue,
)
