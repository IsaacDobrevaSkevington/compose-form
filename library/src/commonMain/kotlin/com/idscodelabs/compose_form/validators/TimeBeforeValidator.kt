package com.idscodelabs.compose_form.validators

import androidx.compose.runtime.Composable
import com.idscodelabs.compose_form.form.fields.core.time.LocalFormTimeFormatter
import com.idscodelabs.compose_form.validators.core.Validator
import kotlinx.datetime.LocalTime
import kotlinx.datetime.format.DateTimeFormat

@Composable
fun TimeBeforeValidator(
    before: LocalTime,
    formatter: DateTimeFormat<LocalTime> = LocalFormTimeFormatter.current,
    error: Any = "Must be before ${formatter.format(before)}",
) = Validator<LocalTime> { value, _ ->
    if (value == null) return@Validator null
    if (value < before) null else error
}
