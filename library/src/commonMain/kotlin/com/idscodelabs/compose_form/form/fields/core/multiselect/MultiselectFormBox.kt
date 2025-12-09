package com.idscodelabs.compose_form.form.fields.core.multiselect

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.text.input.TextFieldValue
import com.idscodelabs.compose_form.form.fields.core.base.DisplayableOption
import com.idscodelabs.compose_form.form.fields.core.base.ListDisplayable
import com.idscodelabs.compose_form.form.model.FormBox

/**
 * [FormBox] specific to multiselects, also holds a list of [DisplayableOption]s to allow use in the UI, and
 * functions to update the underlying state of the field
 *
 * @param Model The Model of the Form
 * @param Item The type of Item to be displayed in the dropdown
 * @property field A base [FormBox]
 * @property options A list of [Item]s which can be selected from the UI
 * @constructor Create Dropdown form box
 */
open class MultiselectFormBox<Model, Item : ListDisplayable>(
    field: FormBox<Model, TextFieldValue>,
    val options: List<DisplayableOption<Item>>,
    private val itemDelimiter: String,
) : FormBox<Model, TextFieldValue>(field) {
    /**
     * If an item is selected
     * When the items selected changes, this will be recalculated and cause recomposition
     *
     * @param item The item to check
     * @return true if [item] is selected, false otherwise
     */
    @Composable
    fun isItemSelected(item: Item) = currentItems.any { it.key == item.key }

    /**
     * Get a stateful version of the currently selected items.
     *
     * This is different from [value] in that it produces [Item]s rather than the raw [TextFieldValue]
     * currently in the entry box.
     */
    val currentItems: List<Item> @Composable get() {
        val value by collectValueAsState()
        return value.text.toItemsList()
    }

    /**
     * Convert a string displayed in the checkbox to the list of [Item]s
     */
    protected fun String.toItemsList(): List<Item> =
        split(itemDelimiter).mapNotNull { itemString ->
            options.firstOrNull { it.label == itemString.trim() }?.item
        }

    /**
     * Convert a list of [Item]s to a string for storage and display
     */
    protected fun List<Item>.toItemString(): String =
        sortedBy { it.position }
            .mapNotNull { item ->
                options
                    .firstOrNull { it.item.key == item.key }
                    ?.label
                    ?.trim()
                    ?.ifBlank { null }
            }.joinToString(itemDelimiter)

    /**
     * Set a list of [Item]s as selected
     * The previous value will be overwritten
     *
     * @param items The list of [Item]s to be selected
     */
    fun setSelected(items: List<Item>) {
        setValue(TextFieldValue(items.toItemString()))
    }

    /**
     * Add an [Item] to the selection
     *
     * @param item The [Item] to be added to the selection
     */
    fun add(item: Item) {
        val current = valueSnapshot.text.toItemsList().toMutableList()
        current.add(item)
        setValue(TextFieldValue(current.distinctBy { it.key }.toItemString()))
    }

    /**
     * Remove and [Item] to
     *
     * @param item
     */
    fun remove(item: Item) {
        val current = valueSnapshot.text.toItemsList().toMutableList()
        current.removeAll {
            it.key == item.key
        }
        setValue(TextFieldValue(current.distinctBy { it.key }.toItemString()))
    }
}
