package com.idscodelabs.compose_form.form.fields.default.multiselect

import androidx.compose.material3.Checkbox
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.idscodelabs.compose_form.form.fields.core.base.DisplayableOption
import com.idscodelabs.compose_form.form.fields.core.base.ListDisplayable
import com.idscodelabs.compose_form.form.fields.core.multiselect.MultiselectFormBox

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun <Model, Item : ListDisplayable> MultiselectFormBox<Model, Item>.DefaultMultiselectMenuItem(
    item: DisplayableOption<Item>,
    selectedIndicator: @Composable MultiselectFormBox<Model, Item>.() -> Unit = {
        Checkbox(
            checked = isItemSelected(item.item),
            onCheckedChange = {
                if (it) add(item.item) else remove(item.item)
            },
        )
    },
    modifier: Modifier = Modifier,
) {
    val displayString = item.label
    val itemSelected = isItemSelected(item.item)
    DropdownMenuItem(
        leadingIcon = {
            selectedIndicator()
        },
        modifier = modifier,
        text = { Text(displayString) },
        onClick = {
            if (itemSelected) remove(item.item) else add(item.item)
        },
        contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding,
    )
}
