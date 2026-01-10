package com.idscodelabs.compose_form.annotations.validators

import androidx.compose.runtime.Composable
import com.idscodelabs.compose_form.form.fields.core.time.LocalFormTimeFormatter
import com.idscodelabs.compose_form.validators.core.Validator
import kotlinx.datetime.LocalTime
import kotlinx.datetime.format.DateTimeFormat

@Composable
fun TimeAfterValidator(
    after: LocalTime,
    formatter: DateTimeFormat<LocalTime> = LocalFormTimeFormatter.current,
    error: Any = "Must be after ${formatter.format(after)}",
) = Validator<LocalTime> { value, _ ->
    if (value == null) return@Validator null
    if (value > after) null else error
}
