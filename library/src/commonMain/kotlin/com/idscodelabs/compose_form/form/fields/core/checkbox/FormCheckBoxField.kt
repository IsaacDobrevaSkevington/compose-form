package com.idscodelabs.compose_form.form.fields.core.checkbox

import androidx.compose.runtime.Composable
import com.idscodelabs.compose_form.form.core.FormScope
import com.idscodelabs.compose_form.form.fields.core.base.BooleanFieldFormFieldWrapper
import com.idscodelabs.compose_form.form.fields.core.base.FormFieldImplementation
import com.idscodelabs.compose_form.validators.core.Validator
import kotlin.reflect.KProperty

@Composable
fun <Model> FormScope<Model>.FormCheckBoxField(
    modelProperty: KProperty<Boolean?>,
    initialValue: Boolean?,
    enabled: Boolean,
    validator: Validator?,
    updateModel: Model.(Boolean?) -> Unit,
    implementation: FormFieldImplementation<Boolean>,
) = BooleanFieldFormFieldWrapper(
    modelProperty = modelProperty,
    initialValue = initialValue,
    enabled = enabled,
    validator = validator,
    updateModel = updateModel,
    implementation = implementation,
)
