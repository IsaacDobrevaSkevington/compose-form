package com.idscodelabs.compose_form.validators

import com.idscodelabs.compose_form.validators.core.Validator

class MaxCountValidator(
    val maxCount: Int,
    val error: Any = "More than $maxCount options selected.",
) : Validator<List<*>> {
    override fun validate(
        value: List<*>?,
        stringRepresentation: String?,
    ): Any? {
        if (value == null) return null
        return if (value.size <= maxCount) null else error
    }
}
