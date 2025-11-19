package com.idscodelabs.compose_form.form.fields.core.time

import androidx.compose.runtime.compositionLocalOf
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalTime
import kotlinx.datetime.format.char

val LocalFormTimeFormatter =
    compositionLocalOf {
        LocalTime.Format {
            hour()
            char(':')
            minute()
        }
    }
