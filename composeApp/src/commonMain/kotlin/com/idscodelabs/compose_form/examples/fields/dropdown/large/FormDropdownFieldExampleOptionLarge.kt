package com.idscodelabs.compose_form.examples.fields.dropdown.large

import com.idscodelabs.compose_form.form.fields.core.base.ListDisplayable

data class FormDropdownFieldExampleOptionLarge(
    override val key: Any,
    override val label: Any,
    override val position: Int,
) : ListDisplayable {
    override fun toString(): String = label.toString()

    companion object {
        val options =
            (0..1000).map {
                FormDropdownFieldExampleOptionLarge(
                    it.toString(),
                    "Option $it",
                    it,
                )
            }
    }
}
