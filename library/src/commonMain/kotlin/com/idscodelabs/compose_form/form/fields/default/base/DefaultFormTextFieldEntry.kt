package com.idscodelabs.compose_form.form.fields.default.base

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import com.idscodelabs.compose_form.form.fields.strings.asDisplayString
import com.idscodelabs.compose_form.form.icons.Icons
import com.idscodelabs.compose_form.form.model.FormBox
import com.idscodelabs.compose_form.styles.FormFieldStyle

class DefaultFormTextFieldEntry : FormTextFieldEntry {
    @Composable
    override fun FormBox<*, TextFieldValue>.Render(
        hint: Any?,
        modifier: Modifier,
        trailingIcon: @Composable (() -> Unit)?,
        placeholder: Any?,
        isLast: Boolean,
        keyboardOptions: KeyboardOptions?,
        keyboardActions: KeyboardActions,
        prefix: Any,
        readOnly: Boolean,
        style: FormFieldStyle,
        onValueChange: (TextFieldValue) -> Unit,
        leadingIcon: @Composable (() -> Unit)?,
        minLines: Int,
        maxLines: Int,
    ) = OutlinedTextField(
        value = value,
        prefix = { Text(prefix.asDisplayString()) },
        onValueChange = {
            setValue(it)
            onValueChange(it)
        },
        readOnly = readOnly,
        placeholder =
            placeholder?.let {
                { Text(it.asDisplayString(), softWrap = false) }
            },
        singleLine = minLines == 1 && maxLines == 1,
        minLines = minLines,
        maxLines = maxLines,
        isError = error != null,
        supportingText = {
            error?.let {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = it,
                    color = MaterialTheme.colorScheme.error,
                    fontWeight = FontWeight.Normal,
                )
            }
        },
        trailingIcon = {
            val tint =
                if (error == null) {
                    LocalContentColor.current
                } else {
                    MaterialTheme.colorScheme.error
                }
            CompositionLocalProvider(LocalContentColor provides tint) {
                trailingIcon?.invoke() ?: error?.let {
                    Icon(
                        Icons.Error,
                        "error",
                        tint = MaterialTheme.colorScheme.error,
                    )
                }
            }
        },
        leadingIcon = leadingIcon,
        label = {
            hint?.let {
                Text(
                    text = it.asDisplayString(),
                    fontWeight = FontWeight.Normal,
                    color =
                        if (error != null) {
                            MaterialTheme.colorScheme.error
                        } else {
                            style.labelColor()
                        },
                    softWrap = false,
                )
            }
        },
        modifier = modifier.primaryFocusable(),
        shape = style.shape(),
        enabled = enabled,
        colors = style.colors(),
        keyboardOptions =
            (keyboardOptions ?: KeyboardOptions()).copy(
                imeAction =
                    keyboardOptions?.imeAction.takeIf { it != ImeAction.Unspecified }
                        ?: if (isLast) ImeAction.Done else ImeAction.Next,
            ),
        keyboardActions = keyboardActions,
    )
}
