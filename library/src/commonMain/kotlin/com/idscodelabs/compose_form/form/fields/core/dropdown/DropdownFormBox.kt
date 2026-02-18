package com.idscodelabs.compose_form.form.fields.core.dropdown

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.text.input.TextFieldValue
import com.idscodelabs.compose_form.form.fields.core.base.DisplayableOption
import com.idscodelabs.compose_form.form.fields.core.base.ListDisplayable
import com.idscodelabs.compose_form.form.model.FormBox

/**
 * [FormBox] specific to dropdowns, also holds a list of [DisplayableOption]s to allow use in the UI
 *
 * @param Model The Model of the Form
 * @param Item The type of Item to be displayed in the dropdown
 * @property field A base [FormBox]
 * @property options A list of [Item]s which can be selected from the UI
 * @constructor Create Dropdown form box
 */
open class DropdownFormBox<Model, Item : ListDisplayable>(
    field: FormBox<Model, TextFieldValue>,
    val options: List<DisplayableOption<Item>>,
) : FormBox<Model, TextFieldValue>(field) {
    /**
     * Get a stateful version of the currently selected item.
     *
     * This is different from [value] in that it produces an [Item] rather than the raw [TextFieldValue]
     * currently in the entry box.
     */
    val currentItem: Item? @Composable get() {
        val value by collectValueAsState()
        return options.firstOrNull { it.label == value.text }?.item
    }
}

/**
 * Remember a FormBox as a dropdown form box
 *
 * @param Item The type of [ListDisplayable] this dropdown displays
 * @param options A list of options displayed in the dropdown
 */
@Composable
fun <Model, Item : ListDisplayable> FormBox<Model, TextFieldValue>.rememberAsDropdownFormBox(options: List<DisplayableOption<Item>>) =
    remember(this, options) {
        DropdownFormBox(this, options)
    }
