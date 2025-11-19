package com.idscodelabs.compose_form.form.fields.core.date

import androidx.compose.runtime.compositionLocalOf
import kotlinx.datetime.LocalDate
import kotlinx.datetime.format.char

val LocalFormDateFormatter = compositionLocalOf { LocalDate.Format{
    day()
    char('/')
    monthNumber()
    char('/')
    year()
} }