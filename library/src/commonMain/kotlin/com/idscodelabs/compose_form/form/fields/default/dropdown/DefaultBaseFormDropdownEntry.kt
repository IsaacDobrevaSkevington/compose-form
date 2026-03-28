package com.idscodelabs.compose_form.form.fields.default.dropdown

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuAnchorType
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.text.input.TextFieldValue
import com.idscodelabs.compose_form.form.fields.core.base.DisplayableOption
import com.idscodelabs.compose_form.form.fields.core.base.ListDisplayable
import com.idscodelabs.compose_form.form.fields.core.dropdown.DropdownFormBox
import com.idscodelabs.compose_form.form.fields.default.text.DefaultTextEntry
import com.idscodelabs.compose_form.form.fields.strings.asDisplayString
import com.idscodelabs.compose_form.form.icons.Icons
import com.idscodelabs.compose_form.form.model.setValue
import com.idscodelabs.compose_form.utils.IconButton

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun <Item : ListDisplayable> DropdownFormBox<*, Item>.DefaultBaseFormDropdownEntry(
    textFieldModifier: Modifier = Modifier.fillMaxWidth(),
    exposedDropdownBoxModifier: Modifier = Modifier,
    exposedDropdownMenuModifier: Modifier = Modifier,
    hint: Any? = null,
    placeholder: Any? = hint,
    isLast: Boolean = false,
    readOnly: Boolean = false,
    leadingIcon: (@Composable () -> Unit)? = null,
    filterFunction: ((item: String, value: String) -> Boolean)? = null,
    clearIcon: (@Composable (onClick: () -> Unit) -> Unit)? = {
        IconButton(Icons.Close, "Clear Icon") { it() }
    },
    expandIcon: @Composable (expanded: Boolean) -> Unit = {
        IconButton(Icons.ArrowDropDown, "Expand Icon", iconModifier = Modifier.rotate(if (it) 180f else 0f)) {}
    },
    menuItem: @Composable (
        item: DisplayableOption<Item>,
        onItemClick: (DisplayableOption<Item>) -> Unit,
    ) -> Unit = { item, onItemClick ->
        DefaultDropdownMenuItem(item, onItemClick = onItemClick)
    },
) {
    val currentValue = value.text
    val filteredOptions =
        remember(options, filterFunction, currentValue) {
            filterFunction?.let {
                options.filter {
                    filterFunction(it.label, currentValue)
                }
            } ?: options
        }

    val (allowExpanded, setExpanded) = remember { mutableStateOf(false) }
    val expanded = allowExpanded && filteredOptions.isNotEmpty()

    val enabled by collectEnabledAsState()
    val lazyDropdownScope = rememberLazyDropdownScope()
    ExposedDropdownMenuBox(
        modifier = exposedDropdownBoxModifier.lazyDropdownBox(lazyDropdownScope),
        expanded = expanded,
        onExpandedChange = {
            if (enabled) {
                setExpanded(it)
            } else {
                setExpanded(false)
            }
        },
    ) {
        DefaultTextEntry(
            hint = hint,
            modifier = textFieldModifier.menuAnchor(ExposedDropdownMenuAnchorType.PrimaryEditable).primaryFocusable(),
            trailingIcon = {
                if (enabled) {
                    Row {
                        clearIcon?.invoke {
                            setValue("")
                        }
                        expandIcon(expanded)
                    }
                }
            },
            placeholder = placeholder?.asDisplayString(),
            isLast = isLast,
            readOnly = readOnly,
            onValueChange = { setExpanded(true) },
            leadingIcon =
                leadingIcon?.let {
                    { it() }
                },
        )

        if (filteredOptions.isNotEmpty()) {
            ExposedDropdownMenu(
                modifier = exposedDropdownMenuModifier.lazyDropdownMenu(lazyDropdownScope),
                expanded = expanded,
                onDismissRequest = {
                    setExpanded(false)
                },
            ) {
                LazyDropdownColumn(
                    items = filteredOptions,
                    scope = lazyDropdownScope,
                ) {
                    menuItem(it) { item ->
                        setValue(item.label)
                        setExpanded(false)
                    }
                }
            }
        }
    }
}
