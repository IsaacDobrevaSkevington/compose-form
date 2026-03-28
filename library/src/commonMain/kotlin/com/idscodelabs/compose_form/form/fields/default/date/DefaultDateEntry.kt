package com.idscodelabs.compose_form.form.fields.default.date

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import com.idscodelabs.compose_form.form.fields.core.date.LocalFormDateFormatter
import com.idscodelabs.compose_form.form.fields.default.base.DefaultPickerTextEntry
import com.idscodelabs.compose_form.form.fields.default.base.PickerController
import com.idscodelabs.compose_form.form.icons.Icons
import com.idscodelabs.compose_form.form.model.FormBox
import com.idscodelabs.compose_form.form.model.setValue
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.atStartOfDayIn
import kotlinx.datetime.format
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
    entry: @Composable (
        controller: PickerController<DatePickerState>,
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
            trailingIconImage = Icons.DateRange,
        )
    },
    dialog: @Composable (
        controller: PickerController<DatePickerState>,
        onDatePicked: (LocalDate) -> Unit,
    ) -> Unit = { controller, onDatePicked ->
        DefaultDatePickerDialog(
            controller,
            onDatePicked = onDatePicked,
        )
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

    val datePickerController = remember(datePickerState) { PickerController(datePickerState) }
    val datePickerVisible by datePickerController.pickerVisible.collectAsState()
    Box(Modifier.primaryFocusable()) {
        entry(datePickerController, value, ::setValue)
    }

    if (datePickerVisible) {
        dialog(datePickerController) {
            setValue(it.format(dateFormatter))
        }
    }
}
