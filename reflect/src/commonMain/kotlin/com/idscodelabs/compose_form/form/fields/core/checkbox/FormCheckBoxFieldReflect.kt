package com.idscodelabs.compose_form.form.fields.core.checkbox

import androidx.compose.runtime.Composable
import com.idscodelabs.compose_form.form.core.controller.FormController
import com.idscodelabs.compose_form.form.fields.core.base.FormFieldImplementation
import com.idscodelabs.compose_form.utils.updateModel
import com.idscodelabs.compose_form.validators.core.Validator
import kotlin.reflect.KMutableProperty

@Composable
fun <Model> FormController<Model>.FormCheckBoxField(
    modelProperty: KMutableProperty<Boolean?>,
    initialValue: Boolean? = null,
    validator: Validator<Boolean>? = null,
    enabled: Boolean = true,
    implementation: FormFieldImplementation<Boolean>,
) = FormCheckBoxField(
    modelProperty = modelProperty,
    initialValue = initialValue,
    enabled = enabled,
    validator = validator,
    updateModel = modelProperty.updateModel(),
    implementation = implementation,
)
