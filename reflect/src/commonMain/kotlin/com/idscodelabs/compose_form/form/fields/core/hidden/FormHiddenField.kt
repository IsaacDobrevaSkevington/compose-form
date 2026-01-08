package com.idscodelabs.compose_form.form.fields.core.hidden

import androidx.compose.runtime.Composable
import com.idscodelabs.compose_form.form.core.controller.FormController
import com.idscodelabs.compose_form.reflect.form.utils.updateModel
import kotlin.reflect.KMutableProperty
import kotlin.reflect.KProperty

/**
 * A hidden field in the form. Use this to store model properties which should be displayed to the user
 *
 * @param Model The Model of the form
 * @param modelProperty The [KProperty] of the [Model] which this field updates.
 * @param value The value for this field
 * @param updateModel Function called which should set the correct property on the model. Most often should be `{ <variable> = it }`
 */
@Composable
fun <Model, Value> FormController<Model>.FormHiddenField(
    modelProperty: KMutableProperty<Value?>,
    value: String? = null,
) = FormHiddenField(
    modelProperty = modelProperty,
    updateModel = modelProperty.updateModel(),
)
