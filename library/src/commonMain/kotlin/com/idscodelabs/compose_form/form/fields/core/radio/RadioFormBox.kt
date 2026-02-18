package com.idscodelabs.compose_form.form.fields.core.radio

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.text.input.TextFieldValue
import com.idscodelabs.compose_form.form.fields.core.base.DisplayableOption
import com.idscodelabs.compose_form.form.fields.core.base.ListDisplayable
import com.idscodelabs.compose_form.form.model.FormBox

open class RadioFormBox<Model, Item : ListDisplayable>(
    field: FormBox<Model, Int>,
    val options: List<DisplayableOption<Item>>,
) : FormBox<Model, Int>(field) {
    /**
     * Get a stateful version of the currently selected item.
     *
     * This is different from [value] in that it produces an [Item] rather than the raw [Int] index
     * currently selected.
     */
    val currentItem: Item? @Composable get() {
        return value.takeIf { it != -1 }?.let {
            options.getOrNull(it)?.item
        }
    }
}

/**
 * Remember a FormBox as a radio form box
 *
 * @param Item The type of [ListDisplayable] this radio group displays
 * @param options A list of options displayed in the radio group
 */
@Composable
fun <Model, Item : ListDisplayable> FormBox<Model, Int>.rememberAsRadioFormBox(options: List<DisplayableOption<Item>>) =
    remember(this, options) {
        RadioFormBox(this, options)
    }
