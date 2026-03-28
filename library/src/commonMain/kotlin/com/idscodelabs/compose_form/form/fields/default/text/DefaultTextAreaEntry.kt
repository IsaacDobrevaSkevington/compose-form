package com.idscodelabs.compose_form.form.fields.default.text

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import com.idscodelabs.compose_form.form.fields.default.base.LocalFormTextFieldEntry
import com.idscodelabs.compose_form.form.model.FormBox
import com.idscodelabs.compose_form.styles.FormFieldStyle
import com.idscodelabs.compose_form.styles.LocalFormFieldStyle

@Composable
fun FormBox<*, TextFieldValue>.DefaultTextAreaEntry(
    hint: Any? = null,
    modifier: Modifier = Modifier.fillMaxWidth(),
    trailingIcon: (@Composable () -> Unit)? = null,
    placeholder: Any? = null,
    isLast: Boolean = false,
    keyboardOptions: KeyboardOptions? = null,
    prefix: Any = "",
    readOnly: Boolean = false,
    style: FormFieldStyle = LocalFormFieldStyle.current,
    onValueChange: (TextFieldValue) -> Unit = {},
    leadingIcon: (@Composable () -> Unit)? = null,
    minLines: Int = 4,
    maxLines: Int = 4,
) = DefaultTextAreaEntry(
    value = value,
    setValue = ::setValue,
    hint = hint,
    modifier = modifier.primaryFocusable(),
    trailingIcon = trailingIcon,
    placeholder = placeholder,
    isLast = isLast,
    keyboardOptions = keyboardOptions,
    prefix = prefix,
    readOnly = readOnly,
    style = style,
    onValueChange = onValueChange,
    leadingIcon = leadingIcon,
    minLines = minLines,
    maxLines = maxLines,
)

@Composable
fun DefaultTextAreaEntry(
    value: TextFieldValue,
    setValue: (TextFieldValue) -> Unit,
    hint: Any? = null,
    modifier: Modifier = Modifier.fillMaxWidth(),
    trailingIcon: (@Composable () -> Unit)? = null,
    placeholder: Any? = null,
    isLast: Boolean = false,
    keyboardOptions: KeyboardOptions? = null,
    prefix: Any = "",
    readOnly: Boolean = false,
    style: FormFieldStyle = LocalFormFieldStyle.current,
    onValueChange: (TextFieldValue) -> Unit = {},
    leadingIcon: (@Composable () -> Unit)? = null,
    minLines: Int = 4,
    maxLines: Int = 4,
) {
    with(LocalFormTextFieldEntry.current) {
        Render(
            value = value,
            onValueChange = {
                setValue(it)
                onValueChange(it)
            },
            hint = hint,
            modifier = modifier,
            isLast = isLast,
            trailingIcon = trailingIcon,
            prefix = prefix,
            readOnly = readOnly,
            style = style,
            keyboardOptions = keyboardOptions,
            leadingIcon = leadingIcon,
            placeholder = placeholder,
            minLines = minLines,
            maxLines = maxLines,
        )
    }
}
