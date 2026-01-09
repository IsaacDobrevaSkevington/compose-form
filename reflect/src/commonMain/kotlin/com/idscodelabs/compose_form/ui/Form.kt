package com.idscodelabs.compose_form.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.idscodelabs.compose_form.form.core.controller.FormController
import com.idscodelabs.compose_form.form.core.ui.Form
import com.idscodelabs.compose_form.styles.LocalFormStyle
import kotlin.reflect.KParameter

/**
 * A Compose Form
 *
 * @param Model The type this form will emit
 * @param emptyModel Constructor for the empty model
 * @param container The root level container for form fields
 * @param contents The contents of the form
 */
@Composable
inline fun <reified Model : Any> Form(
    noinline container: @Composable FormController<Model>.(contents: @Composable FormController<Model>.() -> Unit) -> Unit = {
        Column(
            verticalArrangement = Arrangement.spacedBy(LocalFormStyle.current.fieldSpacing),
        ) {
            it()
        }
    },
    noinline contents: @Composable FormController<Model>.() -> Unit = {},
) {
    val emptyModel =
        remember {
            Model::class.constructors.firstOrNull { it.parameters.all(KParameter::isOptional) }?.let { { it.callBy(emptyMap()) } }
                ?: throw IllegalArgumentException(
                    "${Model::class.simpleName} must have a zero-argument constructor to be used in the form.",
                )
        }
    Form(emptyModel, container, contents)
}
