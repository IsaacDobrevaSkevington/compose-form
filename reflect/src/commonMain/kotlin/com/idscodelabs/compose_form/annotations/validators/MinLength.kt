package com.idscodelabs.compose_form.annotations.validators

import androidx.compose.runtime.Composable
import com.idscodelabs.compose_form.validators.MaxLengthValidator
import com.idscodelabs.compose_form.validators.core.StringValidator

@Target(AnnotationTarget.PROPERTY)
@MustBeDocumented
annotation class MinLength(
    val minLength: Int,
    val error: String = "",
)

@Composable
fun MinLength.validator() =
    MaxLengthValidator(
        minLength,
        error.ifBlank { "Shorter than $minLength characters" },
    )
