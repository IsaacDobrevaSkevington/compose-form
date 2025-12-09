package com.idscodelabs.compose_form.form.fields.core.base

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.TextFieldValue
import com.idscodelabs.compose_form.form.core.FormScope
import com.idscodelabs.compose_form.form.model.FormBox
import com.idscodelabs.compose_form.validators.core.Validator
import kotlin.reflect.KProperty

/**
 * Generic wrapper for a form field handling [TextFieldValue]s
 *
 * @param Model The Model of the form
 * @param Value The stored value of the field
 * @param FormBoxImpl The type of [FormBox] that this field uses
 * @param modelProperty The [KProperty] of the [Model] which this field updates.
 * @param initialValue The initial value for this field
 * @param enabled Whether the field is enabled
 * @param validator The [Validator] for this field
 * @param updateModel Function called which should set the correct property on the model. Most often should be `{ <variable> = it }`
 * @param implementation The implementation of the form field UI
 * @param valueToString Convert [Value] to a [String]. This will be used in [Validator]s and used for state saving
 * @param stringToValue Convert [String] to [Value]. This will be used when restoring state
 * @param formImplementationMapper Map the standard [FormBox] to the specific [FormBox] when extra parameters are required
 * @param mapValue Map the value on set to another [Value], for example, to clean the input
 * @see [FormFieldImplementation]
 *
 */
@Composable
fun <Model, Value, FormBoxImpl : FormBox<Model, TextFieldValue>> FormScope<Model>.TextFieldFormFieldWrapper(
    modelProperty: KProperty<*>,
    initialValue: Value?,
    enabled: Boolean,
    validator: Validator<Value>?,
    updateModel: Model.(Value?) -> Unit,
    implementation: IFormFieldImplementation<FormBoxImpl>,
    valueToString: (Value?) -> String?,
    stringToValue: (String?) -> Value?,
    formImplementationMapper: FormBox<Model, TextFieldValue>.() -> FormBoxImpl,
    mapValue: (TextFieldValue) -> TextFieldValue = { it },
) = FormFieldWrapper(
    modelProperty = modelProperty,
    initialValue = valueToString(initialValue)?.let(::TextFieldValue),
    enabled = enabled,
    validator = Validator<TextFieldValue> {

    },
    updateModel = {
        updateModel(stringToValue(it?.text))
    },
    implementation = implementation,
    formImplementationMapper = formImplementationMapper,
    valueToString = {
        it?.text
    },
    stringToValue = {
        (it ?: "").let { string ->
            TextFieldValue(string, TextRange(string.length))
        }
    },
    mapValue = mapValue,
)
