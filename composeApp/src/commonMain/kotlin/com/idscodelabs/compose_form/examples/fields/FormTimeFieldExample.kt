package com.idscodelabs.compose_form.examples.fields

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import com.idscodelabs.compose_form.examples.helpers.ExampleForm
import com.idscodelabs.compose_form.examples.helpers.ExampleModel
import com.idscodelabs.compose_form.examples.helpers.ExampleScreen
import com.idscodelabs.compose_form.form.core.Form
import com.idscodelabs.compose_form.form.fields.core.date.FormDateField
import com.idscodelabs.compose_form.form.fields.core.date.rememberDatePickerMinMaxState
import com.idscodelabs.compose_form.form.fields.core.time.FormTimeField
import com.idscodelabs.compose_form.form.fields.default.date.DefaultDateEntry
import com.idscodelabs.compose_form.form.fields.default.time.DefaultTimeEntry
import com.idscodelabs.compose_form.validators.DateAfterValidator
import com.idscodelabs.compose_form.validators.DateBeforeValidator
import com.idscodelabs.compose_form.validators.MultipleValidator
import com.idscodelabs.compose_form.validators.NotEmptyValidator
import com.idscodelabs.compose_form.validators.TimeAfterValidator
import com.idscodelabs.compose_form.validators.TimeBeforeValidator
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalTime
import org.jetbrains.compose.ui.tooling.preview.Preview
import kotlin.time.ExperimentalTime

data class FormTimeFieldExampleModel(
    override var value: LocalTime? = null,
) : ExampleModel<LocalTime>

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
                initialValue = null,
                enabled = true,
                validator =
                    NotEmptyValidator() +
                        TimeAfterValidator(min) +
                        TimeBeforeValidator(max),
                updateModel = { value = it },
            ) {
                DefaultTimeEntry(
                    hint = "Time",
                    timePickerState = rememberTimePickerState(),
                )
            }
        }
    }
