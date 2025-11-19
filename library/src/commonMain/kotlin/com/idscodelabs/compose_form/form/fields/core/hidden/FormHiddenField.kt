package com.idscodelabs.compose_form.form.fields.core.hidden

import androidx.compose.runtime.Composable
import com.idscodelabs.compose_form.form.model.FormBox
import com.idscodelabs.compose_form.form.core.FormScope
import kotlin.reflect.KProperty

@Composable
fun <Model> FormScope<Model>.FormHiddenField(
    updatedField: KProperty<*>,
    value: String? = null,
    updateModel: Model.(String?) -> Unit = {}
) {
    FormBox(
        null,
        updateModel,
        { value },
        { },
        null
    ).addToForm(updatedField)
}
