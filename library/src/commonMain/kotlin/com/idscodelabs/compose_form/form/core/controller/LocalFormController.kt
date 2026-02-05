package com.idscodelabs.compose_form.form.core.controller

import androidx.compose.runtime.Composable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.remember

/**
 * Current form controller
 */
val LocalFormController = compositionLocalOf<FormController<*>?> { null }

/**
 * Perform action in the context of the current [FormController]
 *
 * Use this to avoid having to pass a [FormController] down the view hierarchy
 *
 * @param Model The type of model the form deals with
 * @param block The action to execute with the [FormController]
 */
@Composable
fun <Model> withLocalFormController(block: @Composable FormController<Model>.()->Unit) {
    val currentController = LocalFormController.current

    remember(currentController){
        try{
            currentController as? FormController<Model>
        } catch (_: Throwable){
            null
        }
    }?.block()
}

/**
 * Perform action in the context of the current untyped [FormController]
 *
 * Use this to avoid having to pass a [FormController] down the view hierarchy
 *
 * @param block The action to execute with the [FormController]
 */
@Composable
fun withUntypedLocalFormController(block: @Composable FormController<*>.()->Unit) {
    LocalFormController.current?.block()
}