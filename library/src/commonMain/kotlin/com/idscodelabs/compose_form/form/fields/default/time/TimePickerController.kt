package com.idscodelabs.compose_form.form.fields.default.time

import androidx.compose.material3.DatePickerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TimePickerState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
@OptIn(ExperimentalMaterial3Api::class)
data class TimePickerController(
    val timepickerState: TimePickerState,
) {

    val pickerVisible: MutableStateFlow<Boolean> = MutableStateFlow(false)
    fun setPickerVisible(visible: Boolean) {
        pickerVisible.update { visible }
    }
}