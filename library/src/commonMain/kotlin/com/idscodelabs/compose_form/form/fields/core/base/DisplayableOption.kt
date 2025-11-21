package com.idscodelabs.compose_form.form.fields.core.base

/**
 * Interface for objects that can be displayed in a list such as a picker or spinner.
 */
data class DisplayableOption<Item>(
    val item: Item,
    val label: String,
)
