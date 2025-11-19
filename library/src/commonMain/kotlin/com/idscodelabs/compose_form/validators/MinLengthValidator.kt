package com.idscodelabs.compose_form.validators

import com.idscodelabs.compose_form.validators.core.Validator
import org.jetbrains.compose.resources.StringResource

class MinLengthValidator(
    val minLength: Int,
    val error: Any = "Shorter than $minLength characters",
) : Validator {
    override fun validate(
        s: String?,
        otherFieldValues: Map<String, String?>
    ): Any? {
        if (s == null) return null
        return if (s.length >= minLength) null else error
    }
}
