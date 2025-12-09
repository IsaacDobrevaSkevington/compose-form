package com.idscodelabs.compose_form.examples.validators

import androidx.compose.runtime.Composable
import com.idscodelabs.compose_form.examples.fields.textfield.FormTextFieldExampleModel
import com.idscodelabs.compose_form.examples.helpers.ExampleForm
import com.idscodelabs.compose_form.examples.helpers.ExampleScreen
import com.idscodelabs.compose_form.form.fields.core.text.FormTextField
import com.idscodelabs.compose_form.form.fields.default.text.DefaultTextEntry
import com.idscodelabs.compose_form.validators.MaxLengthValidator
import org.jetbrains.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun MaxLengthValidatorExample() =
    ExampleScreen {
        ExampleForm(emptyModel = ::FormTextFieldExampleModel) {
            FormTextField(
                modelProperty = FormTextFieldExampleModel::value,
                initialValue = null,
                enabled = true,
                validator = MaxLengthValidator(10),
                updateModel = { value = it },
            ) {
                DefaultTextEntry(hint = "Value")
            }
        }
    }
