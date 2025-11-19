package com.idscodelabs.compose_form.validators

import com.idscodelabs.compose_form.validators.core.Validator

class MaxLengthValidator(
    val maxLength: Int,
    val error: Any = "Longer than $maxLength characters",
) : Validator {
    override fun validate(
        s: String?,
        otherFieldValues: Map<String, String?>
    ): Any? {
        if (s == null) return null
        return if (s.length <= maxLength) null else error
    }
}
