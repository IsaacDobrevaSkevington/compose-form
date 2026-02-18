package com.idscodelabs.compose_form.annotations.validators

import androidx.compose.runtime.Composable
import com.idscodelabs.compose_form.validators.MaxCountValidator
import com.idscodelabs.compose_form.validators.MaxLengthValidator

@Target(AnnotationTarget.PROPERTY)
@MustBeDocumented
annotation class MaxCount(
    val maxCount: Int,
    val error: String = "",
)

@Composable
fun MaxCount.validator() =
    MaxCountValidator(
        maxCount,
        error.ifBlank { "More than $maxCount options selected." },
    )
