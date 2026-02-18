package com.idscodelabs.compose_form.validators

import com.idscodelabs.compose_form.validators.core.Validator

class MinCountValidator(
    val minCount: Int,
    val error: Any = "Fewer than $minCount options selected.",
) : Validator<List<*>> {
    override fun validate(
        value: List<*>?,
        stringRepresentation: String?,
    ): Any? {
        if (value == null) return null
        return if (value.size >= minCount) null else error
    }
}
