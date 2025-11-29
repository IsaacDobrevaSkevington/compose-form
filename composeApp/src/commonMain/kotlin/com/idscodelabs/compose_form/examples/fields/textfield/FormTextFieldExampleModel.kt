package com.idscodelabs.compose_form.examples.fields.textfield

import com.idscodelabs.compose_form.examples.helpers.ExampleModel

data class FormTextFieldExampleModel(
    override var value: String? = null,
) : com.idscodelabs.compose_form.examples.helpers.ExampleModel<String>
