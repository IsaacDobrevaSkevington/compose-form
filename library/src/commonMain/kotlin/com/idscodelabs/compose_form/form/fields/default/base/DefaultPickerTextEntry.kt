package com.idscodelabs.compose_form.form.fields.default.base

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.DatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import com.idscodelabs.compose_form.form.fields.default.text.DefaultTextEntry
import com.idscodelabs.compose_form.form.fields.strings.asDisplayString
import com.idscodelabs.compose_form.form.icons.Icons
import com.idscodelabs.compose_form.form.model.LocalFormBox
import com.idscodelabs.compose_form.utils.IconButton

@Composable
fun DefaultPickerTextEntry(
    value: TextFieldValue,
    setValue: (TextFieldValue) -> Unit,
    hint: Any? = null,
    modifier: Modifier = Modifier.fillMaxWidth(),
    allowTyping: Boolean = true,
    controller: PickerController<*>,
    placeholder: Any? = null,
    isLast: Boolean = false,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIconImage: ImageVector?,
    trailingIconButton: @Composable ((icon: ImageVector, onClick: () -> Unit) -> Unit)? = { icon, onClick ->
        IconButton(
            icon = icon,
            "Button to open picker.",
            onClick = onClick,
        )
    },
) {
    val enabled = LocalFormBox.current.enabled
    DefaultTextEntry(
        value = value,
        onValueChange = setValue,
        hint = hint?.asDisplayString(),
        modifier =
            modifier.onFocusChanged { focusState ->
                if (focusState.isFocused && !allowTyping && enabled) {
                    controller.setPickerVisible(true)
                }
            },
        trailingIcon =
            if (enabled && trailingIconButton != null && trailingIconImage != null) {
                {
                    trailingIconButton(trailingIconImage) {
                        controller.setPickerVisible(true)
                    }
                }
            } else {
                null
            },
        placeholder = placeholder,
        isLast = isLast,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        leadingIcon = leadingIcon,
        readOnly = !(allowTyping && enabled),
    )
}
