package com.idscodelabs.compose_form.form.fields.core.date

import com.idscodelabs.compose_form.utils.sanitizeDuration

/**
 * Sanitize a date input. The default parameters match a format of dd/MM/yyyy
 *
 * @param input The current input
 * @param firstLength The length of the first part of the date
 * @param secondLength The length of the second part of the date
 * @param thirdLength The length of the third part of the date
 * @param separator The separator between date parts
 * @return A sanitised date
 */
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
