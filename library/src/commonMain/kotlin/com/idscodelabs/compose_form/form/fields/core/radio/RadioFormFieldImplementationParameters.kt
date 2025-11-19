package com.idscodelabs.compose_form.form.fields.core.radio

import androidx.compose.runtime.Composable
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.text.input.TextFieldValue
import com.idscodelabs.compose_form.form.fields.core.base.AbstractFormFieldImplementationParameters
import com.idscodelabs.compose_form.form.fields.core.base.ListDisplayable
import com.idscodelabs.compose_form.form.fields.strings.asDisplayString

data class RadioFormFieldImplementationParameters<Item : ListDisplayable>(
    override val value: Int,
    override val _setValue: (Int) -> Unit,
    override val error: String?,
    override val enabled: Boolean,
    override val focusRequester: FocusRequester,
    val options: List<Item>,
) : AbstractFormFieldImplementationParameters<Int>() {
    val sortedOptions by lazy {
        options.sortedBy { it.position }
    }

    val currentItem: Item? @Composable get() =
        value.takeIf { it != -1 }?.let {
            options[it]
        }

    constructor(params: AbstractFormFieldImplementationParameters<Int>, options: List<Item>) :
        this(
            value = params.value,
            _setValue = params._setValue,
            error = params.error,
            enabled = params.enabled,
            focusRequester = params.focusRequester,
            options = options,
        )
}
