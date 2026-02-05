package com.idscodelabs.compose_form.examples.logic.valuechange.viewmodel

import androidx.lifecycle.viewModelScope
import com.idscodelabs.compose_form.form.core.controller.FormControllerViewModel
import com.idscodelabs.compose_form.form.model.onFieldStringValueChanged
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class FormViewModelValueChangeExampleViewModel :
    FormControllerViewModel<FormViewModelValueChangeExampleModel>(::FormViewModelValueChangeExampleModel) {
    val uiState = MutableStateFlow(FormViewModelValueChangeExampleUiState())

    fun subscribeToAddressChanges() =
        viewModelScope.launch {
            field(FormViewModelValueChangeExampleModel::address).onFieldStringValueChanged(debounceMillis = 300) {
                uiState.update {
                    it.copy(loading = true)
                }
                val addressList =
                    try {
                        searchAddresses(this)
                    } catch (e: Throwable) {
                        // Handle Error
                        e.printStackTrace()
                        emptyList()
                    }
                uiState.update {
                    it.copy(loading = false, options = addressList)
                }
            }
        }

    private suspend fun searchAddresses(input: String?): List<String> {
        if (input.isNullOrBlank()) return emptyList()
        if (input.lastOrNull()?.isDigit() == true) return listOf(input)
        delay(1000)
        return (0..10).map {
            "$input $it"
        }
    }
}
