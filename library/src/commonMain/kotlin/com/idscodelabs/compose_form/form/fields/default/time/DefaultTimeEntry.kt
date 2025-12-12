package com.idscodelabs.compose_form.form.fields.default.time

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TimePickerState
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import com.idscodelabs.compose_form.form.fields.core.time.LocalFormTimeFormatter
import com.idscodelabs.compose_form.form.fields.default.text.DefaultTextEntry
import com.idscodelabs.compose_form.form.icons.Icons
import com.idscodelabs.compose_form.form.model.FormBox
import com.idscodelabs.compose_form.utils.IconButton
import kotlin.time.ExperimentalTime

@ExperimentalMaterial3Api
@ExperimentalTime
@Composable
fun FormBox<*, TextFieldValue>.DefaultTimeEntry(
    modifier: Modifier = Modifier.fillMaxWidth(),
    hint: Any? = null,
    placeholder: Any? = null,
    isLast: Boolean = false,
    leadingIcon: (@Composable () -> Unit)? = null,
    timePickerState: TimePickerState = rememberTimePickerState(),
    allowTyping: Boolean = true,
    entry: @Composable FormBox<*, TextFieldValue>.(TimePickerController) -> Unit = {
        DefaultTextEntry(
            hint = hint,
            modifier = modifier,
            trailingIcon =
                if (enabled) {
                    {
                        IconButton(Icons.Timer, "Clock Icon") {
                            it.setPickerVisible(true)
                        }
                    }
                } else {
                    null
                },
            placeholder = placeholder,
            isLast = isLast,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword),
            leadingIcon = leadingIcon,
            readOnly = !(allowTyping && enabled),
        )
    },
    dialog: @Composable FormBox<*, TextFieldValue>.(TimePickerController) -> Unit = {
        DefaultTimePickerDialog(
            it.timepickerState,
            ::setValue,
        ) {
            it.setPickerVisible(false)
        }
    },
) {
    val timeFormatter = LocalFormTimeFormatter.current

    FieldValueChangedEffect {
        if (text.isNotBlank()) {
            try {
                val time = timeFormatter.parse(text)
                timePickerState.hour = time.hour
                timePickerState.minute = time.minute
            } catch (_: IllegalArgumentException) {
            }
        }
    }

    val timePickerController = remember(timePickerState) { TimePickerController(timePickerState) }
    val datePickerVisible by timePickerController.pickerVisible.collectAsState()
    entry(timePickerController)

    if (datePickerVisible) {
        dialog(timePickerController)
    }
}
