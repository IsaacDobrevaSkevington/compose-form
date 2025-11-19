package com.idscodelabs.compose_form.form.fields.default.dropdown

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.idscodelabs.compose_form.form.fields.core.base.ListDisplayable
import com.idscodelabs.compose_form.form.core.FormScope
import com.idscodelabs.compose_form.form.fields.core.dropdown.DropdownFormFieldImplementationParameters


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun <Item : ListDisplayable> DropdownFormFieldImplementationParameters<Item>.DefaultAutocompleteFormDropdownEntry(
    textFieldModifier: Modifier = Modifier.fillMaxWidth(),
    exposedDropdownBoxModifier: Modifier = Modifier,
    exposedDropdownMenuModifier: Modifier =  Modifier,
    hint: Any? = null,
    placeholder: Any? = hint,
    isLast: Boolean = false,
    leadingIcon: (@Composable DropdownFormFieldImplementationParameters<Item>.() -> Unit)? = null,
    filterFunction: String.() -> Boolean = { startsWith(value.text, ignoreCase = true) },
    clearIcon: ((onClick: ()->Unit) -> FormScope.IconParams)? = null,
    expandIcon: (expanded: Boolean) -> FormScope.IconParams = {
        FormScope.IconParams(
            Icons.Filled.ArrowDropDown,
            if (it) 180f else 0f
        ) {}
    },
    menuItem: @Composable Item.(setExpanded: (Boolean)->Unit) -> Unit = {
        DefaultDropdownMenuItem(this, it)
    }
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
    clearIcon = clearIcon

)