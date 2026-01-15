package com.idscodelabs.compose_form.annotations.validators

import androidx.compose.runtime.Composable
import com.idscodelabs.compose_form.validators.TimeAfterValidator
import com.idscodelabs.compose_form.validators.core.Validator
import kotlinx.datetime.LocalTime

@Target(AnnotationTarget.PROPERTY)
@MustBeDocumented
annotation class TimeAfter(
    val hour: Int,
    val minute: Int,
    val second: Int,
    val error: String = "",
)

@Composable
fun TimeAfter.validator(): Validator<LocalTime> {
    val before = LocalTime(hour, minute, second)
    return if (error.isEmpty()) {
        TimeAfterValidator(before)
    } else {
        TimeAfterValidator(before, error = error)
    }
}
