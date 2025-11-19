package com.idscodelabs.compose_form.form.fields.core.base

/**
 * Interface for objects that can be displayed in a list such as a picker or spinner.
 */
interface ListDisplayable {
    val key: Any
    val position: Int get() = 0
    val label: Any
}
