package com.idscodelabs.compose_form.utils

import kotlin.reflect.KProperty

expect class VirtualKProperty(
    name: String,
) : KProperty<String> {
    override val name: String
}
