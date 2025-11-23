package com.idscodelabs.compose_form.form.fields.strings

import org.jetbrains.compose.resources.StringResource

/**
 * String resource with placeholders
 *
 * @property stringResource The [StringResource]
 * @property placeholders The placeholder to substitute into the string
 * @constructor Create empty String resource with placeholders
 *
 * @see org.jetbrains.compose.resources.stringResource
 */
data class StringResourceWithPlaceholders(
    val stringResource: StringResource,
    val placeholders: Array<Any>,
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as StringResourceWithPlaceholders

        if (stringResource != other.stringResource) return false
        if (!placeholders.contentEquals(other.placeholders)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = stringResource.hashCode()
        result = 31 * result + placeholders.contentHashCode()
        return result
    }
}
