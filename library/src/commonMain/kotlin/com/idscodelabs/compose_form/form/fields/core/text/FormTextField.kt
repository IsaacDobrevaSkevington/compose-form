package com.idscodelabs.compose_form.form.fields.core.text

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.input.TextFieldValue
import com.idscodelabs.compose_form.form.core.FormScope
import com.idscodelabs.compose_form.form.fields.core.base.FormFieldImplementation
import com.idscodelabs.compose_form.form.fields.core.base.TextFieldFormFieldWrapper
import com.idscodelabs.compose_form.validators.core.Validator
import kotlin.reflect.KProperty

@Composable
fun <Model> FormScope<Model>.FormTextField(
    modelProperty: KProperty<String?>,
    initialValue: String?,
    enabled: Boolean,
    validator: Validator?,
    updateModel: Model.(String?) -> Unit,
    implementation: FormFieldImplementation<TextFieldValue>,
) {
    TextFieldFormFieldWrapper(
        modelProperty = modelProperty,
        initialValue = initialValue,
        enabled = enabled,
        validator = validator,
        updateModel = updateModel,
        implementation = implementation,
        valueToString = { it },
        stringToValue = { it },
        { this },
    )
}
