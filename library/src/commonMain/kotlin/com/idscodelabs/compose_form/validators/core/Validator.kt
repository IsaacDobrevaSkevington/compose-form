package com.idscodelabs.compose_form.validators.core

fun interface Validator {
    fun validate(
        s: String?,
        otherFieldValues: Map<String, String?>,
    ): Any?

    operator fun plus(other: Validator?) =
        Validator { s, otherFieldValues ->
            this.validate(s, otherFieldValues) ?: other?.validate(s, otherFieldValues)
        }
}

infix fun Validator?.and(other: Validator?) =
    Validator { s, otherFieldValues ->
        this?.validate(s, otherFieldValues) ?: other?.validate(s, otherFieldValues)
    }
