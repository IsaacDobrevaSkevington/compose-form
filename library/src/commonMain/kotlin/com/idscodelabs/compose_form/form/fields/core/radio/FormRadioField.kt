package com.idscodelabs.compose_form.form.fields.core.radio

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.idscodelabs.compose_form.form.core.FormScope
import com.idscodelabs.compose_form.form.fields.core.base.DisplayableOption
import com.idscodelabs.compose_form.form.fields.core.base.FormFieldWrapper
import com.idscodelabs.compose_form.form.fields.core.base.IFormFieldImplementation
import com.idscodelabs.compose_form.form.fields.core.base.ListDisplayable
import com.idscodelabs.compose_form.form.fields.strings.asDisplayString
import com.idscodelabs.compose_form.validators.InvalidOptionValidator
import com.idscodelabs.compose_form.validators.core.Validator
import kotlin.reflect.KProperty

/**
 * A radio form field
 *
 * @param Model The Model of the form
 * @param modelProperty The [KProperty] of the [Model] which this field updates.
 * @param initialValue The initial value for this field
 * @param enabled Whether the field is enabled
 * @param validator The [Validator] for this field
 * @param updateModel Function called which should set the correct property on the model. Most often should be `{ <variable> = it }`
 * @param implementation The implementation of the form field UI
 * @see [ListDisplayable]
 * @sample com.idscodelabs.compose_form.examples.fields.radio.FormRadioFieldExample
 */
@Composable
fun <Model, Item : ListDisplayable> FormScope<Model>.FormRadioField(
    modelProperty: KProperty<*>,
    updateModel: Model.(Item?) -> Unit,
    options: List<Item>,
    initialValue: Item? = null,
    validator: Validator? = null,
    enabled: Boolean = true,
    implementation: IFormFieldImplementation<RadioFormBox<Model, Item>>,
) {
    val displayableOptions =
        remember(options) {
            options.sortedBy { it.position }
        }.map { DisplayableOption(it, it.label.asDisplayString()) }

    FormFieldWrapper(
        modelProperty = modelProperty,
        initialValue = displayableOptions.indexOfFirst { it.item.key == initialValue?.key }.takeIf { it != -1 },
        enabled = enabled,
        validator = validator,
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
