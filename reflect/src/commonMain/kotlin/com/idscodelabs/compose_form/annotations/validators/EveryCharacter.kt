package com.idscodelabs.compose_form.annotations.validators

import androidx.compose.runtime.Composable
import com.idscodelabs.compose_form.validators.EveryCharacterValidator

@Target(AnnotationTarget.PROPERTY)
@MustBeDocumented
annotation class EveryCharacter(
    val list: CharArray,
    val error: String = "Invalid characters",
)

@Composable
fun EveryCharacter.validator() =
    EveryCharacterValidator(
        error,
    ) {
        it in list
    }
