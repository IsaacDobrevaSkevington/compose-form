package com.idscodelabs.compose_form.json.validator.models

import androidx.compose.runtime.Composable
import com.idscodelabs.compose_form.form.fields.core.date.LocalFormDateFormatter
import com.idscodelabs.compose_form.json.validator.selectors.FormValidatorType
import com.idscodelabs.compose_form.validators.DateAfterValidator
import com.idscodelabs.compose_form.validators.core.Validator
import kotlinx.datetime.LocalDate
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(FormValidatorType.NotEmpty)
data class DateAfterValidatorModel(
    val after: LocalDate,
    val error: String? = null
): ValidatorModel {

    @Composable
    override fun <T> toValidator(): Validator<T> = DateAfterValidator(
        after,
        error ?: "Must be after ${LocalFormDateFormatter.current.format(after)}"
    ).cast()

}