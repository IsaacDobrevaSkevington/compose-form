package com.idscodelabs.compose_form.annotations.validators

import com.idscodelabs.compose_form.validators.core.StringValidator

open class RegexValidator(
    private val pattern: Regex,
    private val error: Any = "Invalid format",
) : StringValidator {
    constructor(
        pattern: String,
        error: Any = "Invalid format",
    ) : this(pattern.toRegex(), error)

    override fun validate(value: String?): Any? {
        if (value.isNullOrBlank()) return null
        return if (pattern.matches(value)) {
            null
        } else {
            error
        }
    }
}
