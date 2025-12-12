package com.idscodelabs.compose_form.validators

import com.idscodelabs.compose_form.form.core.controller.FormController
import com.idscodelabs.compose_form.validators.core.Validator
import kotlin.reflect.KProperty

fun <Value> FormController<*>.NotEmptyIfOtherPopulatedValidator(
    other: KProperty<*>,
    error: Any = "Required if ${other.name} is empty.",
) = Validator<Value> { _, stringRepresentation ->
    val otherFieldValue = field(other)?.getStringValue()
    if (otherFieldValue.isNullOrBlank()) {
        null
    } else if (stringRepresentation.isNullOrBlank()) {
        error
    } else {
        null
    }
}
