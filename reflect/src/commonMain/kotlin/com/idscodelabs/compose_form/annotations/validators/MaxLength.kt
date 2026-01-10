package com.idscodelabs.compose_form.annotations.validators

import com.idscodelabs.compose_form.validators.core.StringValidator

annotation class MaxLength(
    val maxLength: Int,
    val error: String = "",
)
//"Longer than $maxLength characters"
