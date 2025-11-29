package com.idscodelabs.compose_form.examples.fields.switch

import com.idscodelabs.compose_form.examples.helpers.ExampleModel

data class FormSwitchExampleModel(
    override var value: Boolean? = null,
) : com.idscodelabs.compose_form.examples.helpers.ExampleModel<Boolean>
