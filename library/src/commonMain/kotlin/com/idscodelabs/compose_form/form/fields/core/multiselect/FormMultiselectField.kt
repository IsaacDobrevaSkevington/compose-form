package com.idscodelabs.compose_form.form.fields.core.multiselect

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.text.input.TextFieldValue
import com.idscodelabs.compose_form.form.core.FormScope
import com.idscodelabs.compose_form.form.fields.core.base.DisplayableOption
import com.idscodelabs.compose_form.form.fields.core.base.FormFieldWrapper
import com.idscodelabs.compose_form.form.fields.core.base.IFormFieldImplementation
import com.idscodelabs.compose_form.form.fields.core.base.ListDisplayable
import com.idscodelabs.compose_form.form.fields.core.base.StringFieldFormFieldWrapper
import com.idscodelabs.compose_form.form.fields.core.base.TextFieldFormFieldWrapper
import com.idscodelabs.compose_form.form.fields.strings.asDisplayString
import com.idscodelabs.compose_form.validators.InvalidOptionValidator
import com.idscodelabs.compose_form.validators.core.Validator
import kotlin.reflect.KProperty

@Composable
fun <Model, Item : ListDisplayable> FormScope<Model>.FormMultiselectField(
    modelProperty: KProperty<*>,
    updateModel: Model.(List<Item>) -> Unit,
    options: List<Item>,
    initialValue: List<Item> = emptyList(),
    validator: Validator? = null,
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
        formImplementationMapper = { MultiselectFormBox(this, displayableOptions, itemDelimiter) },
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
