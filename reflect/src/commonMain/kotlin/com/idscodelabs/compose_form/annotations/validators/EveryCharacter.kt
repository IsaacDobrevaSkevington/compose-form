package com.idscodelabs.compose_form.annotations.validators

import com.idscodelabs.compose_form.validators.core.StringValidator

annotation class EveryCharacter(val list: CharArray, val error: String = "Invalid characters",)


