package com.idscodelabs.compose_form.form.fields.core.text

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.input.TextFieldValue
import com.idscodelabs.compose_form.form.core.controller.FormController
import com.idscodelabs.compose_form.form.fields.core.base.FormFieldImplementation
import com.idscodelabs.compose_form.utils.updateModel
import com.idscodelabs.compose_form.validators.core.Validator
import kotlin.reflect.KMutableProperty

@Composable
fun <Model> FormController<Model>.FormTextField(
    modelProperty: KMutableProperty<String?>,
    initialValue: String? = null,
    validator: Validator<String>? = null,
    enabled: Boolean = true,
    implementation: FormFieldImplementation<TextFieldValue>,
) = FormTextField(
    modelProperty = modelProperty,
    updateModel = modelProperty.updateModel(),
    initialValue = initialValue,
    validator = validator,
    enabled = enabled,
    implementation = implementation
)
