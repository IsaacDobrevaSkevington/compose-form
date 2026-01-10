package com.idscodelabs.compose_form.annotations.validators

import com.idscodelabs.compose_form.form.core.controller.FormController
import com.idscodelabs.compose_form.validators.core.Validator
import kotlin.reflect.KProperty

fun <Value> FormController<*>.NotEmptyIfOtherNotPopulatedValidator(
    other: KProperty<*>,
    error: Any = "Required if ${other.name} is empty.",
) = Validator<Value> { _, stringRepresentation ->
    val otherFieldValue = fieldSnapshot(other)?.getStringValue()
    if (!otherFieldValue.isNullOrBlank()) {
        null
    } else if (stringRepresentation.isNullOrBlank()) {
        error
    } else {
        null
    }
}
