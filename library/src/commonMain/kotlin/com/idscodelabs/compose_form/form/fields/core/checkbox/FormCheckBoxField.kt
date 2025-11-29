package com.idscodelabs.compose_form.form.fields.core.checkbox

import androidx.compose.runtime.Composable
import com.idscodelabs.compose_form.form.core.FormScope
import com.idscodelabs.compose_form.form.fields.core.base.BooleanFieldFormFieldWrapper
import com.idscodelabs.compose_form.form.fields.core.base.FormFieldImplementation
import com.idscodelabs.compose_form.validators.core.Validator
import kotlin.reflect.KProperty

/**
 * A checkbox form field
 *
 * @param Model The Model of the form
 * @param modelProperty The [KProperty] of the [Model] which this field updates.
 * @param initialValue The initial value for this field
 * @param enabled Whether the field is enabled
 * @param validator The [Validator] for this field
 * @param updateModel Function called which should set the correct property on the model. Most often should be `{ <variable> = it }`
 * @param implementation The implementation of the form field UI
 * @see [FormFieldImplementation]
 * @see [com.idscodelabs.compose_form.form.fields.default.checkbox.DefaultFormCheckBoxEntry]
 * @sample com.idscodelabs.compose_form.examples.fields.checkbox.FormCheckBoxFieldSample
 */
@Composable
fun <Model> FormScope<Model>.FormCheckBoxField(
    modelProperty: KProperty<*>,
    updateModel: Model.(Boolean?) -> Unit,
    initialValue: Boolean? = null,
    validator: Validator? = null,
    enabled: Boolean = true,
    implementation: FormFieldImplementation<Boolean>,
) = BooleanFieldFormFieldWrapper(
    modelProperty = modelProperty,
    initialValue = initialValue,
    enabled = enabled,
    validator = validator,
    updateModel = updateModel,
    implementation = implementation,
)
