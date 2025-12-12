package com.idscodelabs.compose_form.examples.logic.submission

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import com.idscodelabs.compose_form.examples.fields.textfield.FormTextFieldExampleModel
import com.idscodelabs.compose_form.examples.helpers.ExampleScreen
import com.idscodelabs.compose_form.form.core.controller.FormSubmissionResult
import com.idscodelabs.compose_form.form.core.ui.Form
import com.idscodelabs.compose_form.form.fields.core.text.FormTextField
import com.idscodelabs.compose_form.form.fields.default.text.DefaultTextEntry
import com.idscodelabs.compose_form.validators.NotEmptyValidator
import org.jetbrains.compose.ui.tooling.preview.Preview

fun <Model> FormSubmissionResult<Model>.handleErrors(success: (Model) -> Unit) =
    onSuccess(success)
        .onFailure { it.firstOrNull()?.focusRequester?.requestFocus() }
        .onError { it.printStackTrace() }

@OptIn(ExperimentalComposeUiApi::class)
@Preview
@Composable
fun ChainingSubmissionExtensionExample() =
    ExampleScreen {
        Form(emptyModel = ::FormTextFieldExampleModel) {
            FormTextField(
                modelProperty = FormTextFieldExampleModel::value,
                validator = NotEmptyValidator(),
                updateModel = { value = it },
            ) {
                DefaultTextEntry(hint = "Value")
            }
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    submit()
                        .handleErrors {
                            // Logic goes here
                        }.onFailure { println("Failed to submit") } // Add extra handlers
                },
            ) {
                Text("Submit")
            }
        }
    }
