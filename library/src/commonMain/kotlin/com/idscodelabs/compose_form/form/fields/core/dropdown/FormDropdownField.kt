package com.idscodelabs.compose_form.form.fields.core.dropdown

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.idscodelabs.compose_form.form.core.FormViewModel
import com.idscodelabs.compose_form.form.fields.core.base.DisplayableOption
import com.idscodelabs.compose_form.form.fields.core.base.IFormFieldImplementation
import com.idscodelabs.compose_form.form.fields.core.base.ListDisplayable
import com.idscodelabs.compose_form.form.fields.core.base.TextFieldFormFieldWrapper
import com.idscodelabs.compose_form.form.fields.strings.asDisplayString
import com.idscodelabs.compose_form.validators.InvalidOptionValidator
import com.idscodelabs.compose_form.validators.core.Validator
import kotlin.reflect.KProperty

@Composable
fun <Model, Item : ListDisplayable> FormViewModel<Model>.FormDropdownField(
    modelProperty: KProperty<Item?>,
    updateModel: Model.(Item?) -> Unit,
    options: List<Item>,
    initialValue: Item? = null,
    validator: Validator? = null,
    enabled: Boolean = true,
    invalidOptionError: Any = "Invalid Option",
    implementation: IFormFieldImplementation<DropdownFormBox<Model, Item>>,
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
