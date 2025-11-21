package com.idscodelabs.compose_form.form.fields.core.base

import androidx.compose.runtime.Composable
import com.idscodelabs.compose_form.form.core.FormScope
import com.idscodelabs.compose_form.validators.core.Validator
import kotlin.reflect.KProperty

@Composable
fun <Model> FormScope<Model>.IntFieldFormFieldWrapper(
    modelProperty: KProperty<*>,
    initialValue: Int?,
    enabled: Boolean,
    validator: Validator?,
    updateModel: Model.(Int?) -> Unit,
    implementation: FormFieldImplementation<Int>,
) = FormFieldWrapper(
    modelProperty = modelProperty,
    initialValue = initialValue,
    enabled = enabled,
    validator = validator,
    updateModel = updateModel,
    implementation = implementation,
    valueToString = { it?.toString() },
    stringToValue = { it?.toIntOrNull() ?: 0 },
    formImplementationMapper = { this },
)
