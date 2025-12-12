package com.idscodelabs.compose_form.form.core.controller

import com.idscodelabs.compose_form.form.model.FormBox

interface FormSubmissionResult<Model> {
    fun onError(action: (Throwable) -> Unit): FormSubmissionResult<Model> = this

    fun onFailure(action: (List<FormBox<Model, *>>) -> Unit): FormSubmissionResult<Model> = this

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
