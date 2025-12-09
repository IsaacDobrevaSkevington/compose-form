package com.idscodelabs.compose_form.validators

import com.idscodelabs.compose_form.form.fields.core.base.ListDisplayable
import com.idscodelabs.compose_form.validators.core.Validator

class InvalidOptionValidator<Item : ListDisplayable>(
    val possibleOptions: List<String>,
    val error: Any = "Invalid Option Selected",
) : Validator<Item> {
    override fun validate(
        value: Item?,
        stringRepresentation: String?,
    ): Any? =
        if (stringRepresentation.isNullOrBlank() || possibleOptions.any { it.equals(stringRepresentation, true) }) {
            null
        } else {
            error
        }
}
