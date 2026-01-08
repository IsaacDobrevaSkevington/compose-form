package com.idscodelabs.compose_form.form.fields.core.date

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import com.idscodelabs.compose_form.form.core.controller.FormController
import com.idscodelabs.compose_form.form.fields.default.date.DatePickerController
import com.idscodelabs.compose_form.form.fields.default.date.DefaultDatePickerDialog
import com.idscodelabs.compose_form.form.fields.default.text.DefaultTextEntry
import com.idscodelabs.compose_form.form.icons.Icons
import com.idscodelabs.compose_form.form.model.FormBox
import com.idscodelabs.compose_form.utils.IconButton
import com.idscodelabs.compose_form.utils.updateModel
import com.idscodelabs.compose_form.validators.core.Validator
import kotlinx.datetime.LocalDate
import kotlin.reflect.KMutableProperty

@Composable
fun <Model> FormController<Model>.FormDateField(
    modelProperty: KMutableProperty<LocalDate?>,
    initialValue: LocalDate? = null,
    validator: Validator<LocalDate>? = null,
    enabled: Boolean = true,
    invalidDateMessage: Any = "Invalid date format",
    cleanDate: (String) -> String = { sanitizeDate(it) },
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
                            "Calendar Icon",
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
) = FormDateField(
    modelProperty,
    modelProperty.updateModel(),
    initialValue,
    validator,
    enabled,
    invalidDateMessage,
    cleanDate,
    hint,
    modifier,
    placeholder,
    isLast,
    leadingIcon,
    datePickerState,
    allowTyping,
    entry,
    dialog,
)
