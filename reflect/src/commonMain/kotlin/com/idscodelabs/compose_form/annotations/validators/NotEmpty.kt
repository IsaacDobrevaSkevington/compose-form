package com.idscodelabs.compose_form.annotations.validators

@Target(AnnotationTarget.PROPERTY)
@MustBeDocumented
annotation class NotEmpty(val error: String = "Required")
