package com.idscodelabs.compose_form.examples.logic.valuechange

import com.idscodelabs.compose_form.examples.helpers.ExampleModel
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalTime

data class FormValueChangeExampleModel(
    var option: FormValueChangeExampleOption? = null,
    var date: LocalDate? = null,
    var time: LocalTime? = null,
) : ExampleModel<Triple<FormValueChangeExampleOption?, LocalDate?, LocalTime?>> {
    override var value: Triple<FormValueChangeExampleOption?, LocalDate?, LocalTime?>? = null
        get() =
            Triple(
                option,
                date,
                time,
            )
}
