package com.idscodelabs.compose_form.validators

import androidx.compose.runtime.Composable
import com.idscodelabs.compose_form.form.fields.core.date.LocalFormDateFormatter
import com.idscodelabs.compose_form.validators.core.Validator
import kotlinx.datetime.LocalDate
import kotlinx.datetime.format.DateTimeFormat

@Composable
fun DateAfterValidator(
    after: LocalDate,
    formatter: DateTimeFormat<LocalDate> = LocalFormDateFormatter.current,
    error: Any = "Must be after ${formatter.format(after)}",
) = Validator { s, _ ->
    if (s == null) return@Validator null
    val date = formatter.parse(s)
    return@Validator if (date > after) null else error
}
