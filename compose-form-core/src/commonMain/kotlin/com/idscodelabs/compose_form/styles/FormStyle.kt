package com.idscodelabs.compose_form.styles

import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

val LocalFormStyle =
    compositionLocalOf {
        FormStyle()
    }

data class FormStyle(
    val fieldSpacing: Dp = 8.dp,
    val fieldRowSpacing: Dp = 8.dp,
    val fieldColumnSpacing: Dp = 8.dp,
)
