package com.idscodelabs.compose_form.form.fields.default.dropdown

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import com.idscodelabs.compose_form.form.fields.core.base.DisplayableOption
import com.idscodelabs.compose_form.form.fields.core.base.ListDisplayable
import com.idscodelabs.compose_form.form.fields.core.dropdown.DropdownFormBox
import com.idscodelabs.compose_form.form.icons.Icons
import com.idscodelabs.compose_form.utils.IconButton

/**
 * Default autocomplete form dropdown entry
 *
 * @param textFieldModifier The modifier to apply to the text field of the autocomplete dropdown
 * @param exposedDropdownBoxModifier The modifier to apply to the overall exposed dropdown box
 * @param exposedDropdownMenuModifier The modifier to apply to the menu inside the dropdown box
 * @param hint The hint to display for in the text field
 * @param placeholder The placeholder to display when the text field is empty
 * @param isLast If this form field is the last field in the form
 * @param leadingIcon The leading icon in the text field
 * @param filterFunction Function to apply to filter options based on the currently entered value
 * @param clearIcon Icon to display to allow full clearing of the text field
 * @param expandIcon Icon to display which thw user can click to expand or collapse the dropdown menu
 * @param menuItem A display for the menu item
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun <Item : ListDisplayable> DropdownFormBox<*, Item>.DefaultAutocompleteFormDropdownEntry(
    textFieldModifier: Modifier = Modifier.fillMaxWidth(),
    exposedDropdownBoxModifier: Modifier = Modifier,
    exposedDropdownMenuModifier: Modifier = Modifier,
    hint: Any? = null,
    placeholder: Any? = hint,
    isLast: Boolean = false,
    leadingIcon: (@Composable DropdownFormBox<*, Item>.() -> Unit)? = null,
    filterFunction: (item: String, value: String) -> Boolean = { item, value -> item.startsWith(value, ignoreCase = true) },
    clearIcon: (@Composable DropdownFormBox<*, Item>.(onClick: () -> Unit) -> Unit)? = {
        IconButton(Icons.Close, "Clear Icon") { it() }
    },
    expandIcon: @Composable DropdownFormBox<*, Item>.(expanded: Boolean) -> Unit = {
        IconButton(Icons.ArrowDropDown, "Expand Icon", iconModifier = Modifier.rotate(if (it) 180f else 0f)) {}
    },
    menuItem: @Composable DropdownFormBox<*, Item>.(
        item: DisplayableOption<Item>,
        setExpanded: (Boolean) -> Unit,
    ) -> Unit = { item, setExpanded ->
        DefaultDropdownMenuItem(item, setExpanded)
    },
) = DefaultBaseFormDropdownEntry(
    textFieldModifier = textFieldModifier,
    exposedDropdownBoxModifier = exposedDropdownBoxModifier,
    exposedDropdownMenuModifier = exposedDropdownMenuModifier,
    hint = hint,
    placeholder = placeholder,
    isLast = isLast,
    readOnly = false,
    leadingIcon = leadingIcon,
    filterFunction = filterFunction,
    expandIcon = expandIcon,
    menuItem = menuItem,
    clearIcon = clearIcon,
)
