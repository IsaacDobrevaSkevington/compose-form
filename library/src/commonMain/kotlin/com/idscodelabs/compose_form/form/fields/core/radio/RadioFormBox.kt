package com.idscodelabs.compose_form.form.fields.core.radio

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import com.idscodelabs.compose_form.form.fields.core.base.DisplayableOption
import com.idscodelabs.compose_form.form.fields.core.base.ListDisplayable
import com.idscodelabs.compose_form.form.model.FormBox

open class RadioFormBox<Model, Item : ListDisplayable>(
    field: FormBox<Model, Int>,
    val options: List<DisplayableOption<Item>>,
) : FormBox<Model, Int>(field) {
    val currentItem: Item? @Composable get() {
        return value.takeIf { it != -1 }?.let {
            options.getOrNull(it)?.item
        }
    }
}
