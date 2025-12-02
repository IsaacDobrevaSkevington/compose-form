package com.idscodelabs.compose_form.utils

import kotlin.reflect.KProperty

actual class VirtualKProperty actual constructor(
    actual override val name: String,
) : KProperty<String>
