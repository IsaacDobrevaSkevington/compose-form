package com.idscodelabs.compose_form.form.fields.default.multiselect

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.idscodelabs.compose_form.form.fields.core.base.DisplayableOption
import com.idscodelabs.compose_form.form.fields.core.base.ListDisplayable

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun <Item : ListDisplayable> DefaultMultiselectMenuItem(
    item: DisplayableOption<Item>,
    isSelected: Boolean,
    selectedIndicator: @Composable (isSelected: Boolean, onClick: () -> Unit) -> Unit = { isSelected, onClick ->
        Checkbox(
            checked = isSelected,
            onCheckedChange = { onClick() },
        )
    },
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    val displayString = item.label
    DropdownMenuItem(
        leadingIcon = {
            selectedIndicator(isSelected, onClick)
        },
        modifier = modifier,
        text = { Text(displayString) },
        onClick = onClick,
        contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding,
    )
}
