package com.idscodelabs.compose_form.form.fields.default.base

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import com.idscodelabs.compose_form.form.model.FormBox
import com.idscodelabs.compose_form.styles.FormFieldStyle
import com.idscodelabs.compose_form.styles.LocalFormFieldStyle

/**
 * Local form text field entry
 *
 * Use this to override the text box used in text based fields without having to reimplement all
 * the fields
 */
val LocalFormTextFieldEntry = compositionLocalOf<FormTextFieldEntry> { DefaultFormTextFieldEntry() }

interface FormTextFieldEntry {
    @Composable
    fun FormBox<*, TextFieldValue>.Render(
        hint: Any? = null,
        modifier: Modifier = Modifier.fillMaxWidth(),
        trailingIcon: (@Composable () -> Unit)? = null,
        placeholder: Any? = null,
        isLast: Boolean = false,
        keyboardOptions: KeyboardOptions? = null,
        keyboardActions: KeyboardActions = KeyboardActions.Default,
        prefix: Any = "",
        readOnly: Boolean = false,
        style: FormFieldStyle = LocalFormFieldStyle.current,
        onValueChange: (TextFieldValue) -> Unit = {},
        leadingIcon: (@Composable () -> Unit)? = null,
        minLines: Int = 1,
        maxLines: Int = 1,
    )
}
