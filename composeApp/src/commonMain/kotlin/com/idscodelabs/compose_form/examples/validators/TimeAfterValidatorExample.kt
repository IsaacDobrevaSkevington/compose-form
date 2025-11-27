package com.idscodelabs.compose_form.examples.validators

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import com.idscodelabs.compose_form.examples.fields.date.FormDateFieldExampleModel
import com.idscodelabs.compose_form.examples.fields.time.FormTimeFieldExampleModel
import com.idscodelabs.compose_form.examples.helpers.ExampleForm
import com.idscodelabs.compose_form.examples.helpers.ExampleScreen
import com.idscodelabs.compose_form.form.core.Form
import com.idscodelabs.compose_form.form.fields.core.date.FormDateField
import com.idscodelabs.compose_form.form.fields.core.date.LocalFormDateFormatter
import com.idscodelabs.compose_form.form.fields.core.text.FormTextField
import com.idscodelabs.compose_form.form.fields.core.time.FormTimeField
import com.idscodelabs.compose_form.form.fields.default.date.DefaultDateEntry
import com.idscodelabs.compose_form.form.fields.default.time.DefaultTimeEntry
import com.idscodelabs.compose_form.validators.DateAfterValidator
import com.idscodelabs.compose_form.validators.TimeAfterValidator
import com.idscodelabs.compose_form.validators.core.Validator
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalTime
import kotlinx.datetime.format.DateTimeFormat
import org.jetbrains.compose.ui.tooling.preview.Preview
import kotlin.time.ExperimentalTime

@OptIn(ExperimentalMaterial3Api::class, ExperimentalTime::class)
@Preview
@Composable
fun TimeAfterValidatorExample() =
    ExampleScreen {
        ExampleForm(emptyModel = ::FormTimeFieldExampleModel) {
            FormTimeField(
                modelProperty = FormTimeFieldExampleModel::value,
                initialValue = null,
                enabled = true,
                validator = TimeAfterValidator(LocalTime(5, 0, 0)),
                updateModel = { value = it },
            ) {
                DefaultTimeEntry(hint = "Value")
            }
        }
    }
