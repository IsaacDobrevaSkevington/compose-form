package com.idscodelabs.compose_form.form.fields.core.base

import androidx.compose.runtime.Composable
import com.idscodelabs.compose_form.form.model.FormBox

typealias IFormFieldImplementation<FormBox> = @Composable FormBox.() -> Unit
typealias FormFieldImplementation<Stored> = IFormFieldImplementation<FormBox<*, Stored>>
