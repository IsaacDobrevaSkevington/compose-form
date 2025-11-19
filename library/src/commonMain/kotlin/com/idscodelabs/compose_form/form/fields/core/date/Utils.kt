package com.idscodelabs.compose_form.form.fields.core.date

import com.idscodelabs.compose_form.utils.sanitizeDuration

fun sanitizeDate(
    input: String,
    firstLength: Int = 2,
    secondLength: Int = 2,
    thirdLength: Int = 4,
    separator: Char = '/',
): String =
    sanitizeDuration(
        input,
        firstLength,
        secondLength,
        thirdLength,
        separator,
    )
