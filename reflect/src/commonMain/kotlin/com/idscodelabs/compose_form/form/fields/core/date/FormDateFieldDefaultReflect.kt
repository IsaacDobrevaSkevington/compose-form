package com.idscodelabs.compose_form.form.fields.core.date

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import com.idscodelabs.compose_form.form.core.controller.FormController
import com.idscodelabs.compose_form.form.fields.default.base.DefaultPickerTextEntry
import com.idscodelabs.compose_form.form.fields.default.base.PickerController
import com.idscodelabs.compose_form.form.fields.default.date.DefaultDatePickerDialog
import com.idscodelabs.compose_form.form.icons.Icons
import com.idscodelabs.compose_form.utils.hint
import com.idscodelabs.compose_form.utils.updateModel
import com.idscodelabs.compose_form.utils.validator
import com.idscodelabs.compose_form.validators.core.Validator
import kotlinx.datetime.LocalDate
import kotlin.reflect.KMutableProperty

@Composable
fun <Model> FormController<Model>.FormDateField(
    modelProperty: KMutableProperty<LocalDate?>,
    initialValue: LocalDate? = null,
    validator: Validator<LocalDate>? = modelProperty.validator(),
    enabled: Boolean = true,
    cleanDate: (String) -> String = { sanitizeDate(it) },
    modifier: Modifier = Modifier.fillMaxWidth(),
    hint: Any? = modelProperty.hint(),
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
    invalidDateMessage: Any = "Invalid date format",
) = FormDateField(
    modelProperty = modelProperty,
    updateModel = modelProperty.updateModel(),
    initialValue = initialValue,
    validator = validator,
    enabled = enabled,
    invalidDateMessage = invalidDateMessage,
    cleanDate = cleanDate,
    hint = hint,
    modifier = modifier,
    placeholder = placeholder,
    isLast = isLast,
    leadingIcon = leadingIcon,
    datePickerState = datePickerState,
    allowTyping = allowTyping,
    entry = entry,
    dialog = dialog,
)
