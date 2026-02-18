package com.idscodelabs.compose_form.annotations.validators

import androidx.compose.runtime.Composable
import com.idscodelabs.compose_form.validators.MinCountValidator

@Target(AnnotationTarget.PROPERTY)
@MustBeDocumented
annotation class MinCount(
    val minCount: Int,
    val error: String = "",
)

@Composable
fun MinCount.validator() =
    MinCountValidator(
        minCount,
        error.ifBlank { "Fewer than $minCount options selected." },
    )
