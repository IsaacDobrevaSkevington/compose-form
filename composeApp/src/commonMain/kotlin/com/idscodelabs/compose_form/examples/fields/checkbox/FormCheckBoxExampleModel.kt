package com.idscodelabs.compose_form.examples.fields.checkbox

import com.idscodelabs.compose_form.examples.helpers.ExampleModel

data class FormCheckBoxExampleModel(
    override var value: Boolean? = null,
) : com.idscodelabs.compose_form.examples.helpers.ExampleModel<Boolean>
