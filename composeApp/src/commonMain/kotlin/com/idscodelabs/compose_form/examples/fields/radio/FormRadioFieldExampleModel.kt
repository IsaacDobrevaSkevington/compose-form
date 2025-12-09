package com.idscodelabs.compose_form.examples.fields.radio

import com.idscodelabs.compose_form.examples.helpers.ExampleModel

data class FormRadioFieldExampleModel(
    override var value: FormRadioFieldExampleOption? = null,
) : ExampleModel<FormRadioFieldExampleOption>
