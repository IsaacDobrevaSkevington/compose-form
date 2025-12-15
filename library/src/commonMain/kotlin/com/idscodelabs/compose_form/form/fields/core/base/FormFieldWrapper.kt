package com.idscodelabs.compose_form.form.fields.core.base

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.idscodelabs.compose_form.form.core.controller.FormController
import com.idscodelabs.compose_form.form.model.FormBox
import com.idscodelabs.compose_form.form.model.rememberFormBox
import com.idscodelabs.compose_form.validators.core.Validator
import kotlin.reflect.KProperty

/**
 * Generic wrapper for a form field handling FormFields
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
fun <Model, Value, FormBoxImpl : FormBox<*, Value>> FormController<Model>.FormFieldWrapper(
    modelProperty: KProperty<*>,
    initialValue: Value?,
    enabled: Boolean,
    validator: Validator<Value>?,
    updateModel: Model.(Value?) -> Unit,
    implementation: IFormFieldImplementation<FormBoxImpl>,
    valueToString: (Value?) -> String?,
    stringToValue: (String?) -> Value,
    formImplementationMapper: FormBox<Model, Value>.() -> FormBoxImpl,
    mapValue: (value: Value) -> Value = { it },
) {
    val formBox =
        rememberFormBox<Model, Value>(
            initialValue = initialValue,
            enabled = enabled,
            validator = validator,
            setModelProperty = {
                updateModel(stringToValue(it?.ifBlank { null }))
            },
            valueToString = valueToString,
            stringToValue = stringToValue,
            key = modelProperty.name,
            mapValue = mapValue,
        )

    val params = formBox.formImplementationMapper()

    implementation(params)

    formBox.BindLifecycle(modelProperty)
}
