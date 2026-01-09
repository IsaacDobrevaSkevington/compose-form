package com.idscodelabs.compose_form.form.fields.core.date

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.input.TextFieldValue
import com.idscodelabs.compose_form.form.core.controller.FormController
import com.idscodelabs.compose_form.form.fields.core.base.FormFieldImplementation
import com.idscodelabs.compose_form.utils.updateModel
import com.idscodelabs.compose_form.validators.core.Validator
import kotlinx.datetime.LocalDate
import kotlin.reflect.KMutableProperty

@Composable
fun <Model> FormController<Model>.FormDateField(
    modelProperty: KMutableProperty<LocalDate?>,
    initialValue: LocalDate? = null,
    validator: Validator<LocalDate>? = null,
    enabled: Boolean = true,
    invalidDateMessage: Any = "Invalid date format",
    cleanDate: (String) -> String = { sanitizeDate(it) },
    implementation: FormFieldImplementation<TextFieldValue>,
) = FormDateField(
    modelProperty = modelProperty,
    updateModel = modelProperty.updateModel(),
    initialValue = initialValue,
    validator = validator,
    enabled = enabled,
    invalidDateMessage = invalidDateMessage,
    cleanDate = cleanDate,
    implementation = implementation,
)
