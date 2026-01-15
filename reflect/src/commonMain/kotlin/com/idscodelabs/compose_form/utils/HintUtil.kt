package com.idscodelabs.compose_form.utils

import com.idscodelabs.compose_form.annotations.fields.Hint
import kotlin.reflect.KProperty
import kotlin.reflect.full.findAnnotation

fun <Value> KProperty<Value>.hint(): String? = findAnnotation<Hint>()?.value
