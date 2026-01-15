package com.idscodelabs.compose_form.annotations.validators

import androidx.compose.runtime.Composable
import com.idscodelabs.compose_form.validators.DateAfterValidator
import com.idscodelabs.compose_form.validators.DateBeforeValidator
import com.idscodelabs.compose_form.validators.core.Validator
import kotlinx.datetime.LocalDate

@Target(AnnotationTarget.PROPERTY)
@MustBeDocumented
annotation class DateBefore(
    val day: Int,
    val month: Int,
    val year: Int,
    val error: String = "",
)

@Composable
fun DateBefore.validator(): Validator<LocalDate> {
    val before = LocalDate(year, month, day)
    return if (error.isEmpty()) {
        DateBeforeValidator(before)
    } else {
        DateBeforeValidator(before, error)
    }
}
