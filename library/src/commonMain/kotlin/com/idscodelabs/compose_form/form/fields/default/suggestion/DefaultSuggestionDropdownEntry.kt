package com.idscodelabs.compose_form.form.fields.default.suggestion

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.compose.ui.unit.dp
import com.idscodelabs.compose_form.form.fields.core.base.DisplayableOption
import com.idscodelabs.compose_form.form.fields.core.base.ListDisplayable
import com.idscodelabs.compose_form.form.fields.core.suggestion.SuggestionFormBox
import com.idscodelabs.compose_form.form.fields.default.dropdown.*
import com.idscodelabs.compose_form.form.fields.default.text.DefaultTextEntry
import com.idscodelabs.compose_form.form.fields.strings.asDisplayString
import com.idscodelabs.compose_form.form.icons.Icons
import com.idscodelabs.compose_form.utils.IconButton

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun <Item : ListDisplayable> SuggestionFormBox<*, Item>.DefaultSuggestionDropdownEntry(
    textFieldModifier: Modifier = Modifier.fillMaxWidth(),
    exposedDropdownBoxModifier: Modifier = Modifier,
    exposedDropdownMenuModifier: Modifier = Modifier,
    hint: Any? = null,
    placeholder: Any? = hint,
    isLast: Boolean = false,
    readOnly: Boolean = false,
    leadingIcon: (@Composable SuggestionFormBox<*, Item>.() -> Unit)? = null,
    clearIcon: (@Composable SuggestionFormBox<*, Item>.(onClick: () -> Unit) -> Unit)? = {
        IconButton(Icons.Close, "Clear Icon") { it() }
    },
    expandIcon: @Composable SuggestionFormBox<*, Item>.(expanded: Boolean) -> Unit = {
        IconButton(Icons.ArrowDropDown, "Expand Icon", iconModifier = Modifier.rotate(if (it) 180f else 0f)) {}
    },
    menuItem: @Composable SuggestionFormBox<*, Item>.(
        item: DisplayableOption<Item>,
        setExpanded: (Boolean) -> Unit,
    ) -> Unit = { item, setExpanded ->
        DefaultDropdownMenuItem(item, setExpanded)
    },
    loadingView: @Composable () -> Unit = {
        CircularProgressIndicator(Modifier.size(48.dp).padding(8.dp))
    },
) {
    val suggestions by collectSuggestionsAsState()
    val loading by collectLoadingAsState()

    val (allowExpanded, setExpanded) = remember { mutableStateOf(false) }
    val expanded = allowExpanded && suggestions.isNotEmpty() && !loading

    val enabled by collectEnabledAsState()
    val lazyDropdownScope = rememberLazyDropdownScope()
    ExposedDropdownMenuBox(
        modifier = exposedDropdownBoxModifier.lazyDropdownMenu(lazyDropdownScope),
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
                    if (loading) {
                        loadingView()
                    } else {
                        clearIcon?.invoke(this@DefaultSuggestionDropdownEntry) {
                            setValue(TextFieldValue())
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

        if (suggestions.isNotEmpty() && !loading) {
            ExposedDropdownMenu(
                modifier = exposedDropdownMenuModifier.lazyDropdownBox(lazyDropdownScope),
                expanded = expanded,
                onDismissRequest = {
                    setExpanded(false)
                },
            ) {
                val displayableSuggestions =
                    suggestions.map {
                        DisplayableOption(it, it.label.asDisplayString())
                    }
                LazyDropdownColumn(
                    items = displayableSuggestions,
                    scope = lazyDropdownScope,
                ) {
                    menuItem(it, setExpanded)
                }
            }
        }
    }
}
