package com.idscodelabs.compose_form.form.fields.core.text

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import com.idscodelabs.compose_form.form.core.controller.FormController
import com.idscodelabs.compose_form.styles.FormFieldStyle
import com.idscodelabs.compose_form.styles.LocalFormFieldStyle
import com.idscodelabs.compose_form.utils.hint
import com.idscodelabs.compose_form.utils.updateModel
import com.idscodelabs.compose_form.utils.validator
import com.idscodelabs.compose_form.validators.core.Validator
import kotlin.reflect.KMutableProperty

@Composable
fun <Model> FormController<Model>.FormTextField(
    modelProperty: KMutableProperty<String?>,
    initialValue: String? = null,
    validator: Validator<String>? = modelProperty.validator(),
    enabled: Boolean = true,
    hint: Any? = modelProperty.hint(),
    modifier: Modifier = Modifier.fillMaxWidth(),
    trailingIcon: (@Composable () -> Unit)? = null,
    placeholder: Any? = null,
    isLast: Boolean = false,
    keyboardOptions: KeyboardOptions? = null,
    prefix: Any = "",
    readOnly: Boolean = false,
    onValueChange: (TextFieldValue) -> Unit = {},
    leadingIcon: (@Composable () -> Unit)? = null,
    style: FormFieldStyle = LocalFormFieldStyle.current
) = FormTextField(
    modelProperty = modelProperty,
    updateModel = modelProperty.updateModel(),
    initialValue = initialValue,
    validator = validator,
    enabled = enabled,
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
