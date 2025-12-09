package com.idscodelabs.compose_form.form.fields.core.base

import androidx.compose.runtime.Composable
import com.idscodelabs.compose_form.form.core.FormScope
import com.idscodelabs.compose_form.validators.core.Validator
import kotlin.reflect.KProperty

/**
 * Generic wrapper for a form field handling [Int]s
 *
 * @param Model The Model of the form
 * @param modelProperty The [KProperty] of the [Model] which this field updates.
 * @param initialValue The initial value for this field
 * @param enabled Whether the field is enabled
 * @param validator The [Validator] for this field
 * @param updateModel Function called which should set the correct property on the model. Most often should be `{ <variable> = it }`
 * @param implementation The implementation of the form field UI
 * @see [FormFieldImplementation]
 *
 */
@Composable
fun <Model> FormScope<Model>.IntFieldFormFieldWrapper(
    modelProperty: KProperty<*>,
    initialValue: Int?,
    enabled: Boolean,
    validator: Validator<Int>?,
    updateModel: Model.(Int?) -> Unit,
    implementation: FormFieldImplementation<Int>,
) = FormFieldWrapper(
    modelProperty = modelProperty,
    initialValue = initialValue,
    enabled = enabled,
    validator = validator,
    updateModel = updateModel,
    implementation = implementation,
    valueToString = { it?.toString() },
    stringToValue = { it?.toIntOrNull() ?: 0 },
    formImplementationMapper = { this },
)
