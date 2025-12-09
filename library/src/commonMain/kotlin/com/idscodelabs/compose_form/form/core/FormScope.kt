package com.idscodelabs.compose_form.form.core

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.idscodelabs.compose_form.form.core.exceptions.FormSubmissionFailedError
import com.idscodelabs.compose_form.form.model.FormBox
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext
import kotlin.reflect.KProperty

/**
 * Driver for the compose form
 *
 * This inherits from [ViewModel] for convenience, allowing application level [ViewModel]s to drive the form in the UI
 *
 * The [FormScope] can also be used standalone with no extra logic to drive lightweight, non-complex forms.
 *
 * @param Model The type which the form
 * @constructor Create empty Form scope
 *
 */
open class FormScope<Model> : ViewModel() {
    /**
     * Icon parameters for forms
     *
     * @property icon The icon vector
     * @property rotation The rotation of the icon
     * @property onClick Action to perform when the icon is clicked
     * @constructor Create empty Icon params
     */
    class IconParams(
        val icon: ImageVector,
        val rotation: Float = 0f,
        val onClick: () -> Unit,
    )

    private val boxes: MutableMap<String, FormBox<Model, *>> = mutableMapOf()
    private val observerJobs: MutableMap<String, Job> = mutableMapOf()
    lateinit var emptyModel: () -> Model

    /**
     * Clear all the form's values and boxes
     */
    open fun clear() {
        boxes.clear()
    }

    /**
     * Clear a subset of the form's values and boxes
     *
     * @param fields The fields to clear
     */
    open fun clear(vararg fields: KProperty<*>) {
        fields.forEach {
            boxes.remove(it.name)
        }
    }

    /**
     * Add a box to the form
     *
     * @param property The unique property of this form field
     */
    fun FormBox<Model, *>.addToForm(property: KProperty<*>) {
        boxes[property.name] = this
        observerJobs[property.name] =
            viewModelScope.launch {
                this@addToForm.onFieldValueChanged {
                    this@FormScope.valueFlow.update { this@FormScope.valueSnapshot }
                }
            }
    }

    /**
     * Remove a [FormBox] from the form
     *
     * @param property The unique property which this [FormBox] is associated with
     */
    fun removeFromForm(property: KProperty<*>) {
        boxes.remove(property.name)
        observerJobs.remove(property.name)?.cancel()
    }

    /**
     * Validate the form
     * @return A list of boxes which failed validation
     *
     * This function will handle setting and clearing errors on the correct boxes
     */
    open fun validate(): List<FormBox<Model, *>> =
        boxes
            .filter { (_, v) -> !v.validate() }
            .map { it.value }

    /**
     * The current value of the form. Any incomplete fields (e.g. wrong format) will not be populated,
     * and retain their default value from [emptyModel]
     */
    val valueSnapshot: Model
        get() {
            val updateRequest = emptyModel()
            boxes.forEach { (k, v) ->
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
    open fun submitForModel(): Model =
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

    fun field(property: KProperty<*>) = boxes[property.name]

    protected val valueFlow by lazy {
        MutableStateFlow(valueSnapshot)
    }

    /**
     * Collect the current value as state
     *
     * @return [State] of the current [Model]. Will only change when the [Model] changes
     */
    @Composable
    fun collectValueAsState(): State<Model> = valueFlow.collectAsState()

    /**
     * Subscribe to [Form] value changes
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
        valueFlow.debounce(debounceMillis).distinctUntilChanged().collectLatest {
            block(it)
        }
    }

    /**
     * Composition aware value change observation
     *
     * This function should be used to trigger side effects in [Composable]s when the value of the [Model] is
     * changed. The [block] will be cancelled if this leaves the composition
     *
     * @param context [CoroutineContext] in which to run the [block]
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
