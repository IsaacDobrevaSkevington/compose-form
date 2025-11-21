package com.idscodelabs.compose_form.form.fields.core.base

import androidx.compose.runtime.Composable
import com.idscodelabs.compose_form.form.core.FormViewModel
import com.idscodelabs.compose_form.validators.core.Validator
import kotlin.reflect.KProperty

@Composable
fun <Model> FormViewModel<Model>.FloatFieldFormFieldWrapper(
    modelProperty: KProperty<*>,
    initialValue: Float?,
    enabled: Boolean,
    validator: Validator?,
    updateModel: Model.(Float?) -> Unit,
    implementation: FormFieldImplementation<Float>,
) = FormFieldWrapper(
    modelProperty = modelProperty,
    initialValue = initialValue,
    enabled = enabled,
    validator = validator,
    updateModel = updateModel,
    implementation = implementation,
    valueToString = { it?.toString() },
    stringToValue = { it?.toFloatOrNull() ?: 0f },
    formImplementationMapper = { this },
)
