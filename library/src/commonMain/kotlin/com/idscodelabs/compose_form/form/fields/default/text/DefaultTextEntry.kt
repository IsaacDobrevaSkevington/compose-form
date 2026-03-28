package com.idscodelabs.compose_form.form.fields.default.text

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import com.idscodelabs.compose_form.form.fields.default.base.DefaultFormTextFieldEntry
import com.idscodelabs.compose_form.form.fields.default.base.LocalFormTextFieldEntry
import com.idscodelabs.compose_form.form.model.FormBox
import com.idscodelabs.compose_form.styles.FormFieldStyle
import com.idscodelabs.compose_form.styles.LocalFormFieldStyle

@Composable
fun DefaultTextEntry(
    value: TextFieldValue,
    onValueChange: (TextFieldValue) -> Unit,
    hint: Any? = null,
    modifier: Modifier = Modifier.fillMaxWidth(),
    trailingIcon: (@Composable () -> Unit)? = null,
    placeholder: Any? = null,
    isLast: Boolean = false,
    keyboardOptions: KeyboardOptions? = null,
    prefix: Any = "",
    readOnly: Boolean = false,
    style: FormFieldStyle = LocalFormFieldStyle.current,
    leadingIcon: (@Composable () -> Unit)? = null,
) {
    with(LocalFormTextFieldEntry.current) {
        Render(
            hint = hint,
            modifier = modifier,
            isLast = isLast,
            trailingIcon = trailingIcon,
            prefix = prefix,
            readOnly = readOnly,
            style = style,
            value = value,
            onValueChange = onValueChange,
            keyboardOptions = keyboardOptions,
            leadingIcon = leadingIcon,
            placeholder = placeholder,
        )
    }
}

@Composable
fun FormBox<*, TextFieldValue>.DefaultTextEntry(
    hint: Any? = null,
    modifier: Modifier = Modifier.fillMaxWidth(),
    trailingIcon: (@Composable () -> Unit)? = null,
    placeholder: Any? = null,
    isLast: Boolean = false,
    keyboardOptions: KeyboardOptions? = null,
    prefix: Any = "",
    readOnly: Boolean = false,
    onValueChange: (TextFieldValue) -> Unit = {},
    style: FormFieldStyle = LocalFormFieldStyle.current,
    leadingIcon: (@Composable () -> Unit)? = null,
) = DefaultTextEntry(
    value = value,
    onValueChange = {
        setValue(it)
        onValueChange(it)
    },
    hint = hint,
    modifier = modifier.primaryFocusable(),
    isLast = isLast,
    trailingIcon = trailingIcon,
    prefix = prefix,
    readOnly = readOnly,
    style = style,
    keyboardOptions = keyboardOptions,
    leadingIcon = leadingIcon,
    placeholder = placeholder,
)
