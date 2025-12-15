package com.idscodelabs.compose_form.form.fields.default.dropdown

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import com.idscodelabs.compose_form.form.core.controller.FormController
import com.idscodelabs.compose_form.form.fields.core.base.DisplayableOption
import com.idscodelabs.compose_form.form.fields.core.base.ListDisplayable
import com.idscodelabs.compose_form.form.fields.core.dropdown.DropdownFormBox
import com.idscodelabs.compose_form.form.fields.core.dropdown.FormDropdownField
import com.idscodelabs.compose_form.form.icons.Icons
import com.idscodelabs.compose_form.utils.IconButton
import com.idscodelabs.compose_form.validators.core.Validator
import kotlin.reflect.KProperty

@Composable
fun <Model, Item : ListDisplayable> FormController<Model>.FormAutocompleteField(
    modelProperty: KProperty<*>,
    updateModel: Model.(Item?) -> Unit,
    options: List<Item>,
    initialValue: Item? = null,
    validator: Validator<Item>? = null,
    enabled: Boolean = true,
    invalidOptionError: Any = "Invalid Option",
    textFieldModifier: Modifier = Modifier.fillMaxWidth(),
    exposedDropdownBoxModifier: Modifier = Modifier,
    exposedDropdownMenuModifier: Modifier = Modifier,
    hint: Any? = null,
    placeholder: Any? = hint,
    isLast: Boolean = false,
    leadingIcon: (@Composable DropdownFormBox<*, Item>.() -> Unit)? = null,
    filterFunction: (item: String, value: String) -> Boolean = { item, value -> item.startsWith(value, ignoreCase = true) },
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
) = FormDropdownField(
    modelProperty = modelProperty,
    updateModel = updateModel,
    options = options,
    initialValue = initialValue,
    validator = validator,
    enabled = enabled,
    invalidOptionError = invalidOptionError,
) {
    DefaultAutocompleteFormDropdownEntry(
        textFieldModifier = textFieldModifier,
        exposedDropdownBoxModifier = exposedDropdownBoxModifier,
        exposedDropdownMenuModifier = exposedDropdownMenuModifier,
        hint = hint,
        placeholder = placeholder,
        isLast = isLast,
        leadingIcon = leadingIcon,
        clearIcon = clearIcon,
        expandIcon = expandIcon,
        menuItem = menuItem,
        filterFunction = filterFunction,
    )
}
