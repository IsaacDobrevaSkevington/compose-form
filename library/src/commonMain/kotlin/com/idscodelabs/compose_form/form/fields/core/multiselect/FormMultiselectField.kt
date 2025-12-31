package com.idscodelabs.compose_form.form.fields.core.multiselect

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.idscodelabs.compose_form.form.core.controller.FormController
import com.idscodelabs.compose_form.form.fields.core.base.DisplayableOption
import com.idscodelabs.compose_form.form.fields.core.base.IFormFieldImplementation
import com.idscodelabs.compose_form.form.fields.core.base.ListDisplayable
import com.idscodelabs.compose_form.form.fields.core.base.TextFieldFormFieldWrapper
import com.idscodelabs.compose_form.form.fields.strings.asDisplayString
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
 * @param implementation The implementation of the form field UI
 * @param itemDelimiter The delimiter to use for the item, both when displayed and when stored. The [Item] label  must not contain this charater
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
    implementation: IFormFieldImplementation<MultiselectFormBox<Model, Item>>,
) {
    val displayableOptions =
        remember(options) {
            options.sortedBy { it.position }
        }.map { DisplayableOption(it, it.label.asDisplayString()) }

    TextFieldFormFieldWrapper(
        modelProperty = modelProperty,
        initialValue = initialValue,
        enabled = enabled,
        validator = validator,
        updateModel = { updateModel(it ?: emptyList()) },
        implementation = implementation,
        formImplementationMapper = { rememberAsMultiselectFormBox(displayableOptions, itemDelimiter) },
        stringToValue = { string ->
            val items = string?.split(itemDelimiter) ?: emptyList()
            items.mapNotNull { itemString ->
                displayableOptions.firstOrNull { it.label == itemString.trim() }?.item
            }
        },
        valueToString = {
            initialValue
                .mapNotNull { item ->
                    displayableOptions
                        .firstOrNull { it.item.key == item.key }
                        ?.label
                        ?.trim()
                        ?.ifBlank { null }
                }.joinToString(itemDelimiter)
        },
    )
}
