package com.idscodelabs.compose_form.validators

class NumberOnlyValidator(
    error: Any = "Numbers only",
) : EveryCharacterValidator(error, Char::isDigit)
