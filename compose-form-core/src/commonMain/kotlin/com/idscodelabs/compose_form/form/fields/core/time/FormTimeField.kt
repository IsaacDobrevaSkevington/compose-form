package com.idscodelabs.compose_form.form.fields.core.time

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.TextFieldValue
import com.idscodelabs.compose_form.form.core.FormScope
import com.idscodelabs.compose_form.form.fields.core.base.FormFieldImplementation
import com.idscodelabs.compose_form.form.fields.core.base.TextFieldFormFieldWrapper
import com.idscodelabs.compose_form.validators.TimeValidator
import com.idscodelabs.compose_form.validators.core.Validator
import kotlinx.datetime.LocalTime
import kotlinx.datetime.format
import kotlin.reflect.KProperty

@Composable
fun <Model> FormScope<Model>.FormTimeField(
    modelProperty: KProperty<*>,
    updateModel: Model.(LocalTime?) -> Unit,
    initialValue: LocalTime? = null,
    validator: Validator? = null,
    enabled: Boolean = true,
    invalidTimeMessage: Any = "Invalid time format",
    cleanTime: (String) -> String = { sanitizeTime(it) },
    implementation: FormFieldImplementation<TextFieldValue>,
) {
    val timeFormatter = LocalFormTimeFormatter.current
    TextFieldFormFieldWrapper(
        modelProperty = modelProperty,
        initialValue = initialValue,
        enabled = enabled,
        validator = TimeValidator(invalidTimeMessage) + validator,
        updateModel = updateModel,
        implementation = implementation,
        valueToString = { it?.format(timeFormatter) },
        stringToValue = {
            runCatching {
                it?.ifBlank { null }?.let(timeFormatter::parse)
            }.getOrNull()
        },
        { this },
        mapValue = {
            val cleaned = cleanTime(it.text)
            TextFieldValue(cleaned, TextRange(cleaned.length))
        },
    )
}
