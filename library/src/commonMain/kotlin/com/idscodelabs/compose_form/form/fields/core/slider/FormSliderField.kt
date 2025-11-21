package com.idscodelabs.compose_form.form.fields.core.slider

import androidx.compose.runtime.Composable
import com.idscodelabs.compose_form.form.core.FormViewModel
import com.idscodelabs.compose_form.form.fields.core.base.FormFieldImplementation
import com.idscodelabs.compose_form.form.fields.core.base.IntFieldFormFieldWrapper
import com.idscodelabs.compose_form.validators.core.Validator
import kotlin.reflect.KProperty

@Composable
fun <Model> FormViewModel<Model>.FormSliderField(
    modelProperty: KProperty<Int?>,
    updateModel: Model.(Int?) -> Unit,
    initialValue: Int? = null,
    validator: Validator? = null,
    enabled: Boolean = true,
    implementation: FormFieldImplementation<Int>,
) = IntFieldFormFieldWrapper(
    modelProperty = modelProperty,
    initialValue = initialValue,
    enabled = enabled,
    validator = validator,
    updateModel = updateModel,
    implementation = implementation,
)
