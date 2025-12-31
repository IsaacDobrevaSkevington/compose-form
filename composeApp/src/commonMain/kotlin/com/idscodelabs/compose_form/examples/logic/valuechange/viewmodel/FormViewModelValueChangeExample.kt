package com.idscodelabs.compose_form.examples.logic.valuechange.viewmodel

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.idscodelabs.compose_form.examples.fields.textfield.FormTextFieldExampleModel
import com.idscodelabs.compose_form.examples.helpers.ExampleForm
import com.idscodelabs.compose_form.examples.helpers.ExampleScreen
import com.idscodelabs.compose_form.form.core.ui.Form
import com.idscodelabs.compose_form.form.fields.core.dropdown.FormDropdownField
import com.idscodelabs.compose_form.form.fields.core.suggestion.FormSuggestionField
import com.idscodelabs.compose_form.form.fields.core.text.FormTextField
import com.idscodelabs.compose_form.form.fields.default.dropdown.FormAutocompleteField
import com.idscodelabs.compose_form.form.fields.default.text.DefaultTextEntry
import com.idscodelabs.compose_form.validators.NotEmptyValidator
import org.jetbrains.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun FormViewModelValueChangeExample() =
    ExampleScreen {
        val viewModel =
            viewModel { FormViewModelValueChangeExampleViewModel() }
        val uiState by viewModel.uiState.collectAsState()

        DisposableEffect(Unit) {
            val observerJob = viewModel.subscribeToAddressChanges()
            onDispose {
                observerJob.cancel()
            }
        }

        Column(Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
            if (uiState.loading) {
                LinearProgressIndicator(Modifier.fillMaxWidth())
            }
            ExampleForm(controller = viewModel) {
                FormAutocompleteField(
                    enabled = !uiState.loading,
                    modelProperty = FormViewModelValueChangeExampleModel::address,
                    validator = NotEmptyValidator(),
                    options = uiState.optionsList,
                    updateModel = { address = it?.label?.toString() },
                    hint = "Address",
                )
            }
        }
    }
