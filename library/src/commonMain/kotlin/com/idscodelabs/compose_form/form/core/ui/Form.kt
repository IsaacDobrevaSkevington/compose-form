package com.idscodelabs.compose_form.form.core.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.idscodelabs.compose_form.form.core.controller.FormController
import com.idscodelabs.compose_form.form.core.controller.rememberFormController
import com.idscodelabs.compose_form.styles.LocalFormStyle

/**
 * A Compose Form
 *
 * @param Model The type this form will emit
 * @param emptyModel Constructor for the empty model
 * @param container The root level container for form fields
 * @param contents The contents of the form
 */
@Composable
fun <Model : Any> Form(
    emptyModel: () -> Model,
    container: @Composable FormController<Model>.(contents: @Composable FormController<Model>.() -> Unit) -> Unit = {
        Column(
            verticalArrangement = Arrangement.spacedBy(LocalFormStyle.current.fieldSpacing),
        ) {
            it()
        }
    },
    contents: @Composable FormController<Model>.() -> Unit = {},
) {
    val controller: FormController<Model> = rememberFormController(emptyModel)
    remember(emptyModel, controller) {
        controller.emptyModel = emptyModel
    }
    container(controller) {
        contents()
    }
}

/**
 * A Compose Form
 *
 * @param Model The type this form will emit
 * @param controller Form controller which will handle this form's actions
 * @param container The root level container for form fields
 * @param contents The contents of the form
 */
@Composable
fun <Model : Any> Form(
    controller: FormController<Model>,
    container: @Composable FormController<Model>.(contents: @Composable FormController<Model>.() -> Unit) -> Unit = {
        Column(
            verticalArrangement = Arrangement.spacedBy(LocalFormStyle.current.fieldSpacing),
        ) {
            it()
        }
    },
    contents: @Composable FormController<Model>.() -> Unit = {},
) {
    container(controller) {
        contents()
    }
}
