package com.idscodelabs.compose_form.utils

fun sanitizeDuration(
    input: String,
    firstLength: Int,
    secondLength: Int,
    thirdLength: Int,
    separator: Char
): String {
    val digitsOnly = input.filter { it.isDigit() }
    return when {
        digitsOnly.length <= firstLength -> digitsOnly
        digitsOnly.length <= firstLength + secondLength -> "${digitsOnly.take(firstLength)}$separator${digitsOnly.substring(firstLength)}"
        else -> "${digitsOnly.take(firstLength)}$separator${digitsOnly.substring(firstLength, firstLength + secondLength)}$separator${
            digitsOnly.substring(
                firstLength + secondLength,
                minOf(digitsOnly.length, firstLength + secondLength + thirdLength)
            )
        }".trim(separator)
    }
}