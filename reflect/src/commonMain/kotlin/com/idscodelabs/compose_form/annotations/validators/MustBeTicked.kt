package com.idscodelabs.compose_form.annotations.validators

import androidx.compose.runtime.Composable
import com.idscodelabs.compose_form.validators.MustBeTickedValidator
import com.idscodelabs.compose_form.validators.core.Validator

@Target(AnnotationTarget.PROPERTY)
@MustBeDocumented
annotation class MustBeTicked(
    val error: String = "Required",
)

@Composable
fun MustBeTicked.validator() =
    MustBeTickedValidator(
        error,
    )
