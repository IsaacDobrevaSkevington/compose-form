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
import com.idscodelabs.compose_form.form.fields.core.base.FormFieldImplementation
import com.idscodelabs.compose_form.form.fields.default.date.DatePickerController
import com.idscodelabs.compose_form.form.fields.default.date.DefaultDateEntry
import com.idscodelabs.compose_form.form.fields.default.date.DefaultDatePickerDialog
import com.idscodelabs.compose_form.form.fields.default.text.DefaultTextEntry
import com.idscodelabs.compose_form.form.icons.Icons
import com.idscodelabs.compose_form.form.model.FormBox
import com.idscodelabs.compose_form.utils.IconButton
import com.idscodelabs.compose_form.validators.core.Validator
import kotlinx.datetime.LocalDate
import kotlin.reflect.KProperty

/**
 * A date form field
 *
 * @param Model The Model of the form
 * @param modelProperty The [KProperty] of the [Model] which this field updates.
 * @param initialValue The initial value for this field
 * @param enabled Whether the field is enabled
 * @param validator The [Validator] for this field
 * @param updateModel Function called which should set the correct property on the model. Most often should be `{ <variable> = it }`
 * @param invalidDateMessage The message to display if the date is in an invalid format
 * @param cleanDate A function which can be used to coerce the user's input into a date format
 * @param modifier The modifier to apply to the whole field
 * @param hint The hint for the text field - always displayed
 * @param placeholder The placeholder for the textfield - displayed when there is no input in the text field
 * @param isLast If this date field is the last field in the form
 * @param leadingIcon The icon to use at the start of the entry
 * @param datePickerState State of a date picker dialog to allow state hoisting
 * @param allowTyping If true, the box allows typing, otherwise if false, dates can only be selected using the dialog
 * @param entry The entry box to use for the field
 * @param dialog The dialog to use when picking dates
 * @see [FormFieldImplementation]
 * @see [LocalFormDateFormatter]
 * @see [sanitizeDate]
 * @see [DefaultDateEntry]
 * @sample com.idscodelabs.compose_form.examples.fields.date.FormDateFieldExample
 */
@Composable
fun <Model> FormController<Model>.FormDateField(
    modelProperty: KProperty<*>,
    updateModel: Model.(LocalDate?) -> Unit,
    initialValue: LocalDate? = null,
    validator: Validator<LocalDate>? = null,
    enabled: Boolean = true,
    cleanDate: (String) -> String = { sanitizeDate(it) },
    hint: Any? = null,
    modifier: Modifier = Modifier.fillMaxWidth(),
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
    invalidDateMessage: Any = "Invalid date format",
) = FormDateField(
    modelProperty,
    updateModel,
    initialValue,
    validator,
    enabled,
    invalidDateMessage,
    cleanDate,
    implementation = {
        DefaultDateEntry(
            modifier,
            hint,
            placeholder,
            isLast,
            leadingIcon,
            datePickerState,
            allowTyping,
            entry,
            dialog,
        )
    },
)
