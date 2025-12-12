package com.idscodelabs.compose_form.form.fields.core.dropdown

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.idscodelabs.compose_form.form.core.controller.FormController
import com.idscodelabs.compose_form.form.fields.core.base.DisplayableOption
import com.idscodelabs.compose_form.form.fields.core.base.IFormFieldImplementation
import com.idscodelabs.compose_form.form.fields.core.base.ListDisplayable
import com.idscodelabs.compose_form.form.fields.core.base.TextFieldFormFieldWrapper
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
fun <Model, Item : ListDisplayable> FormController<Model>.FormDropdownField(
    modelProperty: KProperty<*>,
    updateModel: Model.(Item?) -> Unit,
    options: List<Item>,
    initialValue: Item? = null,
    validator: Validator<Item>? = null,
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
        validator = InvalidOptionValidator<Item>(displayableOptionsListString, invalidOptionError) + validator,
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
