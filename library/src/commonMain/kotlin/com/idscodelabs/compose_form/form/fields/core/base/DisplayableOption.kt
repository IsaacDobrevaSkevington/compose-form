package com.idscodelabs.compose_form.form.fields.core.base

/**
 * Class which contains ready-to-display [Item] for fields
 *
 * @param Item The type of item the field handles
 * @property item An instance of [Item] which can be selected
 * @property label The label for the [Item] in the UI
 * @constructor Create empty Displayable option
 *
 * @see com.idscodelabs.compose_form.form.fields.core.dropdown.FormDropdownField
 * @see com.idscodelabs.compose_form.form.fields.core.radio.FormRadioField
 */
data class DisplayableOption<Item>(
    val item: Item,
    val label: String,
)
