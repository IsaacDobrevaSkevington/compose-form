package com.idscodelabs.compose_form.json.fields.models

import androidx.compose.runtime.Composable
import com.idscodelabs.compose_form.form.core.controller.FormController
import com.idscodelabs.compose_form.form.fields.core.text.FormTextField
import com.idscodelabs.compose_form.json.fields.models.shared.IconModel
import com.idscodelabs.compose_form.json.fields.models.shared.KeyboardModel
import com.idscodelabs.compose_form.json.fields.models.shared.ValidatorModel
import com.idscodelabs.compose_form.json.fields.selectors.FormFieldType
import com.idscodelabs.compose_form.json.model.JsonFormModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(FormFieldType.TextField)
data class FormTextFieldModel(
    override val name: String,
    val initialValue: String? = null,
    val validator: ValidatorModel? = null,
    val enabled: Boolean = true,
    val hint: String? = null,
    val trailingIcon: IconModel? = null,
    val placeholder: String? = null,
    val keyboardOptions: KeyboardModel? = null,
    val prefix: String = "",
    val readOnly: Boolean = false,
    val leadingIcon: IconModel? = null,
): FieldModel{
    @Composable
    override fun FormController<JsonFormModel>.Render() = FormTextField(
        modelProperty = modelProperty(),
        updateModel = updateModel(),
        validator = validator.toValidator(),
        initialValue = initialValue,
        enabled = enabled,
        hint = hint,
        placeholder = placeholder,
        keyboardOptions = keyboardOptions?.toKeyboardOptions(),
        prefix = prefix,
        readOnly = readOnly,
        leadingIcon = leadingIcon?.let {{it.Render()}},
        trailingIcon = trailingIcon?.let {{it.Render()}}
    )
}