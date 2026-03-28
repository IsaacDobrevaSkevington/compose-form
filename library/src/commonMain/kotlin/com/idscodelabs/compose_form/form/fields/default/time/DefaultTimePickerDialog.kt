package com.idscodelabs.compose_form.form.fields.default.time

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import com.idscodelabs.compose_form.form.fields.default.base.PickerController
import com.idscodelabs.compose_form.form.fields.strings.asDisplayString
import kotlinx.datetime.LocalTime
import kotlin.time.ExperimentalTime

@OptIn(ExperimentalTime::class, ExperimentalMaterial3Api::class)
@Composable
fun DefaultTimePickerDialog(
    controller: PickerController<TimePickerState>,
    positiveButtonText: Any = "Ok",
    negativeButtonText: Any = "Cancel",
    timePicker: @Composable (state: TimePickerState) -> Unit = {
        TimePicker(state = it)
    },
    title: @Composable () -> Unit = { Text("Choose Time") },
    onTimePicked: (LocalTime) -> Unit,
) {
    TimePickerDialog(
        title = title,
        onDismissRequest = {
            controller.setPickerVisible(false)
        },
        confirmButton = {
            TextButton(onClick = {
                val newValue =
                    LocalTime(controller.state.hour, controller.state.minute)
                onTimePicked(newValue)
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
        timePicker(controller.state)
    }
}
