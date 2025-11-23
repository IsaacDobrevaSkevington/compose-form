package com.idscodelabs.compose_form.form.fields.core.base

import androidx.compose.runtime.Composable
import com.idscodelabs.compose_form.form.core.FormScope
import com.idscodelabs.compose_form.validators.core.Validator
import kotlin.reflect.KProperty

/**
 * Generic wrapper for a form field handling [String]s
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
fun <Model> FormScope<Model>.StringFieldFormFieldWrapper(
    modelProperty: KProperty<*>,
    initialValue: String?,
    enabled: Boolean,
    validator: Validator?,
    updateModel: Model.(String?) -> Unit,
    implementation: FormFieldImplementation<String>,
) = FormFieldWrapper(
    modelProperty = modelProperty,
    initialValue = initialValue,
    enabled = enabled,
    validator = validator,
    updateModel = updateModel,
    implementation = implementation,
    valueToString = { it },
    stringToValue = { it ?: "" },
    formImplementationMapper = { this },
)
