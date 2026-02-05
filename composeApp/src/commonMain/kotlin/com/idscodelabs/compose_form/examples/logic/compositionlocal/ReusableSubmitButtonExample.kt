package com.idscodelabs.compose_form.examples.logic.compositionlocal

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.idscodelabs.compose_form.examples.fields.textfield.FormTextFieldExampleModel
import com.idscodelabs.compose_form.examples.helpers.ExampleForm
import com.idscodelabs.compose_form.examples.helpers.ExampleScreen
import com.idscodelabs.compose_form.form.core.ui.Form
import com.idscodelabs.compose_form.form.core.ui.FormExtension
import com.idscodelabs.compose_form.form.fields.core.text.FormTextField
import com.idscodelabs.compose_form.form.fields.default.text.DefaultTextEntry
import com.idscodelabs.compose_form.validators.NotEmptyValidator
import org.jetbrains.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun ReusableSubmitButtonExample() =
    ExampleScreen {
        Form(emptyModel = ::FormTextFieldExampleModel) {
            FormTextField(
                modelProperty = FormTextFieldExampleModel::value,
                validator = NotEmptyValidator(),
                updateModel = { value = it },
                hint = "Value"
            )
            FormSubmitButton{it: FormTextFieldExampleModel ->
                // Use result here
            }
        }
    }


@Composable
fun <Model> FormSubmitButton(
    onSuccess: (Model) -> Unit,
) = FormExtension {
    Button(
        modifier = Modifier.fillMaxWidth(),
        onClick = {
            submit()
                .onSuccess(onSuccess)
                .onFailure { box ->
                    box.forEach { it.focusRequester.requestFocus() }
                }
        },
    ) {
        Text("Submit")
    }
}