package com.idscodelabs.compose_form.form.fields.default.time

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.LockClock
import androidx.compose.material.icons.outlined.PunchClock
import androidx.compose.material.icons.outlined.Timer
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TimePickerState
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import com.idscodelabs.compose_form.form.core.FormScope
import com.idscodelabs.compose_form.form.fields.core.base.AbstractFormFieldImplementationParameters
import com.idscodelabs.compose_form.form.fields.core.time.LocalFormTimeFormatter
import com.idscodelabs.compose_form.form.fields.default.text.DefaultTextEntry
import kotlin.time.ExperimentalTime

@ExperimentalMaterial3Api
@ExperimentalTime
@Composable
fun AbstractFormFieldImplementationParameters<TextFieldValue>.DefaultTimeEntry(
    modifier: Modifier = Modifier.fillMaxWidth(),
    hint: Any? = null,
    placeholder: Any? = null,
    isLast: Boolean = false,
    leadingIcon: (@Composable () -> Unit)? = null,
    timePickerState: TimePickerState = rememberTimePickerState(),
    entry: @Composable AbstractFormFieldImplementationParameters<TextFieldValue>.(TimePickerController) -> Unit = {
        DefaultTextEntry(
            hint = hint,
            modifier = modifier,
            icon =
                if (enabled) {
                    FormScope.IconParams(
                        Icons.Outlined.Timer,
                    ) {
                        it.setPickerVisible(true)
                    }
                } else {
                    null
                },
            placeholder = placeholder,
            isLast = isLast,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword),
            leadingIcon = leadingIcon,
        )
    },
    dialog: @Composable AbstractFormFieldImplementationParameters<TextFieldValue>.(TimePickerController) -> Unit = {
        DefaultTimePickerDialog(
            it.timepickerState,
            ::setValue,
        ) {
            it.setPickerVisible(false)
        }
    },
) {
    val timeFormatter = LocalFormTimeFormatter.current

    LaunchedEffect(value.text) {
        val text = value.text
        if (text.isNotBlank()) {
            try {
                timeFormatter.parse(text)
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
