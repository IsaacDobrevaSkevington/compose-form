package com.idscodelabs.compose_form.annotations.validators

import com.idscodelabs.compose_form.validators.core.StringValidator

class MinLengthValidator(
    val minLength: Int,
    val error: Any = "Shorter than $minLength characters",
) : StringValidator {
    override fun validate(value: String?): Any? {
        if (value == null) return null
        return if (value.length >= minLength) null else error
    }
}
