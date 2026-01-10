package com.idscodelabs.compose_form.annotations.validators

import com.idscodelabs.compose_form.validators.core.Validator
import kotlinx.coroutines.sync.Mutex

open class MultipleValidator<Value>(
    private vararg val validators: Validator<Value>?,
) : Validator<Value> {
    override fun validate(
        value: Value?,
        stringRepresentation: String?,
    ): Any? =
        validators.mapNotNull { it }.sortedBy { it.order }.firstNotNullOfOrNull {
            it.validate(value, stringRepresentation)
        }

    override fun plus(other: Validator<Value>?): Validator<Value> {
        if (other is MultipleValidator<Value>) {
            return MultipleValidator(
                *this.validators,
                *other.validators,
            )
        }
        return MultipleValidator(
            *this.validators,
            other,
        )
    }
}
