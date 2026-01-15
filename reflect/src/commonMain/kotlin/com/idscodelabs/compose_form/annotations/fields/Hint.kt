package com.idscodelabs.compose_form.annotations.fields

@Target(AnnotationTarget.PROPERTY)
@MustBeDocumented
annotation class Hint(
    val value: String,
)
