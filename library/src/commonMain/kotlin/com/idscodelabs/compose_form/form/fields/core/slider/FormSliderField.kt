package com.idscodelabs.compose_form.form.fields.core.slider

import androidx.compose.runtime.Composable
import com.idscodelabs.compose_form.form.core.FormScope
import com.idscodelabs.compose_form.form.fields.core.base.FormFieldImplementation
import com.idscodelabs.compose_form.form.fields.core.base.IntFieldFormFieldWrapper
import com.idscodelabs.compose_form.validators.core.Validator
import kotlin.reflect.KProperty


@Composable
fun <Model> FormScope<Model>.FormSliderField(
    modelProperty: KProperty<Int?>,
    initialValue: Int?,
    enabled: Boolean,
    validator: Validator?,
    updateModel: Model.(Int?) -> Unit,
    implementation: FormFieldImplementation<Int>
) = IntFieldFormFieldWrapper(
    modelProperty = modelProperty,
    initialValue = initialValue,
    enabled = enabled,
    validator = validator,
    updateModel = updateModel,
    implementation = implementation
)