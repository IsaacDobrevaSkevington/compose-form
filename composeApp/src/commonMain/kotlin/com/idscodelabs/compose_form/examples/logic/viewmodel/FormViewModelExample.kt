package com.idscodelabs.compose_form.examples.logic.viewmodel

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.idscodelabs.compose_form.examples.fields.textfield.FormTextFieldExampleModel
import com.idscodelabs.compose_form.examples.helpers.ExampleScreen
import com.idscodelabs.compose_form.form.core.ui.Form
import com.idscodelabs.compose_form.form.fields.core.text.FormTextField
import com.idscodelabs.compose_form.form.fields.default.text.DefaultTextEntry
import com.idscodelabs.compose_form.validators.NotEmptyValidator
import org.jetbrains.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun FormViewModelExample() =
    ExampleScreen {
        val viewModel =
            viewModel { FormViewModelExampleViewModel() }
        val uiState by viewModel.uiState.collectAsState()

        Box(Modifier.fillMaxSize()) {
            if (uiState.loading) {
                CircularProgressIndicator(Modifier.align(Alignment.Center))
            }
            if (uiState.text.isNullOrEmpty()) {
                Form(controller = viewModel) {
                    FormTextField(
                        enabled = !uiState.loading,
                        modelProperty = FormTextFieldExampleModel::value,
                        validator = NotEmptyValidator(),
                        updateModel = { value = it },
                    ) {
                        DefaultTextEntry(hint = "Value")
                    }
                    Button(
                        enabled = !uiState.loading,
                        modifier = Modifier.fillMaxWidth(),
                        onClick = viewModel::onSubmitPressed,
                    ) {
                        Text("Submit")
                    }
                }
            } else {
                Text("Result is ${uiState.text}")
            }
        }
    }
