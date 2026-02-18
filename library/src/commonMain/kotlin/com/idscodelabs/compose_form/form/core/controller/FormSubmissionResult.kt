package com.idscodelabs.compose_form.form.core.controller

import com.idscodelabs.compose_form.form.model.FormBox

/**
 * Form submission result
 *
 * Result of a form submission allowing attaching callbacks
 */
interface FormSubmissionResult<Model> {
    /**
     * On error
     *
     * @param action Action called when there is an error in submission
     * @return A [FormSubmissionResult] for chaining
     */
    fun onError(action: (Throwable) -> Unit): FormSubmissionResult<Model> = this

    /**
     * On failure
     *
     * @param action Action called when there is a failure of submission.
     * The list of validation failed [FormBox] is provided as a parameter to the callback
     * @return A [FormSubmissionResult] for chaining
     */
    fun onFailure(action: (List<FormBox<Model, *>>) -> Unit): FormSubmissionResult<Model> = this

    /**
     * On success
     *
     * @param action Action called when the form submits successfully.
     * The resulting model is provided as a parameter to the callback
     * @return A [FormSubmissionResult] for chaining
     */
    fun onSuccess(action: (Model) -> Unit): FormSubmissionResult<Model> = this

    class Error<Model>(
        private val error: Throwable,
    ) : FormSubmissionResult<Model> {
        override fun onError(action: (Throwable) -> Unit): FormSubmissionResult<Model> {
            action(error)
            return super.onError(action)
        }
    }

    class Success<Model>(
        private val model: Model,
    ) : FormSubmissionResult<Model> {
        override fun onSuccess(action: (Model) -> Unit): FormSubmissionResult<Model> {
            action(model)
            return super.onSuccess(action)
        }
    }

    class Failure<Model>(
        private val boxes: List<FormBox<Model, *>>,
    ) : FormSubmissionResult<Model> {
        override fun onFailure(action: (List<FormBox<Model, *>>) -> Unit): FormSubmissionResult<Model> {
            action(boxes)
            return super.onFailure(action)
        }
    }
}
