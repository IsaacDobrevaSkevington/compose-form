package com.idscodelabs.compose_form.styles

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp

interface FormFieldStyle {
    @Composable
    operator fun invoke(contents: @Composable () -> Unit) {
        CompositionLocalProvider(LocalFormFieldStyle provides this) {
            contents()
        }
    }

    @Composable
    fun colors(): TextFieldColors

    @Composable
    fun labelColor(): Color

    @Composable
    fun shape(): Shape

    object Outlined : FormFieldStyle {
        @Composable
        override fun colors(): TextFieldColors = OutlinedTextFieldDefaults.colors()

        @Composable
        override fun labelColor(): Color = MaterialTheme.colorScheme.outline

        @Composable
        override fun shape(): Shape = RoundedCornerShape(4.dp)
    }

    object Filled : FormFieldStyle {
        @Composable
        override fun colors(): TextFieldColors = TextFieldDefaults.colors()

        @Composable
        override fun labelColor(): Color = MaterialTheme.colorScheme.onBackground

        @Composable
        override fun shape(): Shape = RoundedCornerShape(4.dp)
    }
}

val LocalFormFieldStyle = compositionLocalOf<FormFieldStyle> { FormFieldStyle.Outlined }
