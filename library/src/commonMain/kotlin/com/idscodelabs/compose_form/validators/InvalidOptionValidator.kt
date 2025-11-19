package com.idscodelabs.compose_form.validators

import com.idscodelabs.compose_form.validators.core.Validator

class InvalidOptionValidator(
    val possibleOptions: List<String>,
    val error: Any = "Invalid Option Selected"
) : Validator {
    override fun validate(
        s: String?,
        otherFieldValues: Map<String, String?>
    ): Any? =
        if (s.isNullOrBlank() || possibleOptions.any { it.equals(s, true) }) {
            null
        } else {
             error
        }
}
