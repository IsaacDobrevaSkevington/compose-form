package com.idscodelabs.compose_form.form.fields.default.multiselect

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
import com.idscodelabs.compose_form.form.fields.core.multiselect.MultiselectFormBox
import com.idscodelabs.compose_form.form.fields.default.dropdown.LazyDropdownColumn
import com.idscodelabs.compose_form.form.fields.default.dropdown.lazyDropdownBox
import com.idscodelabs.compose_form.form.fields.default.dropdown.lazyDropdownMenu
import com.idscodelabs.compose_form.form.fields.default.dropdown.rememberLazyDropdownScope
import com.idscodelabs.compose_form.form.fields.default.text.DefaultTextEntry
import com.idscodelabs.compose_form.form.fields.strings.asDisplayString
import com.idscodelabs.compose_form.form.icons.Icons
import com.idscodelabs.compose_form.utils.IconButton

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun <Item : ListDisplayable> MultiselectFormBox<*, Item>.DefaultFormMultiselectEntry(
    textFieldModifier: Modifier = Modifier.fillMaxWidth(),
    exposedDropdownBoxModifier: Modifier = Modifier,
    exposedDropdownMenuModifier: Modifier = Modifier,
    hint: Any? = null,
    placeholder: Any? = hint,
    isLast: Boolean = false,
    leadingIcon: (@Composable MultiselectFormBox<*, Item>.() -> Unit)? = null,
    clearIcon: (@Composable MultiselectFormBox<*, Item>.(onClick: () -> Unit) -> Unit)? = {
        IconButton(Icons.Close, "Clear Icon") { it() }
    },
    expandIcon: @Composable MultiselectFormBox<*, Item>.(expanded: Boolean) -> Unit = {
        IconButton(Icons.ArrowDropDown, "Expand Icon", iconModifier = Modifier.rotate(if (it) 180f else 0f)) {}
    },
    menuItem: @Composable MultiselectFormBox<*, Item>.(item: DisplayableOption<Item>) -> Unit = {
        DefaultMultiselectMenuItem(it)
    },
) {
    val (allowExpanded, setExpanded) = remember { mutableStateOf(false) }
    val expanded = allowExpanded && options.isNotEmpty()

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
            modifier = textFieldModifier.menuAnchor(ExposedDropdownMenuAnchorType.PrimaryEditable),
            trailingIcon = {
                Row {
                    clearIcon?.invoke(this@DefaultFormMultiselectEntry) {
                        setValue(TextFieldValue())
                    }
                    expandIcon(expanded)
                }
            },
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
                modifier = exposedDropdownMenuModifier.lazyDropdownMenu(lazyDropdownScope),
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
