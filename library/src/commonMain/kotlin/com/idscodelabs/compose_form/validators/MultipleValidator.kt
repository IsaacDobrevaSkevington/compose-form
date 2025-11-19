package com.idscodelabs.compose_form.validators

import com.idscodelabs.compose_form.validators.core.Validator

open class MultipleValidator(
    private vararg val validators: Validator?
) : Validator {
    override fun validate(
        s: String?,
        otherFieldValues: Map<String, String?>
    ): Any? =
        validators.mapNotNull { it }.firstNotNullOfOrNull {
            it.validate(s, otherFieldValues)
        }
}
