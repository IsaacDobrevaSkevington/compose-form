package com.idscodelabs.compose_form.form.fields.core.switch

import androidx.compose.runtime.Composable
import com.idscodelabs.compose_form.form.core.controller.FormController
import com.idscodelabs.compose_form.form.fields.core.base.FormFieldImplementation
import com.idscodelabs.compose_form.utils.updateModel
import com.idscodelabs.compose_form.utils.validator
import com.idscodelabs.compose_form.validators.core.Validator
import kotlin.reflect.KMutableProperty

@Composable
fun <Model> FormController<Model>.FormSwitchField(
    modelProperty: KMutableProperty<Boolean?>,
    initialValue: Boolean? = null,
    validator: Validator<Boolean>? = modelProperty.validator(),
    enabled: Boolean = true,
    implementation: FormFieldImplementation<Boolean>,
) = FormSwitchField(
    modelProperty = modelProperty,
    initialValue = initialValue,
    enabled = enabled,
    validator = validator,
    updateModel = modelProperty.updateModel(),
    implementation = implementation,
)
