package com.idscodelabs.compose_form.form.fields.core.base

import androidx.compose.runtime.Composable
import com.idscodelabs.compose_form.form.core.FormViewModel
import com.idscodelabs.compose_form.validators.core.Validator
import kotlin.reflect.KProperty

@Composable
fun <Model> FormViewModel<Model>.BooleanFieldFormFieldWrapper(
    modelProperty: KProperty<*>,
    initialValue: Boolean?,
    enabled: Boolean,
    validator: Validator?,
    updateModel: Model.(Boolean?) -> Unit,
    implementation: FormFieldImplementation<Boolean>,
) = FormFieldWrapper(
    modelProperty = modelProperty,
    initialValue = initialValue,
    enabled = enabled,
    validator = validator,
    updateModel = updateModel,
    implementation = implementation,
    stringToValue = { it?.let { v -> v == "true" } ?: false },
    valueToString = { it?.toString() },
    formImplementationMapper = { this },
)
