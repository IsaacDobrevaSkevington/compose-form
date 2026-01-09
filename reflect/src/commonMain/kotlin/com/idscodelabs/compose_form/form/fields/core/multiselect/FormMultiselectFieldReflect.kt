package com.idscodelabs.compose_form.form.fields.core.multiselect

import androidx.compose.runtime.Composable
import com.idscodelabs.compose_form.form.core.controller.FormController
import com.idscodelabs.compose_form.form.fields.core.base.IFormFieldImplementation
import com.idscodelabs.compose_form.form.fields.core.base.ListDisplayable
import com.idscodelabs.compose_form.utils.updateModel
import com.idscodelabs.compose_form.validators.core.Validator
import kotlin.reflect.KMutableProperty

@Composable
fun <Model, Item : ListDisplayable> FormController<Model>.FormMultiselectField(
    modelProperty: KMutableProperty<List<Item>>,
    options: List<Item>,
    initialValue: List<Item> = emptyList(),
    validator: Validator<List<Item>>? = null,
    enabled: Boolean = true,
    itemDelimiter: String = ", ",
    implementation: IFormFieldImplementation<MultiselectFormBox<Model, Item>>,
) = FormMultiselectField(
    modelProperty = modelProperty,
    updateModel = modelProperty.updateModel(),
    options = options,
    initialValue = initialValue,
    validator = validator,
    enabled = enabled,
    itemDelimiter = itemDelimiter,
    implementation = implementation,
)
