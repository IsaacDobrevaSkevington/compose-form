package com.idscodelabs.compose_form.form.fields.default.dropdown

import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.TextFieldValue
import com.idscodelabs.compose_form.form.fields.core.base.DisplayableOption
import com.idscodelabs.compose_form.form.fields.core.base.ListDisplayable
import com.idscodelabs.compose_form.form.fields.core.dropdown.DropdownFormBox
import com.idscodelabs.compose_form.form.model.FormBox

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun <Item : ListDisplayable> DefaultDropdownMenuItem(
    item: DisplayableOption<Item>,
    modifier: Modifier = Modifier,
    onItemClick: (DisplayableOption<Item>) -> Unit,
) {
    val displayString = item.label
    val focusManager = LocalFocusManager.current
    DropdownMenuItem(
        modifier = modifier,
        text = { Text(displayString) },
        onClick = {
            onItemClick(item)
            focusManager.clearFocus()
        },
        contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding,
    )
}
