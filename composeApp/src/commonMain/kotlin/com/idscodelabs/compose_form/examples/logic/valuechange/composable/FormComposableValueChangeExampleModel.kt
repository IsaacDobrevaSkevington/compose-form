package com.idscodelabs.compose_form.examples.logic.valuechange.composable

import com.idscodelabs.compose_form.examples.helpers.ExampleModel
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalTime

data class FormComposableValueChangeExampleModel(
    var option: FormComposableValueChangeExampleOption? = null,
    var date: LocalDate? = null,
    var time: LocalTime? = null,
) : ExampleModel<Triple<FormComposableValueChangeExampleOption?, LocalDate?, LocalTime?>> {
    override var value:
        Triple<FormComposableValueChangeExampleOption?, LocalDate?, LocalTime?>? = null
        get() =
            Triple(
                option,
                date,
                time,
            )
}
