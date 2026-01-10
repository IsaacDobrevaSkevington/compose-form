package com.idscodelabs.compose_form.annotations.validators

import androidx.compose.runtime.Composable
import com.idscodelabs.compose_form.validators.core.Validator

@Target(AnnotationTarget.PROPERTY)
@MustBeDocumented
annotation class NotEmpty(val error: String = "Required")

@Composable
fun <Value> NotEmpty.validator(): Validator<Value> = NotEmptyValidator(error)

