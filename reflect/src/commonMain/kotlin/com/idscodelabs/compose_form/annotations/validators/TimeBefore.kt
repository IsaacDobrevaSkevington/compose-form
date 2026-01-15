package com.idscodelabs.compose_form.annotations.validators

import androidx.compose.runtime.Composable
import com.idscodelabs.compose_form.validators.TimeBeforeValidator
import com.idscodelabs.compose_form.validators.core.Validator
import kotlinx.datetime.LocalTime

@Target(AnnotationTarget.PROPERTY)
@MustBeDocumented
annotation class TimeBefore(
    val hour: Int,
    val minute: Int,
    val second: Int,
    val error: String = "",
)

@Composable
fun TimeBefore.validator(): Validator<LocalTime> {
    val before = LocalTime(hour, minute, second)
    return if (error.isEmpty()) {
        TimeBeforeValidator(before)
    } else {
        TimeBeforeValidator(before, error = error)
    }
}
