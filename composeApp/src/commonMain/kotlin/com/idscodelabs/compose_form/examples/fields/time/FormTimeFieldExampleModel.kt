package com.idscodelabs.compose_form.examples.fields.time

import com.idscodelabs.compose_form.examples.helpers.ExampleModel
import kotlinx.datetime.LocalTime

data class FormTimeFieldExampleModel(
    override var value: LocalTime? = null,
) : com.idscodelabs.compose_form.examples.helpers.ExampleModel<LocalTime>
