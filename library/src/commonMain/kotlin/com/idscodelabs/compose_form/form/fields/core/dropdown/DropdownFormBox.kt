package com.idscodelabs.compose_form.form.fields.core.dropdown

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.text.input.TextFieldValue
import com.idscodelabs.compose_form.form.fields.core.base.DisplayableOption
import com.idscodelabs.compose_form.form.fields.core.base.ListDisplayable
import com.idscodelabs.compose_form.form.fields.strings.asDisplayString
import com.idscodelabs.compose_form.form.model.FormBox

class DropdownFormBox<Model, Item>(
    val field: FormBox<Model, TextFieldValue>,
    val options: List<DisplayableOption<Item>>,
) : FormBox<Model, TextFieldValue>(field) {
    val currentItem: Item? @Composable get() {
        val value by this.field.collectValueAsState()
        return options
            .firstOrNull {
                it.label.asDisplayString() == value.text
            }?.item
    }
}
