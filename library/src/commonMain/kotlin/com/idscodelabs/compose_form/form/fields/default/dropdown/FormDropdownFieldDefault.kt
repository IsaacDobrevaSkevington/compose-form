package com.idscodelabs.compose_form.form.fields.default.dropdown

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.idscodelabs.compose_form.form.core.FormScope
import com.idscodelabs.compose_form.form.fields.core.base.DisplayableOption
import com.idscodelabs.compose_form.form.fields.core.base.FormFieldImplementation
import com.idscodelabs.compose_form.form.fields.core.base.IFormFieldImplementation
import com.idscodelabs.compose_form.form.fields.core.base.ListDisplayable
import com.idscodelabs.compose_form.form.fields.core.base.TextFieldFormFieldWrapper
import com.idscodelabs.compose_form.form.fields.core.date.LocalFormDateFormatter
import com.idscodelabs.compose_form.form.fields.core.date.sanitizeDate
import com.idscodelabs.compose_form.form.fields.core.dropdown.DropdownFormBox
import com.idscodelabs.compose_form.form.fields.strings.asDisplayString
import com.idscodelabs.compose_form.validators.InvalidOptionValidator
import com.idscodelabs.compose_form.validators.core.Validator
import kotlin.reflect.KProperty

/**
 * A dropdown form field
 *
 * @param Model The Model of the form
 * @param modelProperty The [KProperty] of the [Model] which this field updates.
 * @param initialValue The initial value for this field
 * @param enabled Whether the field is enabled
 * @param validator The [Validator] for this field
 * @param updateModel Function called which should set the correct property on the model. Most often should be `{ <variable> = it }`
 * @param implementation The implementation of the form field UI
 * @param invalidOptionError The error to be displayed if an invalid option is entered into the box
 * @see [ListDisplayable]
 * @sample com.idscodelabs.compose_form.examples.fields.dropdown.large.FormDropdownFieldExampleLarge
 * @sample com.idscodelabs.compose_form.examples.fields.dropdown.large.FormAutocompleteDropdownFieldExampleLarge
 */
@Composable
fun <Model, Item : ListDisplayable> FormScope<Model>.FormDropdownField(
    modelProperty: KProperty<*>,
    updateModel: Model.(Item?) -> Unit,
    options: List<Item>,
    initialValue: Item? = null,
    validator: Validator? = null,
    enabled: Boolean = true,
    invalidOptionError: Any = "Invalid Option",
    textFieldModifier: Modifier = Modifier.fillMaxWidth(),
    exposedDropdownBoxModifier: Modifier = Modifier,
    exposedDropdownMenuModifier: Modifier = Modifier,
    hint: Any? = null,
    placeholder: Any? = hint,
    isLast: Boolean = false,
    leadingIcon: (@Composable DropdownFormBox<Model, Item>.() -> Unit)? = null,
    clearIcon: (DropdownFormBox<Model, Item>.(onClick: () -> Unit) -> FormScope.IconParams)? = {
        FormScope.IconParams(
            Icons.Filled.Close,
            onClick = it,
        )
    },
    expandIcon: DropdownFormBox<Model, Item>.(expanded: Boolean) -> FormScope.IconParams = {
        FormScope.IconParams(
            Icons.Filled.ArrowDropDown,
            if (it) 180f else 0f,
        ) {}
    },
    menuItem: @Composable DropdownFormBox<Model, Item>.(item: DisplayableOption<Item>, setExpanded: (Boolean) -> Unit) -> Unit = {item, setExpanded ->
        DefaultDropdownMenuItem(item, setExpanded)
    },
) {
    val displayableOptions =
        remember(options) {
            options.sortedBy { it.position }
        }.map { DisplayableOption(it, it.label.asDisplayString()) }

    val displayableOptionsListString =
        remember(displayableOptions) {
            displayableOptions.map { it.label }
        }

    TextFieldFormFieldWrapper(
        modelProperty = modelProperty,
        initialValue = initialValue,
        enabled = enabled,
        validator = InvalidOptionValidator(displayableOptionsListString, invalidOptionError) + validator,
        updateModel = updateModel,
        implementation = implementation,
        formImplementationMapper = { DropdownFormBox(this, displayableOptions) },
        valueToString = { item ->
            displayableOptions
                .firstOrNull { it.item.key == item?.key }
                ?.label
                ?.trim()
                ?.ifBlank { null }
        },
        stringToValue = { string ->
            displayableOptions.firstOrNull { it.label == string?.trim() }?.item
        },
    )
}
