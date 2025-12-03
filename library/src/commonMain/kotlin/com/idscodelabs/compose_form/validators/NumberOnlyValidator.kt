package com.idscodelabs.compose_form.validators

import com.idscodelabs.compose_form.validators.core.Validator

class NumberOnlyValidator(
    val error: Any = "Numbers only",
) : Validator {
    override fun validate(
        s: String?,
        otherFieldValues: Map<String, String?>,
    ): Any? {
        if (s == null) return null
        return if (s.all(Char::isDigit)) null else error
    }
}
