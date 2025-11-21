package com.idscodelabs.compose_form.form.fields.core.base

import androidx.compose.runtime.Composable
import com.idscodelabs.compose_form.form.core.FormScope
import com.idscodelabs.compose_form.validators.core.Validator
import kotlin.reflect.KProperty

@Composable
fun <Model> FormScope<Model>.DoubleFieldFormFieldWrapper(
    modelProperty: KProperty<*>,
    initialValue: Double?,
    enabled: Boolean,
    validator: Validator?,
    updateModel: Model.(Double?) -> Unit,
    implementation: FormFieldImplementation<Double>,
) = FormFieldWrapper(
    modelProperty = modelProperty,
    initialValue = initialValue,
    enabled = enabled,
    validator = validator,
    updateModel = updateModel,
    implementation = implementation,
    valueToString = { it?.toString() },
    stringToValue = { it?.toDoubleOrNull() ?: 0.0 },
    formImplementationMapper = { this },
)
