package com.idscodelabs.compose_form.form.core

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.idscodelabs.compose_form.styles.LocalFormStyle

/**
 * A Compose Form
 *
 * @param Model The type this form will emit
 * @param emptyModel Constructor for the empty model
 * @param scope Form scope which will handle this form's actions
 * @param container The root level container for form fields
 * @param contents The contents of the form
 */
@Composable
fun <Model> Form(
    emptyModel: () -> Model,
    scope: FormScope<Model> = rememberFormScope(),
    container: @Composable FormScope<Model>.(contents: @Composable FormScope<Model>.() -> Unit) -> Unit = {
        Column(
            verticalArrangement = Arrangement.spacedBy(LocalFormStyle.current.fieldSpacing),
        ) {
            it()
        }
    },
    contents: @Composable FormScope<Model>.() -> Unit = {},
) {
    LaunchedEffect(emptyModel, scope) {
        scope.emptyModel = emptyModel
    }
    container(scope) {
        contents()
    }
}
