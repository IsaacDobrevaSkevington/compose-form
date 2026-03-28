package com.idscodelabs.compose_form.form.fields.default.base

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

data class PickerController<T>(
    val state: T,
) {
    val pickerVisible: MutableStateFlow<Boolean> = MutableStateFlow(false)

    fun setPickerVisible(visible: Boolean) {
        pickerVisible.update { visible }
    }
}
