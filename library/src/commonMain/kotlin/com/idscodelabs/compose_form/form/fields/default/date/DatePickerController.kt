package com.idscodelabs.compose_form.form.fields.default.date

import androidx.compose.material3.DatePickerState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

data class DatePickerController(
    val datePickerState: DatePickerState,
) {
    val pickerVisible: MutableStateFlow<Boolean> = MutableStateFlow(false)

    fun setPickerVisible(visible: Boolean) {
        pickerVisible.update { visible }
    }
}
