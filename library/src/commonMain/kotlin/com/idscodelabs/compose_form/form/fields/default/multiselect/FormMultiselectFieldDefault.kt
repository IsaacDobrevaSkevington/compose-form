package com.idscodelabs.compose_form.form.fields.default.multiselect

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import com.idscodelabs.compose_form.form.core.controller.FormController
import com.idscodelabs.compose_form.form.fields.core.base.DisplayableOption
import com.idscodelabs.compose_form.form.fields.core.base.ListDisplayable
import com.idscodelabs.compose_form.form.fields.core.multiselect.FormMultiselectField
import com.idscodelabs.compose_form.form.fields.core.multiselect.MultiselectFormBox
import com.idscodelabs.compose_form.form.icons.Icons
import com.idscodelabs.compose_form.utils.IconButton
import com.idscodelabs.compose_form.validators.core.Validator
import kotlin.reflect.KProperty

@Composable
fun <Model, Item : ListDisplayable> FormController<Model>.FormMultiselectField(
    modelProperty: KProperty<*>,
    updateModel: Model.(List<Item>) -> Unit,
    options: List<Item>,
    initialValue: List<Item> = emptyList(),
    validator: Validator<List<Item>>? = null,
    enabled: Boolean = true,
    itemDelimiter: String = ", ",
    textFieldModifier: Modifier = Modifier.fillMaxWidth(),
    exposedDropdownBoxModifier: Modifier = Modifier,
    exposedDropdownMenuModifier: Modifier = Modifier,
    hint: Any? = null,
    placeholder: Any? = hint,
    isLast: Boolean = false,
    leadingIcon: (@Composable MultiselectFormBox<Model, Item>.() -> Unit)? = null,
    clearIcon: (@Composable MultiselectFormBox<Model, Item>.(onClick: () -> Unit) -> Unit)? = {
        IconButton(Icons.Close, "Clear Icon") { it() }
    },
    expandIcon: @Composable MultiselectFormBox<Model, Item>.(expanded: Boolean) -> Unit = {
        IconButton(Icons.ArrowDropDown, "Expand Icon", iconModifier = Modifier.rotate(if (it) 180f else 0f)) {}
    },
    menuItem: @Composable MultiselectFormBox<Model, Item>.(
        item: DisplayableOption<Item>,
    ) -> Unit = { item ->
        DefaultMultiselectMenuItem(item)
    },
) = FormMultiselectField(
    modelProperty = modelProperty,
    updateModel = updateModel,
    options = options,
    initialValue = initialValue,
    validator = validator,
    enabled = enabled,
    itemDelimiter = itemDelimiter,
) {
    DefaultFormMultiselectEntry(
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
    )
}
