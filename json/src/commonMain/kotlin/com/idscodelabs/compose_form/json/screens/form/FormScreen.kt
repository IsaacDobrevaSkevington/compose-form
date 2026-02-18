package com.idscodelabs.compose_form.json.screens.form

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.produceState
import com.idscodelabs.compose_form.form.core.controller.FormController
import com.idscodelabs.compose_form.form.core.controller.FormSubmissionResult
import com.idscodelabs.compose_form.form.core.ui.Form
import com.idscodelabs.compose_form.json.fields.models.FieldModel
import com.idscodelabs.compose_form.json.fields.serialization.LocalFormTypes
import com.idscodelabs.compose_form.json.model.JsonFormModel

@Composable
fun FormController<JsonFormModel>.JsonFormFields(
    json: String,
    loading: @Composable () -> Unit = {},
){

    val formTypesJson = LocalFormTypes.current.json
    val fields by produceState<List<FieldModel>?>(null){
        value = formTypesJson.decodeFromString<List<FieldModel>>(json)
    }

    fields?.let {
        JsonFormFields(it)
    } ?: loading()
}



@Composable
fun FormController<JsonFormModel>.JsonFormFields(
    fields: List<FieldModel>,
){
    fields.forEach {
        with(it){
            Render()
        }
    }
}