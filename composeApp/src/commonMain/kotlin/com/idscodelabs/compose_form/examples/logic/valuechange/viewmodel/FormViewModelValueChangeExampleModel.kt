package com.idscodelabs.compose_form.examples.logic.valuechange.viewmodel

import com.idscodelabs.compose_form.examples.helpers.ExampleModel

data class FormViewModelValueChangeExampleModel(
    var address: String? = null,
) : ExampleModel<String> {
    override var value: String?
        get() = address
        set(value) {
            address = value
        }
}
