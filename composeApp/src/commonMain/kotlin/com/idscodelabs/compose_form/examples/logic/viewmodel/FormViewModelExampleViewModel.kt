package com.idscodelabs.compose_form.examples.logic.viewmodel

import androidx.lifecycle.viewModelScope
import com.idscodelabs.compose_form.examples.fields.textfield.FormTextFieldExampleModel
import com.idscodelabs.compose_form.form.core.FormScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class FormViewModelExampleViewModel : FormScope<FormTextFieldExampleModel>() {
    val uiState = MutableStateFlow(FormViewModelExampleUiState())

    fun onSubmitPressed() =
        submit {
            submissionSuccess(it)
        }

    fun submissionSuccess(model: FormTextFieldExampleModel) =
        viewModelScope.launch {
            uiState.update { it.copy(loading = true) }
            delay(1000)
            uiState.update { it.copy(loading = false) }
            // Some long-running work
            uiState.update {
                it.copy(text = model.value)
            }
        }
}
