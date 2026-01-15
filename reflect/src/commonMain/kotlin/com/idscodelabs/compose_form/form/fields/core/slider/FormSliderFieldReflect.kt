package com.idscodelabs.compose_form.form.fields.core.slider

import androidx.compose.runtime.Composable
import com.idscodelabs.compose_form.form.core.controller.FormController
import com.idscodelabs.compose_form.form.fields.core.base.FormFieldImplementation
import com.idscodelabs.compose_form.utils.updateModel
import com.idscodelabs.compose_form.utils.validator
import com.idscodelabs.compose_form.validators.core.Validator
import kotlin.reflect.KMutableProperty

@Composable
fun <Model> FormController<Model>.FormSliderField(
    modelProperty: KMutableProperty<Int?>,
    initialValue: Int? = null,
    validator: Validator<Int>? = modelProperty.validator(),
    enabled: Boolean = true,
    implementation: FormFieldImplementation<Int>,
) = FormSliderField(
    modelProperty = modelProperty,
    updateModel = modelProperty.updateModel(),
    initialValue = initialValue,
    validator = validator,
    enabled = enabled,
    implementation = implementation,
)
