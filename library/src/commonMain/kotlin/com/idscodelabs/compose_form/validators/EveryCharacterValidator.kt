package com.idscodelabs.compose_form.validators

import com.idscodelabs.compose_form.validators.core.StringValidator

open class EveryCharacterValidator(
    val error: Any = "Invalid characters",
    val checker: (Char) -> Boolean,
) : StringValidator {
    override fun validate(value: String?): Any? {
        if (value == null) return null
        return if (value.all(checker)) null else error
    }
}

fun ((Char) -> Boolean).asValidator(error: Any = "Invalid characters"): StringValidator = EveryCharacterValidator(error, this)
