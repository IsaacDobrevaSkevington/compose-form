package com.idscodelabs.compose_form.form.core

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import com.idscodelabs.compose_form.styles.LocalFormStyle

/**
 * A Compose Form
 *
 * @param Model The type this form will emit
 * @param emptyModel Constructor for the empty model
 * @param viewModel Form viewmodel which will handle this form's actions
 * @param container The root level container for form fields
 * @param contents The contents of the form
 */
@Composable
fun <Model> Form(
    emptyModel: () -> Model,
    viewModel: FormViewModel<Model> = viewModel { FormViewModel() },
    container: @Composable FormViewModel<Model>.(contents: @Composable FormViewModel<Model>.() -> Unit) -> Unit = {
        Column(
            verticalArrangement = Arrangement.spacedBy(LocalFormStyle.current.fieldSpacing),
        ) {
            it()
        }
    },
    contents: @Composable FormViewModel<Model>.() -> Unit = {},
) {
    remember(emptyModel, viewModel) {
        viewModel.emptyModel = emptyModel
    }
    container(viewModel) {
        contents()
    }
}
