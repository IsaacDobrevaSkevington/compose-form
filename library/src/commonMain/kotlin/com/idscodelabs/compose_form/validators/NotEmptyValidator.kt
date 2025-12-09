package com.idscodelabs.compose_form.validators

import com.idscodelabs.compose_form.validators.core.Validator

class NotEmptyValidator<Value>(
    private val error: Any = "Required",
) : Validator<Value> {
    override fun validate(
        value: Value?,
        stringRepresentation: String?,
    ): Any? =
        if (stringRepresentation.isNullOrBlank()) {
            error
        } else {
            null
        }
}
