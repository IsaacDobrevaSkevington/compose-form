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
fun FormBox<*, TextFieldValue>.DefaultTextEntry(
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
            onValueChange = onValueChange,
            keyboardOptions = keyboardOptions,
            leadingIcon = leadingIcon,
            placeholder = placeholder,
        )
    }
}
