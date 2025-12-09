package com.idscodelabs.compose_form.form.core.exceptions

import com.idscodelabs.compose_form.form.model.FormBox

class FormSubmissionFailedError(
    val boxes: List<FormBox<*, *>>,
    cause: Throwable?,
): RuntimeException(cause)