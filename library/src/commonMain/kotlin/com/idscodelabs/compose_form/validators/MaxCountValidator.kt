package com.idscodelabs.compose_form.validators

import com.idscodelabs.compose_form.validators.core.Validator

class MaxCountValidator<T>(
    val maxCount: Int,
    val error: Any = "More than $maxCount options selected.",
) : Validator<List<T>> {
    override fun validate(
        value: List<T>?,
        stringRepresentation: String?,
    ): Any? {
        if (value == null) return null
        return if (value.size <= maxCount) null else error
    }
}
