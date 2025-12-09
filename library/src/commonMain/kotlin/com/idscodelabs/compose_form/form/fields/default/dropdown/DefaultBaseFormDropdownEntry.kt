package com.idscodelabs.compose_form.form.fields.default.dropdown

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuAnchorType
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import com.idscodelabs.compose_form.form.core.FormScope
import com.idscodelabs.compose_form.form.fields.core.base.DisplayableOption
import com.idscodelabs.compose_form.form.fields.core.base.ListDisplayable
import com.idscodelabs.compose_form.form.fields.core.dropdown.DropdownFormBox
import com.idscodelabs.compose_form.form.fields.default.text.DefaultTextEntry
import com.idscodelabs.compose_form.form.fields.strings.asDisplayString
import com.idscodelabs.compose_form.form.icons.Icons

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun <Model, Item : ListDisplayable> DropdownFormBox<Model, Item>.DefaultBaseFormDropdownEntry(
    textFieldModifier: Modifier = Modifier.fillMaxWidth(),
    exposedDropdownBoxModifier: Modifier = Modifier,
    exposedDropdownMenuModifier: Modifier = Modifier,
    hint: Any? = null,
    placeholder: Any? = hint,
    isLast: Boolean = false,
    readOnly: Boolean = false,
    leadingIcon: (@Composable DropdownFormBox<Model, Item>.() -> Unit)? = null,
    filterFunction: ((item: String, value: String) -> Boolean)? = null,
    clearIcon: (DropdownFormBox<Model, Item>.(onClick: () -> Unit) -> FormScope.IconParams)? = {
        FormScope.IconParams(
            Icons.Close,
            onClick = it,
        )
    },
    expandIcon: DropdownFormBox<Model, Item>.(expanded: Boolean) -> FormScope.IconParams = {
        FormScope.IconParams(
            Icons.ArrowDropDown,
            if (it) 180f else 0f,
        ) {}
    },
    menuItem: @Composable DropdownFormBox<Model, Item>.(
        item: DisplayableOption<Item>,
        setExpanded: (Boolean) -> Unit,
    ) -> Unit = { item, setExpanded ->
        DefaultDropdownMenuItem(item, setExpanded)
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
        modifier = exposedDropdownBoxModifier.lazyDropdown(lazyDropdownScope),
        expanded = expanded,
        onExpandedChange = {
            if (enabled) {
                setExpanded(it)
            } else {
                setExpanded(false)
            }
        },
    ) {
        val clearIconResolved =
            clearIcon?.invoke(this@DefaultBaseFormDropdownEntry) {
                setValue(TextFieldValue())
            }

        val expandIconResolved = expandIcon(expanded)
        DefaultTextEntry(
            hint = hint,
            modifier = textFieldModifier.menuAnchor(ExposedDropdownMenuAnchorType.PrimaryEditable),
            icons =
                listOfNotNull(
                    clearIconResolved.takeIf { value.text.isNotBlank() && enabled },
                    expandIconResolved.takeIf { enabled },
                ),
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
                modifier = exposedDropdownMenuModifier,
                expanded = expanded,
                onDismissRequest = {
                    setExpanded(false)
                },
            ) {
                LazyDropdownColumn(
                    items = filteredOptions,
                    scope = lazyDropdownScope,
                ) {
                    menuItem(it, setExpanded)
                }
            }
        }
    }
}
