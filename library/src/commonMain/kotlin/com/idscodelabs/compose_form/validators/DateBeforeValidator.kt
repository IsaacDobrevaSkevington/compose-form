package com.idscodelabs.compose_form.validators

import androidx.compose.runtime.Composable
import com.idscodelabs.compose_form.form.fields.core.date.LocalFormDateFormatter
import com.idscodelabs.compose_form.validators.core.Validator
import kotlinx.datetime.LocalDate
import kotlinx.datetime.format.DateTimeFormat

@Composable
fun DateBeforeValidator(
    before: LocalDate,
    formatter: DateTimeFormat<LocalDate> = LocalFormDateFormatter.current,
    error: Any = "Must be before ${formatter.format(before)}",
) = Validator {s, _ ->
    if (s == null) return@Validator null
    val date = formatter.parse(s)
    return@Validator if (date < before) null else error
}

