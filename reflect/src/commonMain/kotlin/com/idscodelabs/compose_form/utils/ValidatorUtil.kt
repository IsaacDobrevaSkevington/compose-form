package com.idscodelabs.compose_form.utils

import androidx.compose.runtime.Composable
import com.idscodelabs.compose_form.annotations.validators.AnnotationMapper
import com.idscodelabs.compose_form.validators.core.Validator
import kotlin.reflect.KProperty

@Composable
fun <Value : Any> KProperty<Value?>.validator(): Validator<Value>? {
    val validators =
        this.annotations.mapNotNull {
            AnnotationMapper.map<Value>(it)
        }
    return validators.ifEmpty { null }?.reduce(Validator<Value>::plus)
}
