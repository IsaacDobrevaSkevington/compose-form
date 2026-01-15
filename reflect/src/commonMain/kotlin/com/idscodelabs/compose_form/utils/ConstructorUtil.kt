package com.idscodelabs.compose_form.utils

import com.idscodelabs.compose_form.utils.zeroArgConstructor
import kotlin.reflect.KClass
import kotlin.reflect.KFunction
import kotlin.reflect.KParameter

fun <T : Any> KClass<T>.zeroArgConstructor(): (() -> T)? =
    constructors
        .firstOrNull {
            it.parameters.all(KParameter::isOptional)
        }?.let { { it.callBy(emptyMap()) } }
