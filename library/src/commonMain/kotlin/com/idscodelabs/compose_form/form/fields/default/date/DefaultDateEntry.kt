package com.idscodelabs.compose_form.form.fields.default.date

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import com.idscodelabs.compose_form.form.core.FormScope
import com.idscodelabs.compose_form.form.fields.core.date.LocalFormDateFormatter
import com.idscodelabs.compose_form.form.fields.default.text.DefaultTextEntry
import com.idscodelabs.compose_form.form.icons.Icons
import com.idscodelabs.compose_form.form.model.FormBox
import kotlinx.datetime.TimeZone
import kotlinx.datetime.atStartOfDayIn
import kotlin.time.ExperimentalTime

@OptIn(ExperimentalMaterial3Api::class, ExperimentalTime::class)
@Composable
fun FormBox<*, TextFieldValue>.DefaultDateEntry(
    modifier: Modifier = Modifier.fillMaxWidth(),
    hint: Any? = null,
    placeholder: Any? = null,
    isLast: Boolean = false,
    leadingIcon: (@Composable () -> Unit)? = null,
    datePickerState: DatePickerState = rememberDatePickerState(),
    allowTyping: Boolean = true,
    entry: @Composable FormBox<*, TextFieldValue>.(DatePickerController) -> Unit = {
        val enabled = enabled
        DefaultTextEntry(
            hint = hint,
            modifier =
                modifier.onFocusChanged { focusState ->
                    if (focusState.isFocused && !allowTyping && enabled) {
                        it.setPickerVisible(true)
                    }
                },
            icon =
                if (enabled) {
                    FormScope.IconParams(
                        Icons.DateRange,
                    ) {
                        it.setPickerVisible(true)
                    }
                } else {
                    null
                },
            placeholder = placeholder,
            isLast = isLast,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            leadingIcon = leadingIcon,
            readOnly = !(allowTyping && enabled),
        )
    },
    dialog: @Composable FormBox<*, TextFieldValue>.(DatePickerController) -> Unit = {
        DefaultDatePickerDialog(
            it.datePickerState,
            ::setValue,
        ) {
            it.setPickerVisible(false)
        }
    },
) {
    val dateFormatter = LocalFormDateFormatter.current

    FieldValueChangedEffect {
        if (text.isNotBlank()) {
            try {
                datePickerState.selectedDateMillis = dateFormatter.parse(text).atStartOfDayIn(TimeZone.UTC).toEpochMilliseconds()
            } catch (_: IllegalArgumentException) {
            }
        }
    }

    val datePickerController = remember(datePickerState) { DatePickerController(datePickerState) }
    val datePickerVisible by datePickerController.pickerVisible.collectAsState()
    entry(datePickerController)

    if (datePickerVisible) {
        dialog(datePickerController)
    }
}
