package com.idscodelabs.compose_form.utils

import com.idscodelabs.compose_form.form.validators.annotations.AnnotationMapper
import com.idscodelabs.compose_form.validators.core.Validator
import kotlin.reflect.KProperty

fun <Value> KProperty<Value>.validator(): Validator<Value> {
    val validators = this.annotations.mapNotNull {
        AnnotationMapper.map<Value>(it)
    }
    return validators.reduce(Validator<Value>::plus)

}