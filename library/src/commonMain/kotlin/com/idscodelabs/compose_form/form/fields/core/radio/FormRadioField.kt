package com.idscodelabs.compose_form.form.fields.core.radio

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.idscodelabs.compose_form.form.core.FormViewModel
import com.idscodelabs.compose_form.form.fields.core.base.DisplayableOption
import com.idscodelabs.compose_form.form.fields.core.base.FormFieldWrapper
import com.idscodelabs.compose_form.form.fields.core.base.IFormFieldImplementation
import com.idscodelabs.compose_form.form.fields.core.base.ListDisplayable
import com.idscodelabs.compose_form.form.fields.strings.asDisplayString
import com.idscodelabs.compose_form.validators.InvalidOptionValidator
import com.idscodelabs.compose_form.validators.core.Validator
import kotlin.reflect.KProperty

@Composable
fun <Model, Item : ListDisplayable> FormViewModel<Model>.FormRadioField(
    modelProperty: KProperty<Item?>,
    updateModel: Model.(Item?) -> Unit,
    options: List<Item>,
    initialValue: Item? = null,
    validator: Validator? = null,
    enabled: Boolean = true,
    invalidOptionError: Any = "Invalid Option",
    implementation: IFormFieldImplementation<RadioFormBox<Model, Item>>,
) {
    val displayableOptions =
        remember(options) {
            options.sortedBy { it.position }
        }.map { DisplayableOption(it, it.label.asDisplayString()) }

    val displayableOptionsListString =
        remember(displayableOptions) {
            displayableOptions.map { it.label }
        }

    FormFieldWrapper(
        modelProperty = modelProperty,
        initialValue = displayableOptions.indexOfFirst { it.item.key == initialValue?.key }.takeIf { it != -1 },
        enabled = enabled,
        validator = InvalidOptionValidator(displayableOptionsListString, invalidOptionError) + validator,
        updateModel = {
            updateModel(it?.let { index -> displayableOptions.getOrNull(index) }?.item)
        },
        implementation = implementation,
        formImplementationMapper = { RadioFormBox(this, displayableOptions) },
        valueToString = { item ->
            item?.toString()
        },
        stringToValue = { string ->
            string?.toIntOrNull()?.takeIf { it != -1 } ?: 0
        },
    )
}
