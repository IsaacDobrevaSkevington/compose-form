package com.idscodelabs.compose_form.form.fields.core.multiselect

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import com.idscodelabs.compose_form.form.core.controller.FormController
import com.idscodelabs.compose_form.form.fields.core.base.DisplayableOption
import com.idscodelabs.compose_form.form.fields.core.base.ListDisplayable
import com.idscodelabs.compose_form.form.fields.default.multiselect.DefaultFormMultiselectEntry
import com.idscodelabs.compose_form.form.fields.default.multiselect.DefaultMultiselectMenuItem
import com.idscodelabs.compose_form.form.icons.Icons
import com.idscodelabs.compose_form.utils.IconButton
import com.idscodelabs.compose_form.validators.core.Validator
import kotlin.reflect.KProperty

/**
 * A multiselect form field
 *
 * @param Model The Model of the form
 * @param modelProperty The [KProperty] of the [Model] which this field updates.
 * @param initialValue The initial value for this field
 * @param enabled Whether the field is enabled
 * @param validator The [Validator] for this field
 * @param updateModel Function called which should set the correct property on the model. Most often should be `{ <variable> = it }`
 * @param itemDelimiter The delimiter to use for the item, both when displayed and when stored. The [Item] label  must not contain this character
 * @param textFieldModifier The modifier to apply to the text field of the autocomplete dropdown
 * @param exposedDropdownBoxModifier The modifier to apply to the overall exposed dropdown box
 * @param exposedDropdownMenuModifier The modifier to apply to the menu inside the dropdown box
 * @param hint The hint to display for in the text field
 * @param placeholder The placeholder to display when the text field is empty
 * @param isLast If this form field is the last field in the form
 * @param leadingIcon The leading icon in the text field
 * @param clearIcon Icon to display to allow full clearing of the text field
 * @param expandIcon Icon to display which thw user can click to expand or collapse the dropdown menu
 * @param menuItem A display for the menu item
 * @see [ListDisplayable]
 * @sample com.idscodelabs.compose_form.examples.fields.multiselect.FormMultiselectFieldExample
 */
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
    leadingIcon: (@Composable MultiselectFormBox<*, Item>.() -> Unit)? = null,
    clearIcon: (@Composable MultiselectFormBox<*, Item>.(onClick: () -> Unit) -> Unit)? = {
        IconButton(Icons.Close, "Clear Icon") { it() }
    },
    expandIcon: @Composable MultiselectFormBox<*, Item>.(expanded: Boolean) -> Unit = {
        IconButton(Icons.ArrowDropDown, "Expand Icon", iconModifier = Modifier.rotate(if (it) 180f else 0f)) {}
    },
    menuItem: @Composable MultiselectFormBox<*, Item>.(
        item: DisplayableOption<Item>,
    ) -> Unit = { item ->
        DefaultMultiselectMenuItem(item)
    },
    isLast: Boolean = false,
) = FormMultiselectField(
    modelProperty = modelProperty,
    updateModel = updateModel,
    options = options,
    initialValue = initialValue,
    validator = validator,
    enabled = enabled,
    itemDelimiter = itemDelimiter,
    implementation = {
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
    },
)
