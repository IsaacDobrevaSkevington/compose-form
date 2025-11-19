package com.idscodelabs.compose_form.form.fields.default.date

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.TextFieldValue
import com.idscodelabs.compose_form.form.fields.core.date.LocalFormDateFormatter
import com.idscodelabs.compose_form.form.fields.strings.asDisplayString
import kotlinx.datetime.TimeZone
import kotlinx.datetime.format
import kotlinx.datetime.toLocalDateTime
import kotlin.time.ExperimentalTime
import kotlin.time.Instant

@OptIn(ExperimentalTime::class)
@Composable
fun DefaultDatePickerDialog(
    state: DatePickerState,
    onValueChange: (TextFieldValue) -> Unit,
    positiveButtonText: Any = "Ok",
    negativeButtonText: Any = "Cancel",
    datePicker: @Composable ColumnScope.() -> Unit = {
        DatePicker(
            state = state,
            showModeToggle = false
        )
    },
    onDismissRequest: () -> Unit,
){
    val formatter = LocalFormDateFormatter.current
    DatePickerDialog(
        onDismissRequest = onDismissRequest,
        confirmButton = {
            TextButton(onClick = {
                val newValue =
                    state.selectedDateMillis?.let {
                        Instant.fromEpochMilliseconds(it)
                            .toLocalDateTime(TimeZone.UTC)
                            .date
                            .format(formatter)
                    } ?: ""
                onValueChange(
                    TextFieldValue(newValue, TextRange(newValue.length))
                )
                onDismissRequest()
            }) {
                Text(text = positiveButtonText.asDisplayString())
            }
        },
        dismissButton = {
            TextButton(onClick = onDismissRequest) {
                Text(text = negativeButtonText.asDisplayString())
            }
        }
    ) {
        datePicker()
    }
}