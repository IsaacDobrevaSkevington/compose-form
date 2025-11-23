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

/**
 * A date form field
 *
 * @param Model The Model of the form
 * @param modelProperty The [KProperty] of the [Model] which this field updates.
 * @param initialValue The initial value for this field
 * @param enabled Whether the field is enabled
 * @param validator The [Validator] for this field
 * @param updateModel Function called which should set the correct property on the model. Most often should be `{ <variable> = it }`
 * @param implementation The implementation of the form field UI
 * @param invalidDateMessage The message to display if the date is in an invalid format
 * @param cleanDate A function which can be used to coerce the user's input into a date format
 * @see [FormFieldImplementation]
 * @see [LocalFormDateFormatter]
 * @see [sanitizeDate]
 * @see [com.idscodelabs.compose_form.form.fields.default.date.DefaultDateEntry]
 */
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
