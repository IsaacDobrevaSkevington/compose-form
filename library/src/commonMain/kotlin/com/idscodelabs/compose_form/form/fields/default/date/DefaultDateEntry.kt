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
import com.idscodelabs.compose_form.form.fields.core.date.LocalFormDateFormatter
import com.idscodelabs.compose_form.form.fields.default.text.DefaultTextEntry
import com.idscodelabs.compose_form.form.icons.Icons
import com.idscodelabs.compose_form.form.model.FormBox
import com.idscodelabs.compose_form.utils.IconButton
import kotlinx.datetime.TimeZone
import kotlinx.datetime.atStartOfDayIn
import kotlin.time.ExperimentalTime

/**
 * Default date entry
 *
 * @param modifier The modifier to apply to the whole field
 * @param hint The hint for the text field - always displayed
 * @param placeholder The placeholder for the textfield - displayed when there is no input in the text field
 * @param isLast If this date field is the last field in the form
 * @param leadingIcon The icon to use at the start of the entry
 * @param datePickerState State of a date picker dialog to allow state hoisting
 * @param allowTyping If true, the box allows typing, otherwise if false, dates can only be selected using the dialog
 * @param entry The entry box to use for the field
 * @param dialog The dialog to use when picking dates
 */
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
            trailingIcon =
                if (enabled) {
                    {
                        IconButton(
                            Icons.DateRange,
                            null,
                        ) {
                            it.setPickerVisible(true)
                        }
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
