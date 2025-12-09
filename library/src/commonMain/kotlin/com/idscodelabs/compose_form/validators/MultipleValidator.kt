package com.idscodelabs.compose_form.validators

import com.idscodelabs.compose_form.validators.core.Validator

open class MultipleValidator<Value>(
    private vararg val validators: Validator<Value>?,
) : Validator<Value> {
    override fun validate(
        value: Value?,
        stringRepresentation: String?,
    ): Any? =
        validators.mapNotNull { it }.firstNotNullOfOrNull {
            it.validate(value, stringRepresentation)
        }
}
