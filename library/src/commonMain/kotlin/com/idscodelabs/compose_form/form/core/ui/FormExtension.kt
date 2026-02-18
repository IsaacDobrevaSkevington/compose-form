package com.idscodelabs.compose_form.form.core.ui

import androidx.compose.runtime.Composable
import com.idscodelabs.compose_form.form.core.controller.FormController
import com.idscodelabs.compose_form.form.core.controller.withLocalFormController
import com.idscodelabs.compose_form.form.core.controller.withUntypedLocalFormController

/**
 * Form extension
 *
 * Use this to avoid having to pass a [FormController] down the view hierarchy
 *
 * @param Model The type of model the form deals with
 * @param content The content of the form extension
 */
@Composable
fun <Model> FormExtension(content: @Composable FormController<Model>.() -> Unit) = withLocalFormController(content)

/**
 * Form extension without a type.
 *
 * Use this to avoid having to pass a [FormController] down the view hierarchy
 *
 * @param content The content of the form extension
 */
@Composable
fun UntypedFormExtension(content: @Composable FormController<*>.() -> Unit) = withUntypedLocalFormController(content)
