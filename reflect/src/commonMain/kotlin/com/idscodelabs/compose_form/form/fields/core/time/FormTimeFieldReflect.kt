package com.idscodelabs.compose_form.form.fields.core.time

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.input.TextFieldValue
import com.idscodelabs.compose_form.form.core.controller.FormController
import com.idscodelabs.compose_form.form.fields.core.base.FormFieldImplementation
import com.idscodelabs.compose_form.utils.updateModel
import com.idscodelabs.compose_form.utils.validator
import com.idscodelabs.compose_form.validators.core.Validator
import kotlinx.datetime.LocalTime
import kotlin.reflect.KMutableProperty

@Composable
fun <Model> FormController<Model>.FormTimeField(
    modelProperty: KMutableProperty<LocalTime?>,
    initialValue: LocalTime? = null,
    validator: Validator<LocalTime>? = modelProperty.validator(),
    enabled: Boolean = true,
    invalidTimeMessage: Any = "Invalid time format",
    cleanTime: (String) -> String = { sanitizeTime(it) },
    implementation: FormFieldImplementation<TextFieldValue>,
) = FormTimeField(
    modelProperty = modelProperty,
    updateModel = modelProperty.updateModel(),
    initialValue = initialValue,
    validator = validator,
    enabled = enabled,
    invalidTimeMessage = invalidTimeMessage,
    cleanTime = cleanTime,
    implementation = implementation,
)
