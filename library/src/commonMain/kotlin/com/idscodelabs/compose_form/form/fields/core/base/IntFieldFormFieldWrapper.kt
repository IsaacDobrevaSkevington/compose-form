package com.idscodelabs.compose_form.form.fields.core.base

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import com.idscodelabs.compose_form.form.core.FormScope
import com.idscodelabs.compose_form.validators.core.Validator
import kotlin.reflect.KProperty

@Composable
fun <Model> FormScope<Model>.IntFieldFormFieldWrapper(
    modelProperty: KProperty<Int?>,
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
    valueToStored = { it },
    storedToString = { it?.toString() },
    stringToValue = { it?.toIntOrNull() },
    formImplementationMapper = { this },
    rememberState = {
        rememberSaveable(modelProperty.name) { mutableIntStateOf(0) }
    },
)
