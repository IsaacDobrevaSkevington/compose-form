package com.idscodelabs.compose_form.examples.fields.time

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import com.idscodelabs.compose_form.examples.fields.time.FormTimeFieldExampleModel
import com.idscodelabs.compose_form.examples.helpers.ExampleForm
import com.idscodelabs.compose_form.examples.helpers.ExampleScreen
import com.idscodelabs.compose_form.form.fields.core.time.FormTimeField
import com.idscodelabs.compose_form.form.fields.default.time.DefaultTimeEntry
import com.idscodelabs.compose_form.validators.NotEmptyValidator
import com.idscodelabs.compose_form.validators.TimeAfterValidator
import com.idscodelabs.compose_form.validators.TimeBeforeValidator
import kotlinx.datetime.LocalTime
import org.jetbrains.compose.ui.tooling.preview.Preview
import kotlin.time.ExperimentalTime

@OptIn(ExperimentalTime::class, ExperimentalMaterial3Api::class)
@Preview
@Composable
fun FormTimeFieldExample() =
    ExampleScreen {
        ExampleForm(emptyModel = ::FormTimeFieldExampleModel) {
            val min = LocalTime(5, 0, 0)
            val max = LocalTime(10, 0, 0)
            FormTimeField(
                modelProperty = FormTimeFieldExampleModel::value,
                validator =
                    TimeAfterValidator(min) +
                        TimeBeforeValidator(max) +
                        NotEmptyValidator(),
                updateModel = { value = it },
            ) {
                DefaultTimeEntry(
                    hint = "Time",
                    timePickerState = rememberTimePickerState(),
                )
            }
        }
    }
