package com.idscodelabs.compose_form.form.fields.strings

import org.jetbrains.compose.resources.StringResource

data class StringResourceWithPlaceholders(
    val stringResource: StringResource,
    val placeholders: List<String>,
)
