package com.idscodelabs.compose_form.annotations.validators

import com.idscodelabs.compose_form.validators.core.Validator

class MustBeTickedValidator(
    val error: Any = "Required",
) : Validator<Boolean> {
    override fun validate(
        value: Boolean?,
        stringRepresentation: String?,
    ): Any? =
        if (value == false) {
            error
        } else {
            null
        }
}
