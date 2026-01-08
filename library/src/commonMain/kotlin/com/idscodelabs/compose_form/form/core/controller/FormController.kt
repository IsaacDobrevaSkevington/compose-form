package com.idscodelabs.compose_form.form.core.controller

import androidx.compose.runtime.*
import com.idscodelabs.compose_form.form.core.exceptions.FormSubmissionFailedError
import com.idscodelabs.compose_form.form.model.FormBox
import com.idscodelabs.compose_form.form.model.FormBoxFlow
import com.idscodelabs.compose_form.form.model.FormControllerState
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext
import kotlin.reflect.KProperty

/**
 * Driver for the compose form
 *
 * This inherits from [androidx.lifecycle.ViewModel] for convenience, allowing application level [androidx.lifecycle.ViewModel]s to drive the form in the UI
 *
 * The [FormController] can also be used standalone with no extra logic to drive lightweight, non-complex forms.
 *
 * @param Model The type which the form populates
 *
 */
@Suppress("UNCHECKED_CAST")
interface FormController<Model> {
    /**
     * Scope in which coroutines should run
     */
    val lifecycleScope: CoroutineScope get() = CoroutineScope(Dispatchers.Default)

    /**
     * An empty version of the [Model] to be populated
     */
    var emptyModel: () -> Model

    /**
     * State of the form controller
     */
    val state: FormControllerState<Model>

    /**
     * Clear all the form's values and boxes
     */
    fun clearForm() {
        state.boxes.clear()
    }

    /**
     * Clear a subset of the form's values and boxes
     *
     * @param fields The fields to clear
     */
    fun clearForm(vararg fields: KProperty<*>) {
        fields.forEach {
            state.boxes.remove(it.name)
        }
    }

    /**
     * Add a box to the form
     *
     * @param property The unique property of this form field
     */
    private fun FormBox<Model, *>.addToForm(property: KProperty<*>) {
        state.boxes[property.name] = this
        state.boxFlows[property.name]?.value = this
        state.observerJobs[property.name] =
            lifecycleScope.launch {
                this@addToForm.onFieldValueChanged {
                    this@FormController.state.valueFlow.update { this@FormController.valueSnapshot }
                }
            }
    }

    /**
     * Remove a [FormBox] from the form
     *
     * @param property The unique property which this [FormBox] is associated with
     */
    private fun removeFromForm(property: KProperty<*>) {
        state.boxes.remove(property.name)
        state.observerJobs.remove(property.name)?.cancel()
    }

    /**
     * Validate the form
     * @return A list of boxes which failed validation
     *
     * This function will handle setting and clearing errors on the correct boxes
     */
    fun validate(): List<FormBox<Model, *>> =
        state.boxes
            .filter { (_, v) -> !v.validate() }
            .map { it.value }

    /**
     * The current value of the form. Any incomplete fields (e.g. wrong format) will not be populated,
     * and retain their default value from [emptyModel]
     */
    val valueSnapshot: Model
        get() {
            val updateRequest = emptyModel()
            state.boxes.forEach { (k, v) ->
                try {
                    val currentValue = v.getStringValue()
                    v.setModelProperty(updateRequest, currentValue)
                } catch (e: Throwable) {
                    println(e.message ?: "Unknown error while parsing field $k")
                }
            }
            return updateRequest
        }

    /**
     * Submit the form
     *
     * @param onFailure Called when one or more fields fail validation.
     * The list of [FormBox]es which failed is provided as a parameter.
     * This allows, for example, to use the [FormBox.focusRequester] to bring the invalid field into focus.
     * @param onError Called when the form submission logic throws an error.
     * This could be due to one of the validators throwing an unexpected error.
     * The error which occurred is provided as a parameter.
     * @param onSuccess Called when the form submission succeeds.
     * The resulting [Model] is provided as a parameter to avoid having to call [valueSnapshot] again.
     */
    fun submit(
        onFailure: (List<FormBox<Model, *>>) -> Unit = {},
        onError: (Throwable) -> Unit = {},
        onSuccess: (Model) -> Unit = {},
    ) {
        try {
            try {
                onSuccess(submitForModel())
            } catch (e: FormSubmissionFailedError) {
                e.cause?.let(onError) ?: onFailure(e.boxes as List<FormBox<Model, *>>)
            }
        } catch (e: Throwable) {
            onError(e)
        }
    }

