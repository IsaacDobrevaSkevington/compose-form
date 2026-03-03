package com.idscodelabs.compose_form.json.screens.form

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.idscodelabs.compose_form.form.core.controller.FormController
import com.idscodelabs.compose_form.form.core.controller.FormSubmissionResult
import com.idscodelabs.compose_form.form.core.ui.Form
import com.idscodelabs.compose_form.json.model.JsonFormModel
import com.idscodelabs.compose_form.styles.LocalFormStyle

@Composable
fun JsonForm(
    json: String,
    container: @Composable FormController<JsonFormModel>.(contents: @Composable FormController<JsonFormModel>.() -> Unit) -> Unit = {
        Column(
            verticalArrangement = Arrangement.spacedBy(LocalFormStyle.current.fieldSpacing),
        ) {
            it()
        }
    },
    submitButton: @Composable (onClick: ()->Unit) -> Unit = {
        Button(it, modifier = Modifier.fillMaxWidth()){
            Text("Submit")
        }
    },
    onComplete: FormSubmissionResult<JsonFormModel>.() -> Unit
){
    Form(emptyModel = ::mutableMapOf, container = container){
        JsonFormFields(json)
        submitButton {
            submit().onComplete()
        }
    }

}

@Composable
fun JsonForm(
    json: String,
    controller: FormController<JsonFormModel>,
    container: @Composable FormController<JsonFormModel>.(contents: @Composable FormController<JsonFormModel>.() -> Unit) -> Unit = {
        Column(
            verticalArrangement = Arrangement.spacedBy(LocalFormStyle.current.fieldSpacing),
        ) {
            it()
        }
    },
    submitButton: @Composable (onClick: ()->Unit) -> Unit = {
        Button(it, modifier = Modifier.fillMaxWidth()){
            Text("Submit")
        }
    },
    onComplete: FormSubmissionResult<JsonFormModel>.() -> Unit
){
    Form(controller = controller, container = container){
        JsonFormFields(json)
        submitButton {
            submit().onComplete()
        }
    }

}
