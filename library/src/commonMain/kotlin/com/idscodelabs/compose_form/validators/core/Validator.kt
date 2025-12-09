package com.idscodelabs.compose_form.validators.core

fun interface Validator<Value> {
    fun validate(
        value: Value?,
        stringRepresentation: String?,
    ): Any?

    operator fun plus(other: Validator<Value>?) =
        Validator<Value> { value, stringRepresentation ->
            this.validate(value, stringRepresentation) ?: other?.validate(value, stringRepresentation)
        }
}

infix fun <Value> Validator<Value>?.and(other: Validator<Value>?) =
    Validator<Value> { value, stringRepresentation ->
        this?.validate(value, stringRepresentation) ?: other?.validate(value, stringRepresentation)
    }
