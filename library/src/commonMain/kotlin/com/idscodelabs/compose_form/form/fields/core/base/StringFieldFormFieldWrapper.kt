package com.idscodelabs.compose_form.form.fields.core.base

import androidx.compose.runtime.Composable
import com.idscodelabs.compose_form.form.core.FormViewModel
import com.idscodelabs.compose_form.validators.core.Validator
import kotlin.reflect.KProperty

@Composable
fun <Model> FormViewModel<Model>.StringFieldFormFieldWrapper(
    modelProperty: KProperty<*>,
    initialValue: String?,
    enabled: Boolean,
    validator: Validator?,
    updateModel: Model.(String?) -> Unit,
    implementation: FormFieldImplementation<String>,
) = FormFieldWrapper(
    modelProperty = modelProperty,
    initialValue = initialValue,
    enabled = enabled,
    validator = validator,
    updateModel = updateModel,
    implementation = implementation,
    valueToString = { it },
    stringToValue = { it ?: "" },
    formImplementationMapper = { this },
)
