package com.idscodelabs.compose_form.form.fields.core.base

import androidx.compose.runtime.Composable
import com.idscodelabs.compose_form.form.model.FormBox

/**
 * A generic implementation for a Form Field
 *
 *
 * @param [FormBox] The type of Form Box handler which will be passed to the implementation function
 *
 */
typealias IFormFieldImplementation<FormBox> = @Composable FormBox.() -> Unit
typealias FormFieldImplementation<Stored> = IFormFieldImplementation<FormBox<*, Stored>>
