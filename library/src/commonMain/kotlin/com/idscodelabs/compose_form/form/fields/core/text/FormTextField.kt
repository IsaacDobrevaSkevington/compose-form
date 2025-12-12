package com.idscodelabs.compose_form.form.fields.core.text

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.input.TextFieldValue
import com.idscodelabs.compose_form.form.core.controller.FormController
import com.idscodelabs.compose_form.form.fields.core.base.FormFieldImplementation
import com.idscodelabs.compose_form.form.fields.core.base.TextFieldFormFieldWrapper
import com.idscodelabs.compose_form.validators.core.Validator
import kotlin.reflect.KProperty

@Composable
fun <Model> FormController<Model>.FormTextField(
    modelProperty: KProperty<*>,
    updateModel: Model.(String?) -> Unit,
    initialValue: String? = null,
    validator: Validator<String>? = null,
    enabled: Boolean = true,
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
