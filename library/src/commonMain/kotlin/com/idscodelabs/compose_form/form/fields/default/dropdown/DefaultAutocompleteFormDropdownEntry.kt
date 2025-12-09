package com.idscodelabs.compose_form.form.fields.default.dropdown

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.idscodelabs.compose_form.form.core.FormScope
import com.idscodelabs.compose_form.form.fields.core.base.DisplayableOption
import com.idscodelabs.compose_form.form.fields.core.base.ListDisplayable
import com.idscodelabs.compose_form.form.fields.core.dropdown.DropdownFormBox

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun <Model, Item : ListDisplayable> DropdownFormBox<Model, Item>.DefaultAutocompleteFormDropdownEntry(
    textFieldModifier: Modifier = Modifier.fillMaxWidth(),
    exposedDropdownBoxModifier: Modifier = Modifier,
    exposedDropdownMenuModifier: Modifier = Modifier,
    hint: Any? = null,
    placeholder: Any? = hint,
    isLast: Boolean = false,
    leadingIcon: (@Composable DropdownFormBox<Model, Item>.() -> Unit)? = null,
    filterFunction: (item: String, value: String) -> Boolean = { item, value -> item.startsWith(value, ignoreCase = true) },
    clearIcon: (DropdownFormBox<Model, Item>.(onClick: () -> Unit) -> FormScope.IconParams)? = null,
    expandIcon: DropdownFormBox<Model, Item>.(expanded: Boolean) -> FormScope.IconParams = {
        FormScope.IconParams(
            Icons.Filled.ArrowDropDown,
            if (it) 180f else 0f,
        ) {}
    },
    menuItem: @Composable DropdownFormBox<Model, Item>.(item: DisplayableOption<Item>, setExpanded: (Boolean) -> Unit) -> Unit = {item, setExpanded ->
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
