package com.idscodelabs.compose_form.annotations.validators

import com.idscodelabs.compose_form.validators.EveryCharacterValidator

class NumberOnlyValidator(
    error: Any = "Numbers only",
) : EveryCharacterValidator(error, Char::isDigit)
