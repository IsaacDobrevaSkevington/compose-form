package com.idscodelabs.compose_form.examples.validators

import androidx.compose.runtime.Composable
import com.idscodelabs.compose_form.examples.fields.textfield.FormTextFieldExampleModelDouble
import com.idscodelabs.compose_form.examples.helpers.ExampleForm
import com.idscodelabs.compose_form.examples.helpers.ExampleScreen
import com.idscodelabs.compose_form.form.fields.core.text.FormTextField
import com.idscodelabs.compose_form.form.fields.default.text.DefaultTextEntry
import com.idscodelabs.compose_form.validators.NotEmptyIfOtherPopulatedValidator
import org.jetbrains.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun NotEmptyIfOtherPopulatedValidatorExample() =
    ExampleScreen {
        ExampleForm(emptyModel = ::FormTextFieldExampleModelDouble) {
            FormTextField(
                modelProperty = FormTextFieldExampleModelDouble::value1,
                initialValue = null,
                enabled = true,
                validator = null,
                updateModel = { value1 = it },
            ) {
                DefaultTextEntry(hint = "Value 1")
            }
            FormTextField(
                modelProperty = FormTextFieldExampleModelDouble::value2,
                initialValue = null,
                enabled = true,
                validator = NotEmptyIfOtherPopulatedValidator(FormTextFieldExampleModelDouble::value1),
                updateModel = { value2 = it },
            ) {
                DefaultTextEntry(hint = "Value 2")
            }
        }
    }
