package com.idscodelabs.compose_form.form.fields.core.hidden

import androidx.compose.runtime.Composable
import com.idscodelabs.compose_form.form.core.FormScope
import com.idscodelabs.compose_form.form.model.rememberFormBox
import kotlin.reflect.KProperty

@Composable
fun <Model> FormScope<Model>.FormHiddenField(
    modelProperty: KProperty<*>,
    value: String? = null,
    updateModel: Model.(String?) -> Unit = {},
) {
    rememberFormBox(
        true,
        null,
        updateModel,
        { value },
        { value },
        { value },
        modelProperty.name,
    ).BindLifecycle(modelProperty)
}
