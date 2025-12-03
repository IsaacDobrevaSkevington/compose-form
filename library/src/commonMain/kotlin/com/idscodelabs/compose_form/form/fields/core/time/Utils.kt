package com.idscodelabs.compose_form.form.fields.core.time

import com.idscodelabs.compose_form.utils.sanitizeDuration

fun sanitizeTime(
    input: String,
    firstLength: Int = 2,
    secondLength: Int = 2,
    thirdLength: Int = 0,
    separator: Char = ':',
): String =
    sanitizeDuration(
        input,
        firstLength,
        secondLength,
        thirdLength,
        separator,
    )
