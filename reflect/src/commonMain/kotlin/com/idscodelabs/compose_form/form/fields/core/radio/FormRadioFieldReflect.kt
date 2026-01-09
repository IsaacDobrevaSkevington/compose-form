package com.idscodelabs.compose_form.form.fields.core.radio

import androidx.compose.runtime.Composable
import com.idscodelabs.compose_form.form.core.controller.FormController
import com.idscodelabs.compose_form.form.fields.core.base.IFormFieldImplementation
import com.idscodelabs.compose_form.form.fields.core.base.ListDisplayable
import com.idscodelabs.compose_form.utils.updateModel
import com.idscodelabs.compose_form.utils.validator
import com.idscodelabs.compose_form.validators.core.Validator
import kotlin.reflect.KMutableProperty

@Composable
fun <Model, Item : ListDisplayable> FormController<Model>.FormRadioField(
    modelProperty: KMutableProperty<Item?>,
    options: List<Item>,
    initialValue: Item? = null,
    validator: Validator<Item>? = modelProperty.validator(),
    enabled: Boolean = true,
    implementation: IFormFieldImplementation<RadioFormBox<Model, Item>>,
) = FormRadioField(
    modelProperty = modelProperty,
    updateModel = modelProperty.updateModel(),
    options = options,
    initialValue = initialValue,
    validator = validator,
    enabled = enabled,
    implementation = implementation
)
