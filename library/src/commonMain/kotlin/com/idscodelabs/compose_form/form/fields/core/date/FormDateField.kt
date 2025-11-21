package com.idscodelabs.compose_form.form.fields.core.date

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.TextFieldValue
import com.idscodelabs.compose_form.form.core.FormScope
import com.idscodelabs.compose_form.form.fields.core.base.FormFieldImplementation
import com.idscodelabs.compose_form.form.fields.core.base.TextFieldFormFieldWrapper
import com.idscodelabs.compose_form.validators.DateValidator
import com.idscodelabs.compose_form.validators.core.Validator
import kotlinx.datetime.LocalDate
import kotlinx.datetime.format
import kotlin.reflect.KProperty

@Composable
fun <Model> FormScope<Model>.FormDateField(
    modelProperty: KProperty<*>,
    updateModel: Model.(LocalDate?) -> Unit,
    initialValue: LocalDate? = null,
    validator: Validator? = null,
    enabled: Boolean = true,
    invalidDateMessage: Any = "Invalid date format",
    cleanDate: (String) -> String = { sanitizeDate(it) },
    implementation: FormFieldImplementation<TextFieldValue>,
) {
    val dateFormatter = LocalFormDateFormatter.current
    TextFieldFormFieldWrapper(
        modelProperty = modelProperty,
        initialValue = initialValue,
        enabled = enabled,
        validator = DateValidator(invalidDateMessage) + validator,
        updateModel = updateModel,
        implementation = implementation,
        valueToString = { it?.format(dateFormatter) },
        stringToValue = {
            runCatching {
                it?.ifBlank { null }?.let(dateFormatter::parse)
            }.getOrNull()
        },
        { this },
        mapValue = {
            val cleaned = cleanDate(it.text)
            TextFieldValue(cleaned, TextRange(cleaned.length))
        },
    )
}
