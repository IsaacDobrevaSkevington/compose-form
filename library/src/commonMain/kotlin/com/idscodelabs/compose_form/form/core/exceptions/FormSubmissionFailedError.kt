package com.idscodelabs.compose_form.form.core.exceptions

import com.idscodelabs.compose_form.form.model.FormBox

/**
 * Form submission failed error
 *
 * Error thrown on failure of the form submission
 * @param boxes List of failed boxes, may be empty
 * @param cause Error causing the failure, may be null
 *
 * Catchers of this error should check that the [cause] is null before checking for [boxes] which have failed
 */
class FormSubmissionFailedError(
    val boxes: List<FormBox<*, *>>,
    cause: Throwable?,
) : RuntimeException(cause)
