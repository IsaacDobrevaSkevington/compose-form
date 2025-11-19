package com.idscodelabs.compose_form.form.fields.core.base

import androidx.compose.runtime.Composable
import androidx.compose.ui.focus.FocusRequester

typealias IFormFieldImplementation<Parameters> = @Composable Parameters.() -> Unit
typealias FormFieldImplementation<Stored> = IFormFieldImplementation<FormFieldImplementationParameters<Stored>>

abstract class AbstractFormFieldImplementationParameters<Stored>{
    abstract val value: Stored
    abstract val _setValue: (Stored) -> Unit
    abstract val error: String?
    abstract val enabled: Boolean
    abstract val focusRequester: FocusRequester
    fun setValue(value: Stored) = _setValue(value)
}

data class FormFieldImplementationParameters<Stored>(
    override val value: Stored,
    override val _setValue: (Stored) -> Unit,
    override val error: String?,
    override val enabled: Boolean,
    override val focusRequester: FocusRequester
): AbstractFormFieldImplementationParameters<Stored>()
