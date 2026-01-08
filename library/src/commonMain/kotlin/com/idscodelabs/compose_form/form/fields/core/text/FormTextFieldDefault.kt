package com.idscodelabs.compose_form.form.fields.core.text

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import com.idscodelabs.compose_form.form.core.controller.FormController
import com.idscodelabs.compose_form.form.fields.default.text.DefaultTextEntry
import com.idscodelabs.compose_form.styles.FormFieldStyle
import com.idscodelabs.compose_form.styles.LocalFormFieldStyle
import com.idscodelabs.compose_form.validators.core.Validator
import kotlin.reflect.KProperty

@Composable
fun <Model> FormController<Model>.FormTextField(
    modelProperty: KProperty<*>,
    updateModel: Model.(String?) -> Unit,
    initialValue: String? = null,
    validator: Validator<String>? = null,
    enabled: Boolean = true,
    hint: Any? = null,
    modifier: Modifier = Modifier.fillMaxWidth(),
    trailingIcon: (@Composable () -> Unit)? = null,
    placeholder: Any? = null,
    isLast: Boolean = false,
    keyboardOptions: KeyboardOptions? = null,
    prefix: Any = "",
    readOnly: Boolean = false,
    onValueChange: (TextFieldValue) -> Unit = {},
    leadingIcon: (@Composable () -> Unit)? = null,
    style: FormFieldStyle = LocalFormFieldStyle.current,
) = FormTextField(
    modelProperty = modelProperty,
    updateModel = updateModel,
    initialValue = initialValue,
    validator = validator,
    enabled = enabled,
    implementation = {
        DefaultTextEntry(
            hint = hint,
            modifier = modifier,
            trailingIcon = trailingIcon,
            placeholder = placeholder,
            isLast = isLast,
            keyboardOptions = keyboardOptions,
            prefix = prefix,
            readOnly = readOnly,
            style = style,
            onValueChange = onValueChange,
            leadingIcon = leadingIcon,
        )
    },
)
