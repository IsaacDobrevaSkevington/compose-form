package com.idscodelabs.compose_form.form.fields.core.time

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TimePickerState
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import com.idscodelabs.compose_form.form.core.controller.FormController
import com.idscodelabs.compose_form.form.fields.default.text.DefaultTextEntry
import com.idscodelabs.compose_form.form.fields.default.time.DefaultTimePickerDialog
import com.idscodelabs.compose_form.form.fields.default.time.TimePickerController
import com.idscodelabs.compose_form.form.icons.Icons
import com.idscodelabs.compose_form.form.model.FormBox
import com.idscodelabs.compose_form.utils.IconButton
import com.idscodelabs.compose_form.utils.updateModel
import com.idscodelabs.compose_form.validators.core.Validator
import kotlinx.datetime.LocalTime
import kotlin.reflect.KMutableProperty
import kotlin.time.ExperimentalTime

@OptIn(ExperimentalMaterial3Api::class, ExperimentalTime::class)
@Composable
fun <Model> FormController<Model>.FormTimeField(
    modelProperty: KMutableProperty<LocalTime?>,
    initialValue: LocalTime? = null,
    validator: Validator<LocalTime>? = null,
    enabled: Boolean = true,
    invalidTimeMessage: Any = "Invalid time format",
    cleanTime: (String) -> String = { sanitizeTime(it) },
    modifier: Modifier = Modifier.fillMaxWidth(),
    hint: Any? = null,
    placeholder: Any? = null,
    isLast: Boolean = false,
    leadingIcon: (@Composable () -> Unit)? = null,
    timePickerState: TimePickerState = rememberTimePickerState(),
    allowTyping: Boolean = true,
    entry: @Composable FormBox<*, TextFieldValue>.(TimePickerController) -> Unit = {
        DefaultTextEntry(
            hint = hint,
            modifier = modifier,
            trailingIcon =
                if (enabled) {
                    {
                        IconButton(Icons.Timer, "Clock Icon") {
                            it.setPickerVisible(true)
                        }
                    }
                } else {
                    null
                },
            placeholder = placeholder,
            isLast = isLast,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword),
            leadingIcon = leadingIcon,
            readOnly = !(allowTyping && enabled),
        )
    },
    dialog: @Composable FormBox<*, TextFieldValue>.(TimePickerController) -> Unit = {
        DefaultTimePickerDialog(
            it.timepickerState,
            ::setValue,
        ) {
            it.setPickerVisible(false)
        }
    },
) = FormTimeField(
    modelProperty = modelProperty,
    updateModel = modelProperty.updateModel(),
    initialValue = initialValue,
    validator = validator,
    enabled = enabled,
    invalidTimeMessage = invalidTimeMessage,
    cleanTime = cleanTime,
    modifier = modifier,
    hint = hint,
    leadingIcon = leadingIcon,
    timePickerState = timePickerState,
    allowTyping = allowTyping,
    entry = entry,
    dialog = dialog,
    placeholder = placeholder,
    isLast = isLast,
)
