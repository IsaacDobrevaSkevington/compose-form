package com.idscodelabs.compose_form.form.fields.default.dropdown

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuAnchorType
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import com.idscodelabs.compose_form.form.core.FormScope
import com.idscodelabs.compose_form.form.fields.core.base.ListDisplayable
import com.idscodelabs.compose_form.form.fields.core.dropdown.DropdownFormFieldImplementationParameters
import com.idscodelabs.compose_form.form.fields.default.text.DefaultTextEntry
import com.idscodelabs.compose_form.form.fields.strings.asDisplayString

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun <Item : ListDisplayable> DropdownFormFieldImplementationParameters<Item>.DefaultBaseFormDropdownEntry(
    textFieldModifier: Modifier = Modifier.fillMaxWidth(),
    exposedDropdownBoxModifier: Modifier = Modifier,
    exposedDropdownMenuModifier: Modifier =  Modifier,
    hint: Any? = null,
    placeholder: Any? = hint,
    isLast: Boolean = false,
    readOnly: Boolean = false,
    leadingIcon: (@Composable DropdownFormFieldImplementationParameters<Item>.() -> Unit)? = null,
    filterFunction: (String.() -> Boolean)? = null,
    clearIcon: ((onClick: ()->Unit) -> FormScope.IconParams)? = {
        FormScope.IconParams(
            Icons.Filled.Close,
            onClick = it
        )
    },
    expandIcon: (expanded: Boolean) -> FormScope.IconParams = {
        FormScope.IconParams(
            Icons.Filled.ArrowDropDown,
            if (it) 180f else 0f
        ) {}
    },
    menuItem: @Composable Item.(setExpanded: (Boolean)->Unit) -> Unit = {
        DefaultDropdownMenuItem(this, it)
    }
) {

    val filteredOptions = filterFunction?.let {
        options.filter {
            it.label.asDisplayString().it()
        }
    } ?: options
    val (allowExpanded, setExpanded) = remember { mutableStateOf(false) }
    val expanded = allowExpanded && filteredOptions.isNotEmpty()

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
        }
    ) {
        val clearIconResolved = clearIcon?.invoke {
            setValue(TextFieldValue())
        }

        val expandIconResolved = expandIcon(expanded)
        DefaultTextEntry(
            hint = hint,
            modifier = textFieldModifier.menuAnchor(ExposedDropdownMenuAnchorType.PrimaryEditable),
            icons = listOfNotNull(
                clearIconResolved.takeIf { value.text.isNotBlank() && enabled },
                expandIconResolved.takeIf { enabled }
            ),
            placeholder = placeholder?.asDisplayString(),
            isLast = isLast,
            readOnly = readOnly,
            leadingIcon = leadingIcon?.let{
                { it() }
            }
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
                ){
                    menuItem(it, setExpanded)
                }
            }
        }
    }
}