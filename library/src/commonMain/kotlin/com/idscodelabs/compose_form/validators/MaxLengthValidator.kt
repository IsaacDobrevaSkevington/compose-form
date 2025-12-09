package com.idscodelabs.compose_form.validators

import com.idscodelabs.compose_form.validators.core.StringValidator

class MaxLengthValidator(
    val maxLength: Int,
    val error: Any = "Longer than $maxLength characters",
) : StringValidator {
    override fun validate(value: String?): Any? {
        if (value == null) return null
        return if (value.length <= maxLength) null else error
    }
}
