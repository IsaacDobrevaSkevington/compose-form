package com.idscodelabs.compose_form.form.fields.default.text

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import com.idscodelabs.compose_form.form.core.FormScope
import com.idscodelabs.compose_form.form.fields.default.base.LocalFormTextFieldEntry
import com.idscodelabs.compose_form.form.fields.strings.asDisplayString
import com.idscodelabs.compose_form.form.icons.Icons
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
