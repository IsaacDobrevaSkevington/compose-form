package com.idscodelabs.compose_form.form.model

import androidx.compose.ui.focus.FocusRequester
import com.idscodelabs.compose_form.validators.core.Validator

class FormBox<Model>(
    val validator: Validator?,
    val setModelProperty: Model.(String?) -> Unit,
    val getFieldValue: () -> String?,
    val setError: (Any?) -> Unit,
    val focusRequester: FocusRequester?,
)
