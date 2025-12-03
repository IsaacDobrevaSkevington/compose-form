package com.idscodelabs.compose_form.form.fields.core.date

import androidx.compose.runtime.compositionLocalOf
import kotlinx.datetime.LocalDate
import kotlinx.datetime.format.char

/**
 * Provides a default date formatter for date fields of format dd/MM/yyyy.
 *
 * This can be changed by using `CompositionLocalProvider(LocalFormDateFormatter provides <your format>){...}`
 *
 * @see kotlinx.datetime.format.DateTimeFormat
 */
val LocalFormDateFormatter =
    compositionLocalOf {
        LocalDate.Format {
            day()
            char('/')
            monthNumber()
            char('/')
            year()
        }
    }
