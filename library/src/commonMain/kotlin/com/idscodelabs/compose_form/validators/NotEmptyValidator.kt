package com.idscodelabs.compose_form.validators

import com.idscodelabs.compose_form.validators.core.Validator

class NotEmptyValidator(
    private val error: Any = "Required",
) : Validator {
    override fun validate(
        s: String?,
        otherFieldValues: Map<String, String?>,
    ): Any? =
        if (s.isNullOrBlank()) {
            error
        } else {
            null
        }
}
