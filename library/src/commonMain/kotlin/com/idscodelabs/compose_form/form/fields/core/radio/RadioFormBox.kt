package com.idscodelabs.compose_form.form.fields.core.radio

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import com.idscodelabs.compose_form.form.fields.core.base.DisplayableOption
import com.idscodelabs.compose_form.form.fields.core.base.ListDisplayable
import com.idscodelabs.compose_form.form.model.FormBox

data class RadioFormBox<Model, Item : ListDisplayable>(
    val field: FormBox<Model, Int>,
    val options: List<DisplayableOption<Item>>,
) : FormBox<Model, Int>(field) {
    val currentItem: Item? @Composable get() {
        val value by this.field.collectValueAsState()
        return value.takeIf { it != -1 }?.let {
            options.getOrNull(it)?.item
        }
    }
}
