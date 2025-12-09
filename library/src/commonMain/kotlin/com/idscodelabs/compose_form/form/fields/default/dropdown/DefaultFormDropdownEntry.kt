package com.idscodelabs.compose_form.form.fields.default.dropdown

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.idscodelabs.compose_form.form.core.FormScope
import com.idscodelabs.compose_form.form.fields.core.base.DisplayableOption
import com.idscodelabs.compose_form.form.fields.core.base.ListDisplayable
import com.idscodelabs.compose_form.form.fields.core.dropdown.DropdownFormBox

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun <Model, Item : ListDisplayable> DropdownFormBox<Model, Item>.DefaultFormDropdownEntry(
    textFieldModifier: Modifier = Modifier.fillMaxWidth(),
    exposedDropdownBoxModifier: Modifier = Modifier,
    exposedDropdownMenuModifier: Modifier = Modifier,
    hint: Any? = null,
    placeholder: Any? = hint,
    isLast: Boolean = false,
    leadingIcon: (@Composable DropdownFormBox<Model, Item>.() -> Unit)? = null,
    clearIcon: (DropdownFormBox<Model, Item>.(onClick: () -> Unit) -> FormScope.IconParams)? = {
        FormScope.IconParams(
            Icons.Filled.Close,
            onClick = it,
        )
    },
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
    readOnly = true,
    leadingIcon = leadingIcon,
    filterFunction = null,
    expandIcon = expandIcon,
    menuItem = menuItem,
    clearIcon = clearIcon,
)
