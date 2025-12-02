package com.idscodelabs.compose_form.form.fields.default.text

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Error
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import com.idscodelabs.compose_form.form.core.FormScope
import com.idscodelabs.compose_form.form.fields.strings.asDisplayString
import com.idscodelabs.compose_form.form.model.FormBox
import com.idscodelabs.compose_form.styles.FormFieldStyle
import com.idscodelabs.compose_form.styles.LocalFormFieldStyle

@Composable
fun FormBox<*, TextFieldValue>.DefaultTextEntry(
    hint: Any? = null,
    modifier: Modifier = Modifier,
    icon: FormScope.IconParams?,
    placeholder: Any? = null,
    isLast: Boolean = false,
    keyboardOptions: KeyboardOptions? = null,
    prefix: Any = "",
    readOnly: Boolean = false,
    style: FormFieldStyle = LocalFormFieldStyle.current,
    onValueChange: (TextFieldValue) -> Unit = {},
    leadingIcon: (@Composable () -> Unit)? = null,
) = DefaultTextEntry(
    hint,
    modifier,
    listOfNotNull(icon),
    placeholder,
    isLast,
    keyboardOptions,
    prefix,
    readOnly,
    style,
    onValueChange,
    leadingIcon,
)

@Composable
fun FormBox<*, TextFieldValue>.DefaultTextEntry(
    hint: Any? = null,
    modifier: Modifier = Modifier.fillMaxWidth(),
    icons: List<FormScope.IconParams> = emptyList(),
    placeholder: Any? = null,
    isLast: Boolean = false,
    keyboardOptions: KeyboardOptions? = null,
    prefix: Any = "",
    readOnly: Boolean = false,
    style: FormFieldStyle = LocalFormFieldStyle.current,
    onValueChange: (TextFieldValue) -> Unit = {},
    leadingIcon: (@Composable () -> Unit)? = null,
) {
    OutlinedTextField(
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
        singleLine = true,
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
            icons.ifEmpty { null }?.let {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    it.forEach { icon ->
                        IconButton(onClick = icon.onClick, enabled = enabled) {
                            Icon(
                                icon.icon,
                                "",
                                tint =
                                    if (error == null) {
                                        MaterialTheme.colorScheme.onBackground
                                    } else {
                                        MaterialTheme.colorScheme.error
                                    },
                                modifier = Modifier.rotate(icon.rotation),
                            )
                        }
                    }
                }
            } ?: error?.let {
                Icon(
                    Icons.Filled.Error,
                    "error",
                    tint = MaterialTheme.colorScheme.error,
                )
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
    )
}
