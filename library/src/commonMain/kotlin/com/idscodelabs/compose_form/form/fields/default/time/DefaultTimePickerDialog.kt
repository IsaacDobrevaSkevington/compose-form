package com.idscodelabs.compose_form.form.fields.default.time

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.TextFieldValue
import com.idscodelabs.compose_form.form.fields.core.time.LocalFormTimeFormatter
import com.idscodelabs.compose_form.form.fields.strings.asDisplayString
import kotlinx.datetime.LocalTime
import kotlinx.datetime.format
import kotlin.time.ExperimentalTime

@OptIn(ExperimentalTime::class, ExperimentalMaterial3Api::class)
@Composable
fun DefaultTimePickerDialog(
    state: TimePickerState,
    onValueChange: (TextFieldValue) -> Unit,
    positiveButtonText: Any = "Ok",
    negativeButtonText: Any = "Cancel",
    timePicker: @Composable () -> Unit = {
        TimePicker(state = state)
    },
    title: @Composable ()-> Unit = {Text("Choose Time")},
    onDismissRequest: () -> Unit,
){
    val timeFormat = LocalFormTimeFormatter.current
    TimePickerDialog(
        title = title,
        onDismissRequest = onDismissRequest,
        confirmButton = {
            TextButton(onClick = {
                val newValue = LocalTime(state.hour, state.minute).format(
                    timeFormat
                )
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
        timePicker()
    }
}