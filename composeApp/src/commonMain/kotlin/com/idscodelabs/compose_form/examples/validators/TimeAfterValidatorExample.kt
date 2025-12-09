package com.idscodelabs.compose_form.examples.validators

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import com.idscodelabs.compose_form.examples.fields.time.FormTimeFieldExampleModel
import com.idscodelabs.compose_form.examples.helpers.ExampleForm
import com.idscodelabs.compose_form.examples.helpers.ExampleScreen
import com.idscodelabs.compose_form.form.fields.core.time.FormTimeField
import com.idscodelabs.compose_form.form.fields.default.time.DefaultTimeEntry
import com.idscodelabs.compose_form.validators.TimeAfterValidator
import kotlinx.datetime.LocalTime
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
