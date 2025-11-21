package com.idscodelabs.compose_form.form.fields.core.base

import androidx.compose.runtime.Composable
import com.idscodelabs.compose_form.form.core.FormScope
import com.idscodelabs.compose_form.validators.core.Validator
import kotlin.reflect.KProperty

@Composable
fun <Model> FormScope<Model>.StringFieldFormFieldWrapper(
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
