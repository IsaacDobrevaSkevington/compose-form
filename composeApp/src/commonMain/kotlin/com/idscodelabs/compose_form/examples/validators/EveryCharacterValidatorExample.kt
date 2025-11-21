package com.idscodelabs.compose_form.examples.validators

import androidx.compose.runtime.Composable
import com.idscodelabs.compose_form.examples.fields.textfield.FormTextFieldExampleModel
import com.idscodelabs.compose_form.form.core.Form
import com.idscodelabs.compose_form.form.fields.core.text.FormTextField
import com.idscodelabs.compose_form.form.fields.default.text.DefaultTextEntry
import com.idscodelabs.compose_form.validators.EveryCharacterValidator
import com.idscodelabs.compose_form.validators.asValidator
import org.jetbrains.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun EveryCharacterValidatorExampleCustom() {
    Form(emptyModel = ::FormTextFieldExampleModel) {
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
fun EveryCharacterValidatorExampleWithPredefinedFunction() {
    Form(emptyModel = ::FormTextFieldExampleModel) {
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
