package com.idscodelabs.compose_form.json.validator.models

import androidx.compose.runtime.Composable
import com.idscodelabs.compose_form.validators.core.Validator

interface ValidatorModel{

    @Composable
    fun <T> toValidator(): Validator<T>

}

@Suppress("UNCHECKED_CAST")
internal fun <NewValue> Validator<*>.cast(): Validator<NewValue> {
    return this as Validator<NewValue>
}