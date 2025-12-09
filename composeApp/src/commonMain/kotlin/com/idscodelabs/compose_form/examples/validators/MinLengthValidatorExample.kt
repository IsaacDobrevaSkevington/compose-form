package com.idscodelabs.compose_form.examples.validators

import androidx.compose.runtime.Composable
import com.idscodelabs.compose_form.examples.fields.textfield.FormTextFieldExampleModel
import com.idscodelabs.compose_form.examples.helpers.ExampleForm
import com.idscodelabs.compose_form.examples.helpers.ExampleScreen
import com.idscodelabs.compose_form.form.fields.core.text.FormTextField
import com.idscodelabs.compose_form.form.fields.default.text.DefaultTextEntry
import com.idscodelabs.compose_form.validators.MinLengthValidator
import org.jetbrains.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun MinLengthValidatorExample() =
    ExampleScreen {
        ExampleForm(emptyModel = ::FormTextFieldExampleModel) {
            FormTextField(
                modelProperty = FormTextFieldExampleModel::value,
                initialValue = null,
                enabled = true,
                validator = MinLengthValidator(10),
                updateModel = { value = it },
            ) {
                DefaultTextEntry(hint = "Value")
            }
        }
    }
