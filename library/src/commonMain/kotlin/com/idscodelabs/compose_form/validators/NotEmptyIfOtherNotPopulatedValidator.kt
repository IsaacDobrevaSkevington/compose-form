package com.idscodelabs.compose_form.validators

import com.idscodelabs.compose_form.validators.core.Validator
import org.jetbrains.compose.resources.StringResource
import kotlin.reflect.KProperty

class NotEmptyIfOtherNotPopulatedValidator(
    val other: KProperty<*>,
    private val error: Any = "Required if ${other.name} is empty.",
) : Validator {
    override fun validate(
        s: String?,
        otherFieldValues: Map<String, String?>,
    ): Any? {
        val otherFieldValue = otherFieldValues[other.name]
        if (!otherFieldValue.isNullOrBlank()) return null
        return if (s.isNullOrBlank()) {
            error
        } else {
            null
        }
    }
}
