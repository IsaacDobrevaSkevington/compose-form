package com.idscodelabs.compose_form.examples.validators

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.idscodelabs.compose_form.examples.fields.textfield.FormTextFieldExampleModel
import com.idscodelabs.compose_form.examples.helpers.ExampleForm
import com.idscodelabs.compose_form.examples.helpers.ExampleScreen
import com.idscodelabs.compose_form.form.fields.core.text.FormTextField
import com.idscodelabs.compose_form.form.fields.default.text.DefaultTextEntry
import com.idscodelabs.compose_form.validators.EveryCharacterValidator
import com.idscodelabs.compose_form.validators.asValidator

@Preview
@Composable
fun EveryCharacterValidatorExampleCustom() =
    ExampleScreen {
        ExampleForm(emptyModel = ::FormTextFieldExampleModel) {
            FormTextField(
                modelProperty = FormTextFieldExampleModel::value,
                initialValue = null,
                enabled = true,
                validator =
                    EveryCharacterValidator {
                        it.isLetterOrDigit()
                    },
                updateModel = { value = it },
            ) {
                DefaultTextEntry(hint = "Value")
            }
        }
    }

@Preview
@Composable
fun EveryCharacterValidatorExampleWithPredefinedFunction() =
    ExampleScreen {
        ExampleForm(emptyModel = ::FormTextFieldExampleModel) {
            FormTextField(
                modelProperty = FormTextFieldExampleModel::value,
                initialValue = null,
                enabled = true,
                validator = Char::isLetterOrDigit.asValidator(),
                updateModel = { value = it },
            ) {
                DefaultTextEntry(hint = "Value")
            }
        }
    }
