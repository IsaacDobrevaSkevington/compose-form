package com.idscodelabs.compose_form.validators

import androidx.compose.runtime.Composable
import com.idscodelabs.compose_form.form.fields.core.time.LocalFormTimeFormatter
import com.idscodelabs.compose_form.validators.core.Validator
import kotlinx.datetime.LocalTime
import kotlinx.datetime.format.DateTimeFormat

@Composable
fun TimeValidator(
    error: Any = "Invalid time format",
    formatter: DateTimeFormat<LocalTime> = LocalFormTimeFormatter.current,
) = Validator {s, _ ->
    if (s == null) return@Validator null
    try {
        formatter.parse(s)
        null
    } catch (_: Throwable) {
        error
    }
}
