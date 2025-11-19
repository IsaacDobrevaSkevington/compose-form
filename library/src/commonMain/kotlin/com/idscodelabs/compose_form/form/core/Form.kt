package com.idscodelabs.compose_form.form.core

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.idscodelabs.compose_form.styles.LocalFormStyle

/**
 * Generic form without editing the realm
 * @param Model The result object
 * @param emptyModel Get a blank instance of the result object for this form
 * @param setLoading Function to setLoading on the parent
 * @param contents The contents of the form - receives blank [Model]
 */
@Composable
fun <Model> Form(
    emptyModel: () -> Model,
    scope: FormScope<Model> = rememberFormScope(),
    container: @Composable FormScope<Model>.(contents: @Composable FormScope<Model>.() -> Unit) -> Unit = {
        Column(
            verticalArrangement = Arrangement.spacedBy(LocalFormStyle.current.fieldSpacing)
        ) {
            it()
        }
    },
    contents: @Composable FormScope<Model>.() -> Unit = {}
) {
    LaunchedEffect(emptyModel, scope) {
        scope.emptyModel = emptyModel
    }
    container(scope){
        contents()
    }

}
