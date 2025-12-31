package com.idscodelabs.compose_form.examples.logic.valuechange.viewmodel

import com.idscodelabs.compose_form.form.fields.core.base.ListDisplayable

data class FormViewModelValueChangeExampleUiState(
    val options: List<String> = emptyList(),
    val loading: Boolean = false,
) {
    val optionsList by lazy {
        options.mapIndexed { index, it ->
            ListDisplayable(it, it, index)
        }
    }
}
