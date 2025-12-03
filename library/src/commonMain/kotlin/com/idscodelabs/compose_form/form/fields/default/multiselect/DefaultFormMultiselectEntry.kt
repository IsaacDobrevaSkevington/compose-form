package com.idscodelabs.compose_form.form.fields.default.multiselect

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuAnchorType
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.idscodelabs.compose_form.form.core.FormScope
import com.idscodelabs.compose_form.form.fields.core.base.DisplayableOption
import com.idscodelabs.compose_form.form.fields.core.base.ListDisplayable
import com.idscodelabs.compose_form.form.fields.core.multiselect.MultiselectFormBox
import com.idscodelabs.compose_form.form.fields.default.dropdown.LazyDropdownColumn
import com.idscodelabs.compose_form.form.fields.default.dropdown.lazyDropdown
import com.idscodelabs.compose_form.form.fields.default.dropdown.rememberLazyDropdownScope
import com.idscodelabs.compose_form.form.fields.default.text.DefaultTextEntry
import com.idscodelabs.compose_form.form.fields.strings.asDisplayString

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun <Model, Item : ListDisplayable> MultiselectFormBox<Model, Item>.DefaultFormMultiselectEntry(
    textFieldModifier: Modifier = Modifier.fillMaxWidth(),
    exposedDropdownBoxModifier: Modifier = Modifier,
    exposedDropdownMenuModifier: Modifier = Modifier,
    hint: Any? = null,
    placeholder: Any? = hint,
    isLast: Boolean = false,
    leadingIcon: (@Composable MultiselectFormBox<Model, Item>.() -> Unit)? = null,
    clearIcon: ((onClick: () -> Unit) -> FormScope.IconParams)? = {
        FormScope.IconParams(
            Icons.Filled.Close,
            onClick = it,
        )
    },
    expandIcon: (expanded: Boolean) -> FormScope.IconParams = {
        FormScope.IconParams(
            Icons.Filled.ArrowDropDown,
            if (it) 180f else 0f,
        ) {}
    },
    menuItem: @Composable DisplayableOption<Item>.() -> Unit = {
        DefaultMultiselectMenuItem(this)
    },
) {
    val (allowExpanded, setExpanded) = remember { mutableStateOf(false) }
    val expanded = allowExpanded && options.isNotEmpty()

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
            clearIcon?.invoke {
                setSelected(emptyList())
            }

        val expandIconResolved = expandIcon(expanded)
        DefaultTextEntry(
            hint = hint,
            modifier = textFieldModifier.menuAnchor(ExposedDropdownMenuAnchorType.PrimaryEditable),
            icons =
                listOfNotNull(
                    clearIconResolved.takeIf { currentItems.isNotEmpty() && enabled },
                    expandIconResolved.takeIf { enabled },
                ),
            placeholder = placeholder?.asDisplayString(),
            isLast = isLast,
            onValueChange = { setExpanded(true) },
            leadingIcon =
                leadingIcon?.let {
                    { it() }
                },
            readOnly = true,
        )

        if (options.isNotEmpty()) {
            ExposedDropdownMenu(
                modifier = exposedDropdownMenuModifier,
                expanded = expanded,
                onDismissRequest = {
                    setExpanded(false)
                },
            ) {
                LazyDropdownColumn(
                    items = options,
                    scope = lazyDropdownScope,
                ) {
                    menuItem(it)
                }
            }
        }
    }
}
