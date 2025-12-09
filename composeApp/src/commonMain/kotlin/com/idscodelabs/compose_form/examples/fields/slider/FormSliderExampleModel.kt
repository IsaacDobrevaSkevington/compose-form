package com.idscodelabs.compose_form.examples.fields.slider

import com.idscodelabs.compose_form.examples.helpers.ExampleModel

data class FormSliderExampleModel(
    override var value: Int? = null,
) : ExampleModel<Int>
