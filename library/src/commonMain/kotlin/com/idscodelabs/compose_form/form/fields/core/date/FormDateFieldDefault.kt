package com.idscodelabs.compose_form.form.fields.core.date

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import com.idscodelabs.compose_form.form.core.controller.FormController
import com.idscodelabs.compose_form.form.fields.core.base.FormFieldImplementation
import com.idscodelabs.compose_form.form.fields.default.base.DefaultPickerTextEntry
import com.idscodelabs.compose_form.form.fields.default.base.PickerController
import com.idscodelabs.compose_form.form.fields.default.date.DefaultDateEntry
import com.idscodelabs.compose_form.form.fields.default.date.DefaultDatePickerDialog
import com.idscodelabs.compose_form.form.icons.Icons
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
