package com.idscodelabs.compose_form.utils

import kotlin.reflect.KMutableProperty

fun <Model, Value> KMutableProperty<Value>.updateModel(): Model.(Value) -> Unit =
    {
        setter.call(this, it)
    }
