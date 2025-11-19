package com.idscodelabs.compose_form.form.fields.core.switch

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import com.idscodelabs.compose_form.form.core.FormScope
import com.idscodelabs.compose_form.form.fields.core.base.BooleanFieldFormFieldWrapper
import com.idscodelabs.compose_form.form.fields.core.base.FormFieldImplementation
import com.idscodelabs.compose_form.form.fields.core.base.FormFieldWrapper
import com.idscodelabs.compose_form.validators.core.Validator
import kotlin.reflect.KProperty

@Composable
fun <Model> FormScope<Model>.FormSwitchField(
    modelProperty: KProperty<Boolean?>,
    initialValue: Boolean?,
    enabled: Boolean,
    validator: Validator?,
    updateModel: Model.(Boolean?) -> Unit,
    implementation: FormFieldImplementation<Boolean>
) = BooleanFieldFormFieldWrapper(
    modelProperty = modelProperty,
    initialValue = initialValue,
    enabled = enabled,
    validator = validator,
    updateModel = updateModel,
    implementation = implementation
)