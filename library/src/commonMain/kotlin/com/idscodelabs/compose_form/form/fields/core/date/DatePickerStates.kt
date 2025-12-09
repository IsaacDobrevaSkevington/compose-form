package com.idscodelabs.compose_form.form.fields.core.date

import androidx.compose.material3.DisplayMode
import androidx.compose.material3.SelectableDates
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.atStartOfDayIn
import kotlin.time.ExperimentalTime

/**
 * Date picker state with min and max selectable values
 *
 * @param min The min value selectable (inclusive)
 * @param max The max value selectable (inclusive)
 * @param initialDisplayMode Initial [DisplayMode] for the [androidx.compose.material3.DatePicker]
 * @sample com.idscodelabs.compose_form.examples.fields.date.FormDateFieldExample
 *
 */
@OptIn(ExperimentalTime::class)
@Composable
fun rememberDatePickerMinMaxState(
    min: LocalDate,
    max: LocalDate,
    initialDisplayMode: DisplayMode = DisplayMode.Picker,
) = rememberDatePickerState(
    initialDisplayMode = initialDisplayMode,
    yearRange = min.year..max.year,
    selectableDates =
        object : SelectableDates {
            override fun isSelectableDate(utcTimeMillis: Long): Boolean =
                utcTimeMillis >= min.atStartOfDayIn(TimeZone.UTC).toEpochMilliseconds() &&
                    utcTimeMillis <= max.atStartOfDayIn(TimeZone.UTC).toEpochMilliseconds()

            override fun isSelectableYear(year: Int): Boolean = year in min.year..max.year
        },
)
