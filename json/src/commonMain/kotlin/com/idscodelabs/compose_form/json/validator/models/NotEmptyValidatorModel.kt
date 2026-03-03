package com.idscodelabs.compose_form.json.validator.models

import androidx.compose.runtime.Composable
import com.idscodelabs.compose_form.json.validator.selectors.FormValidatorType
import com.idscodelabs.compose_form.validators.NotEmptyValidator
import com.idscodelabs.compose_form.validators.core.Validator
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(FormValidatorType.NotEmpty)
data class NotEmptyValidatorModel(
    val error: String = "Required",
): ValidatorModel {

    @Composable
    override fun <T> toValidator(): Validator<T> = NotEmptyValidator(error)

}