package com.idscodelabs.compose_form.form.fields.default.date

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import com.idscodelabs.compose_form.form.fields.default.base.PickerController
import com.idscodelabs.compose_form.form.fields.strings.asDisplayString
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import kotlin.time.ExperimentalTime
import kotlin.time.Instant

@OptIn(ExperimentalTime::class, ExperimentalMaterial3Api::class)
@Composable
fun DefaultDatePickerDialog(
    controller: PickerController<DatePickerState>,
    positiveButtonText: Any = "Ok",
    negativeButtonText: Any = "Cancel",
    datePicker: @Composable ColumnScope.(state: DatePickerState) -> Unit = {
        DatePicker(
            state = it,
            showModeToggle = false,
        )
    },
    onDatePicked: (LocalDate) -> Unit,
) {
    DatePickerDialog(
        onDismissRequest = {
            controller.setPickerVisible(false)
        },
        confirmButton = {
            TextButton(onClick = {
                val newValue =
                    controller.state.selectedDateMillis?.let {
                        Instant
                            .fromEpochMilliseconds(it)
                            .toLocalDateTime(TimeZone.UTC)
                            .date
                    }
                newValue?.let(onDatePicked)
                controller.setPickerVisible(false)
            }) {
                Text(text = positiveButtonText.asDisplayString())
            }
        },
        dismissButton = {
            TextButton(onClick = { controller.setPickerVisible(false) }) {
                Text(text = negativeButtonText.asDisplayString())
            }
        },
    ) {
        datePicker(controller.state)
    }
}
