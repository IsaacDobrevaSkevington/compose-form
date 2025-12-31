package com.idscodelabs.compose_form.form.core.controller

import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import com.idscodelabs.compose_form.form.model.FormBox
import com.idscodelabs.compose_form.form.model.FormControllerState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow

/**
 * Basic implementation of the form controller
 *
 * @param Model The type which the form populates
 * @property emptyModel An empty version of the [Model] to be populated
 * @property lifecycleScope Scope in which coroutines should run
 */
open class BasicFormController<Model>(
    override var emptyModel: () -> Model,
    override var lifecycleScope: CoroutineScope,
) : FormController<Model> {
    override val state: FormControllerState<Model> = FormControllerState(emptyModel())
}

@Composable
fun <Model> rememberFormController(emptyModel: () -> Model): FormController<Model> {
    val controllerScope = rememberCoroutineScope()
    return BasicFormController(emptyModel, controllerScope)
}
