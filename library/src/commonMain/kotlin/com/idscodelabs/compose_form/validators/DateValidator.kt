package com.idscodelabs.compose_form.validators

import androidx.compose.runtime.Composable
import com.idscodelabs.compose_form.form.fields.core.date.LocalFormDateFormatter
import com.idscodelabs.compose_form.validators.core.Validator
import kotlinx.datetime.LocalDate
import kotlinx.datetime.format.DateTimeFormat

@Composable
fun DateValidator(
    error: Any = "Invalid date format",
    formatter: DateTimeFormat<LocalDate> = LocalFormDateFormatter.current,
): Validator<LocalDate> {
    return Validator { _, stringRepresentation ->
        if (stringRepresentation.isNullOrBlank()) return@Validator null
        try {
            formatter.parse(stringRepresentation)
            null
        } catch (_: Throwable) {
            error
        }
    }
}
