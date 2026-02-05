package com.idscodelabs.compose_form.annotations.validators

import androidx.compose.runtime.Composable
import com.idscodelabs.compose_form.validators.RegexValidator

@Target(AnnotationTarget.PROPERTY)
@MustBeDocumented
@Repeatable
annotation class Regex(
    val error: String = "Invalid format",
)

@Composable
fun Regex.validator() =
    RegexValidator(
        error,
    )
