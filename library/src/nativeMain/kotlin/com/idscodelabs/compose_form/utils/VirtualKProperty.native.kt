package com.idscodelabs.compose_form.utils

import kotlin.reflect.KProperty
import kotlin.reflect.KType

actual class VirtualKProperty actual constructor(
    actual override val name: String,
) : KProperty<String> {
    override val returnType: KType
        get() = VirtualKProperty::name.returnType
}
