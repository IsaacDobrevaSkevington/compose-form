package com.idscodelabs.compose_form.annotations.validators

import androidx.compose.runtime.Composable
import com.idscodelabs.compose_form.validators.NumberOnlyValidator

@Target(AnnotationTarget.PROPERTY)
@MustBeDocumented
annotation class NumberOnly(
    val error: String = "Numbers only",
)

@Composable
fun NumberOnly.validator() = NumberOnlyValidator(error)
