package com.idscodelabs.compose_form.validators

import com.idscodelabs.compose_form.validators.core.Validator

class MustBeTickedValidator(
    val error: Any = "Required",
) : Validator {
    override fun validate(
        s: String?,
        otherFieldValues: Map<String, String?>,
    ): Any? =
        if (s == "false") {
            error
        } else {
            null
        }
}
