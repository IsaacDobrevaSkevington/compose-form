package com.idscodelabs.compose_form.form.core.controller

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.idscodelabs.compose_form.form.model.FormBox
import com.idscodelabs.compose_form.form.model.FormControllerState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow

/**
 * View Model for controlling forms
 *
 * In an app which uses [ViewModel], each screen [ViewModel] can inherit from this class to separate business
 * logic from the UI layer.
 *
 * @param Model The type which the form populates
 * @property emptyModel An empty version of the [Model] to be populated
 * @sample com.idscodelabs.compose_form.examples.logic.viewmodel.FormViewModelExample
 */
abstract class FormControllerViewModel<Model>(
    override var emptyModel: () -> Model,
) : ViewModel(),
    FormController<Model> {
    override val state: FormControllerState<Model> = FormControllerState(emptyModel())
    override var lifecycleScope: CoroutineScope = viewModelScope
}
