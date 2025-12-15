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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun <Item : ListDisplayable> DropdownFormBox<*, Item>.DefaultFormDropdownEntry(
    textFieldModifier: Modifier = Modifier.fillMaxWidth(),
    exposedDropdownBoxModifier: Modifier = Modifier,
    exposedDropdownMenuModifier: Modifier = Modifier,
    hint: Any? = null,
    placeholder: Any? = hint,
    isLast: Boolean = false,
    leadingIcon: (@Composable DropdownFormBox<*, Item>.() -> Unit)? = null,
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
    readOnly = true,
    leadingIcon = leadingIcon,
    filterFunction = null,
    expandIcon = expandIcon,
    menuItem = menuItem,
    clearIcon = clearIcon,
)
