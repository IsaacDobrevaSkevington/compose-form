package com.idscodelabs.compose_form.examples.reflect.annotations

import com.idscodelabs.compose_form.validators.core.Validator

class EverySecondCharOneValidator : Validator<String> {
    override fun validate(
        value: String?,
        stringRepresentation: String?,
    ): Any? {
        val valid =
            value?.mapIndexed(::Pair)?.all { (index, c) ->
                index % 2 != 1 || c == '1'
            } ?: return null

        return if (valid) {
            null
        } else {
            "Every second character must be '1'"
        }
    }
}
