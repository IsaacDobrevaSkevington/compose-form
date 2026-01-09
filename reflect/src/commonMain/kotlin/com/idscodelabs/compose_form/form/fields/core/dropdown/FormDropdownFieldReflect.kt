package com.idscodelabs.compose_form.form.fields.core.dropdown

import androidx.compose.runtime.Composable
import com.idscodelabs.compose_form.form.core.controller.FormController
import com.idscodelabs.compose_form.form.fields.core.base.IFormFieldImplementation
import com.idscodelabs.compose_form.form.fields.core.base.ListDisplayable
import com.idscodelabs.compose_form.utils.updateModel
import com.idscodelabs.compose_form.utils.validator
import com.idscodelabs.compose_form.validators.core.Validator
import kotlin.reflect.KMutableProperty

@Composable
fun <Model, Item : ListDisplayable> FormController<Model>.FormDropdownField(
    modelProperty: KMutableProperty<Item?>,
    options: List<Item>,
    initialValue: Item? = null,
    validator: Validator<Item>? = modelProperty.validator(),
    enabled: Boolean = true,
    invalidOptionError: Any = "Invalid Option",
    implementation: IFormFieldImplementation<DropdownFormBox<Model, Item>>,
) = FormDropdownField(
    modelProperty = modelProperty,
    updateModel = modelProperty.updateModel(),
    options = options,
    initialValue = initialValue,
    validator = validator,
    enabled = enabled,
    invalidOptionError = invalidOptionError,
    implementation = implementation,
)
