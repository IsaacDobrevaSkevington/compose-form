package com.idscodelabs.compose_form.validators

import com.idscodelabs.compose_form.validators.core.Validator

open class RegexValidator(
    private val pattern: Regex,
    private val error: Any = "Invalid format",
) : Validator {
    constructor(
        pattern: String,
        error: Any = "Invalid format",
    ) : this(pattern.toRegex(), error)

    override fun validate(
        s: String?,
        otherFieldValues: Map<String, String?>,
    ): Any? {
        if (s.isNullOrBlank()) return null
        return if (pattern.matches(s)) {
            null
        } else {
            error
        }
    }
}
