package com.idscodelabs.compose_form.annotations.validators

import androidx.compose.runtime.Composable
import com.idscodelabs.compose_form.validators.DateAfterValidator
import com.idscodelabs.compose_form.validators.core.Validator
import kotlinx.datetime.LocalDate

@Target(AnnotationTarget.PROPERTY)
@MustBeDocumented
annotation class DateAfter(
    val year: Int,
    val month: Int,
    val day: Int,
    val error: String = "",
)

@Composable
fun DateAfter.validator(): Validator<LocalDate> {
    val after = LocalDate(year, month, day)
    return if (error.isEmpty()) {
        DateAfterValidator(after)
    } else {
        DateAfterValidator(after, error)
    }
}
