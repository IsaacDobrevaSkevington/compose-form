package com.idscodelabs.compose_form.validators

import com.idscodelabs.compose_form.validators.core.Validator

class EveryCharacterValidator(
    val error: Any = "Invalid characters",
    val checker: (Char) -> Boolean
) : Validator {
    override fun validate(
        s: String?,
        otherFieldValues: Map<String, String?>
    ): Any? {
        if (s == null) return null
        return if (s.all(checker)) null else error
    }
}

fun ((Char)->Boolean).asValidator(error: Any = "Invalid characters"): Validator = EveryCharacterValidator(error, this)
