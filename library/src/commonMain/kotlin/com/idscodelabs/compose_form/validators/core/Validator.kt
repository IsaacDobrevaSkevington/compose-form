package com.idscodelabs.compose_form.validators.core

import com.idscodelabs.compose_form.validators.MultipleValidator

fun interface Validator<Value> {

    val order: Int get() = 0

    fun validate(
        value: Value?,
        stringRepresentation: String?,
    ): Any?

    operator fun plus(other: Validator<Value>?): Validator<Value> = MultipleValidator(
        this, other
    )

    infix fun and(other: Validator<Value>?) = plus(other)
}


