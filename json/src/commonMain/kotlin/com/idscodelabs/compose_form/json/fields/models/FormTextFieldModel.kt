package com.idscodelabs.compose_form.json.fields.models

import com.idscodelabs.compose_form.json.fields.models.shared.IconModel
import com.idscodelabs.compose_form.json.fields.models.shared.KeyboardModel
import com.idscodelabs.compose_form.json.fields.models.shared.ValidatorModel
import com.idscodelabs.compose_form.json.fields.selectors.FormFieldType
import kotlinx.serialization.Serializable

@Serializable
data class FormTextFieldModel(
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
    override val type: String = FormFieldType.TextField
}