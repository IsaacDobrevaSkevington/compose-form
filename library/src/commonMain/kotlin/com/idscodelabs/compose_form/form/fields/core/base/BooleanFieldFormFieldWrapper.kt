package com.idscodelabs.compose_form.form.fields.core.base

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import com.idscodelabs.compose_form.form.core.FormScope
import com.idscodelabs.compose_form.validators.core.Validator
import kotlin.reflect.KProperty

@Composable
fun <Model> FormScope<Model>.BooleanFieldFormFieldWrapper(
    modelProperty: KProperty<Boolean?>,
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
    valueToStored = { it },
    storedToString = { it?.toString() },
    stringToValue = { it?.let { v -> v == "true" }},
    formImplementationMapper = { this },
    rememberState = {
        rememberSaveable(it) {
            mutableStateOf(false)
        }
    }
)