package com.idscodelabs.compose_form.examples.validators

import androidx.compose.runtime.Composable
import com.idscodelabs.compose_form.examples.fields.textfield.FormTextFieldExampleModel
import com.idscodelabs.compose_form.examples.helpers.ExampleForm
import com.idscodelabs.compose_form.examples.helpers.ExampleScreen
import com.idscodelabs.compose_form.form.fields.core.text.FormTextField
import com.idscodelabs.compose_form.form.fields.default.text.DefaultTextEntry
import com.idscodelabs.compose_form.validators.MaxLengthValidator
import com.idscodelabs.compose_form.validators.MinLengthValidator
import com.idscodelabs.compose_form.validators.MultipleValidator
import com.idscodelabs.compose_form.validators.core.and
import org.jetbrains.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun MultipleValidatorExample() =
    ExampleScreen {
        ExampleForm(emptyModel = ::FormTextFieldExampleModel) {
            FormTextField(
                modelProperty = FormTextFieldExampleModel::value,
                initialValue = null,
                enabled = true,
                validator =
                    MultipleValidator(
                        MinLengthValidator(5),
                        MaxLengthValidator(10),
                    ),
                updateModel = { value = it },
            ) {
                DefaultTextEntry(hint = "Value")
            }
        }
    }

@Preview
@Composable
fun InfixMultipleValidatorExample() =
    ExampleScreen {
        ExampleForm(emptyModel = ::FormTextFieldExampleModel) {
            FormTextField(
                modelProperty = FormTextFieldExampleModel::value,
                initialValue = null,
                enabled = true,
                validator = MinLengthValidator(5) and MaxLengthValidator(10),
                updateModel = { value = it },
            ) {
                DefaultTextEntry(hint = "Value")
            }
        }
    }

@Preview
@Composable
fun OperatorMultipleValidatorExample() =
    ExampleScreen {
        ExampleForm(emptyModel = ::FormTextFieldExampleModel) {
            FormTextField(
                modelProperty = FormTextFieldExampleModel::value,
                initialValue = null,
                enabled = true,
                validator = MinLengthValidator(5) + MaxLengthValidator(10),
                updateModel = { value = it },
            ) {
                DefaultTextEntry(hint = "Value")
            }
        }
    }
