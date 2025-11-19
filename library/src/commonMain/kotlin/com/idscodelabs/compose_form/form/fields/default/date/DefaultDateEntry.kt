package com.idscodelabs.compose_form.form.fields.default.date

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import com.idscodelabs.compose_form.form.core.FormScope
import com.idscodelabs.compose_form.form.fields.core.base.AbstractFormFieldImplementationParameters
import com.idscodelabs.compose_form.form.fields.core.base.FormFieldImplementationParameters
import com.idscodelabs.compose_form.form.fields.core.date.LocalFormDateFormatter
import com.idscodelabs.compose_form.form.fields.default.text.DefaultTextEntry
import kotlinx.datetime.TimeZone
import kotlinx.datetime.atStartOfDayIn
import kotlin.time.ExperimentalTime

@OptIn(ExperimentalMaterial3Api::class, ExperimentalTime::class)
@Composable
fun AbstractFormFieldImplementationParameters<TextFieldValue>.DefaultDateEntry(
    modifier: Modifier = Modifier.fillMaxWidth(),
    hint: Any? = null,
    placeholder: Any? = null,
    isLast: Boolean = false,
    leadingIcon: (@Composable () -> Unit)? = null,
    datePickerState: DatePickerState = rememberDatePickerState(),
    entry: @Composable AbstractFormFieldImplementationParameters<TextFieldValue>.(DatePickerController) -> Unit = {
        DefaultTextEntry(
            hint = hint,
            modifier = modifier,
            icon = if (enabled) {
                FormScope.IconParams(
                    Icons.Filled.DateRange
                ) {
                    it.setPickerVisible(true)
                }
            } else {
                null
            },
            placeholder = placeholder,
            isLast = isLast,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            leadingIcon = leadingIcon
        )
    },
    dialog: @Composable AbstractFormFieldImplementationParameters<TextFieldValue>.(DatePickerController)->Unit = {
        DefaultDatePickerDialog(
            it.datePickerState,
            ::setValue
        ){
            it.setPickerVisible(false)
        }
    }
) {

    val dateFormatter = LocalFormDateFormatter.current

    LaunchedEffect(value.text) {
        val text = value.text
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