    @Throws(FormSubmissionFailedError::class)
    fun submitForModel(): Model =
        try {
            val failedBoxes = validate()
            if (failedBoxes.isEmpty()) {
                valueSnapshot
            } else {
                throw FormSubmissionFailedError(failedBoxes, null)
            }
        } catch (e: Throwable) {
            throw FormSubmissionFailedError(emptyList(), e)
        }

    fun submitForModelOrNull(): Model? =
        try {
            submitForModel()
        } catch (_: Throwable) {
            null
        }

    fun submit(): FormSubmissionResult<Model> =
        try {
            FormSubmissionResult.Success(submitForModel())
        } catch (e: FormSubmissionFailedError) {
            e.cause?.let {
                FormSubmissionResult.Error(it)
            } ?: FormSubmissionResult.Failure(e.boxes as List<FormBox<Model, *>>)
        }

    fun submitFunction(
        onFailure: (List<FormBox<Model, *>>) -> Unit = {},
        onError: (Throwable) -> Unit = {},
        onSuccess: (Model) -> Unit = {},
    ): () -> Unit = { submit(onFailure, onError, onSuccess) }

    fun <Value> fieldSnapshotWithType(property: KProperty<*>) =
        try {
            fieldSnapshot(property) as? FormBox<Model, Value>?
        } catch (_: Throwable) {
            null
        }

    fun fieldSnapshot(property: KProperty<*>) = state.boxes[property.name]

    fun <Value> fieldWithType(property: KProperty<*>): FormBoxFlow<Model, Value>? =
        try {
            field(property) as? FormBoxFlow<Model, Value>?
        } catch (e: Throwable) {
            null
        }

    fun field(property: KProperty<*>): FormBoxFlow<Model, *>? {
        var currentFlow = state.boxFlows[property.name]
        if (currentFlow == null) {
            val newFlow = MutableStateFlow(fieldSnapshot(property))
            state.boxFlows[property.name] = newFlow
            currentFlow = newFlow
        }
        return currentFlow
    }

    /**
     * Collect the current value as state
     *
     * @param context [CoroutineContext] to use for collecting.
     * @return [androidx.compose.runtime.State] of the current [Model]. Will only change when the [Model] changes
     */
    @Composable
    fun collectValueAsState(context: CoroutineContext = EmptyCoroutineContext): State<Model> = state.valueFlow.collectAsState(context)

    /**
     * Subscribe to [com.idscodelabs.compose_form.form.core.ui.Form] value changes
     *
     * This function should be used to observe value changes
     *
     * @param debounceMillis Debounce for the value to allow the value to settle
     * @param block Executed when a change is detected in [Model] assuming no further changes are detected for [debounceMillis]
     * The [Model] is provided as as the receiver
     */
    @OptIn(FlowPreview::class)
    suspend fun onValueChanged(
        debounceMillis: Long = 100,
        block: suspend Model.() -> Unit,
    ) {
        state.valueFlow.debounce(debounceMillis).distinctUntilChanged().collectLatest {
            block(it)
        }
    }

    /**
     * Composition aware value change observation
     *
     * This function should be used to trigger side effects in [Composable]s when the value of the [Model] is
     * changed. The [block] will be cancelled if this leaves the composition
     *
     * @param context [kotlin.coroutines.CoroutineContext] in which to run the [block]
     * @param debounceMillis Debounce for the value to allow the value to settle
     * @param block Executed when a change is detected in [Model] assuming no further changes are detected for [debounceMillis]
     */
    @Composable
    fun ValueChangedEffect(
        context: CoroutineContext = EmptyCoroutineContext,
        debounceMillis: Long = 100,
        block: suspend Model.() -> Unit,
    ) {
        LaunchedEffect(Unit) {
            if (context == EmptyCoroutineContext) {
                onValueChanged(debounceMillis, block)
            } else {
                withContext(context) { onValueChanged(debounceMillis, block) }
            }
        }
    }

    /**
     * Bind a [FormBox] lifecycle to this form
     *
     * @param modelProperty
     */
    @Composable
    fun FormBox<Model, *>.BindLifecycle(modelProperty: KProperty<*>) {
        DisposableEffect(modelProperty.name, this) {
            addToForm(modelProperty)

            onDispose {
                removeFromForm(modelProperty)
            }
        }
    }
}
