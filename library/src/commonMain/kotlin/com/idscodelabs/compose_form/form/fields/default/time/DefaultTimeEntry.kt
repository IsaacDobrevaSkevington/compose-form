package com.idscodelabs.compose_form.form.fields.default.time

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TimePickerState
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import com.idscodelabs.compose_form.form.fields.core.time.LocalFormTimeFormatter
import com.idscodelabs.compose_form.form.fields.default.base.DefaultPickerTextEntry
import com.idscodelabs.compose_form.form.fields.default.base.PickerController
import com.idscodelabs.compose_form.form.icons.Icons
import com.idscodelabs.compose_form.form.model.FormBox
import com.idscodelabs.compose_form.form.model.setValue
import kotlinx.datetime.LocalTime
import kotlinx.datetime.format
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
    entry: @Composable (
        controller: PickerController<TimePickerState>,
        value: TextFieldValue,
        setValue: (TextFieldValue) -> Unit,
    ) -> Unit = { controller, value, setValue ->
        DefaultPickerTextEntry(
            value = value,
            setValue = setValue,
            modifier = modifier,
            hint = hint,
            isLast = isLast,
            leadingIcon = leadingIcon,
            placeholder = placeholder,
            controller = controller,
            allowTyping = allowTyping,
            trailingIconImage = Icons.Timer,
        )
    },
    dialog: @Composable (
        controller: PickerController<TimePickerState>,
        onTimePicked: (LocalTime) -> Unit,
    ) -> Unit = { controller, onTimePicked ->
        DefaultTimePickerDialog(
            controller,
            onTimePicked = onTimePicked,
        )
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

    val timePickerController = remember(timePickerState) { PickerController(timePickerState) }
    val datePickerVisible by timePickerController.pickerVisible.collectAsState()
    Box(Modifier.primaryFocusable()) {
        entry(timePickerController, value, ::setValue)
    }

    if (datePickerVisible) {
        dialog(timePickerController) {
            setValue(it.format(timeFormatter))
        }
    }
}
