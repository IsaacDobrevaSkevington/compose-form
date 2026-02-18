package com.idscodelabs.compose_form.json.fields.models

import androidx.compose.runtime.Composable
import com.idscodelabs.compose_form.form.core.controller.FormController
import com.idscodelabs.compose_form.json.model.JsonFormModel
import com.idscodelabs.compose_form.utils.VirtualKProperty

interface FieldModel {

    val name: String

    @Composable
    fun FormController<JsonFormModel>.Render()

    fun modelProperty() = VirtualKProperty(name)

    fun <T> updateModel(valueToString: (T)->String? = Any?::toString): JsonFormModel.(T)->Unit = {
        this[name] = valueToString(it)
    }
}