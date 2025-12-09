package com.idscodelabs.compose_form.examples.fields.custom.field

import com.idscodelabs.compose_form.examples.helpers.ExampleModel

data class CustomFormFieldExampleModel(
    override var value: String? = null,
) : ExampleModel<String>
