package com.idscodelabs.compose_form.utils

import kotlin.reflect.KProperty

/**
 * Virtual KProperty
 *
 * Use this when a [KProperty] is required for a form field with a string name
 *
 * This is useful for dynamic forms, where fields may be defined in a configuration file
 * and need a string key
 */
expect class VirtualKProperty(
    name: String,
) : KProperty<String> {
    override val name: String
}
