package com.idscodelabs.compose_form.validators.core

fun interface StringValidator : Validator<String> {
    override fun validate(
        value: String?,
        stringRepresentation: String?,
    ): Any? = validate(value)

    fun validate(value: String?): Any?
}
