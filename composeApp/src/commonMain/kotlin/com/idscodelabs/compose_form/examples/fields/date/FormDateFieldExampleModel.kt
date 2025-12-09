package com.idscodelabs.compose_form.examples.fields.date

import com.idscodelabs.compose_form.examples.helpers.ExampleModel
import kotlinx.datetime.LocalDate

data class FormDateFieldExampleModel(
    override var value: LocalDate? = null,
) : ExampleModel<LocalDate>
