package com.idscodelabs.compose_form.validators

import androidx.compose.runtime.Composable
import com.idscodelabs.compose_form.form.fields.core.date.LocalFormDateFormatter
import com.idscodelabs.compose_form.validators.core.Validator
import kotlinx.datetime.LocalDate

@Composable
fun DateAfterValidator(
    after: LocalDate,
    error: Any = "Must be after ${LocalFormDateFormatter.current.format(after)}",
) = Validator<LocalDate> { value, _ ->
    if (value == null) return@Validator null
    return@Validator if (value > after) null else error
}
