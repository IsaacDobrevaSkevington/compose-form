package com.idscodelabs.compose_form.form.fields.core.multiselect

import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.text.input.TextFieldValue
import com.idscodelabs.compose_form.form.fields.core.base.DisplayableOption
import com.idscodelabs.compose_form.form.fields.core.base.ListDisplayable
import com.idscodelabs.compose_form.form.model.FormBox

open class MultiselectFormBox<Model, Item : ListDisplayable>(
    field: FormBox<Model, TextFieldValue>,
    val options: List<DisplayableOption<Item>>,
    private val itemDelimiter: String,
) : FormBox<Model, TextFieldValue>(field) {
    @Composable
    fun isItemSelected(item: Item) = currentItems.any { it.key == item.key }

    val currentItems: List<Item> @Composable get() {
        val value by collectValueAsState()
        return value.text.toItemsList()
    }

    private fun String.toItemsList(): List<Item> =
        split(itemDelimiter).mapNotNull { itemString ->
            options.firstOrNull { it.label == itemString.trim() }?.item
        }

    private fun List<Item>.toItemString(): String =
        sortedBy { it.position }
            .mapNotNull { item ->
                options
                    .firstOrNull { it.item.key == item.key }
                    ?.label
                    ?.trim()
                    ?.ifBlank { null }
            }.joinToString(itemDelimiter)

    fun setSelected(items: List<Item>) {
        setValue(TextFieldValue(items.toItemString()))
    }

    fun add(item: Item) {
        val current = valueSnapshot.text.toItemsList().toMutableList()
        current.add(item)
        setValue(TextFieldValue(current.distinctBy { it.key }.toItemString()))
    }

    fun remove(item: Item) {
        val current = valueSnapshot.text.toItemsList().toMutableList()
        current.removeAll {
            it.key == item.key
        }
        setValue(TextFieldValue(current.distinctBy { it.key }.toItemString()))
    }
}
