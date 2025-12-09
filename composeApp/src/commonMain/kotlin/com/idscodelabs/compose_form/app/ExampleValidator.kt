package com.idscodelabs.compose_form.app

import androidx.compose.runtime.Composable
import com.idscodelabs.compose_form.examples.validators.DateAfterValidatorExample
import com.idscodelabs.compose_form.examples.validators.DateBeforeValidatorExample
import com.idscodelabs.compose_form.examples.validators.EveryCharacterValidatorExampleCustom
import com.idscodelabs.compose_form.examples.validators.MaxLengthValidatorExample
import com.idscodelabs.compose_form.examples.validators.MinLengthValidatorExample
import com.idscodelabs.compose_form.examples.validators.MultipleValidatorExample
import com.idscodelabs.compose_form.examples.validators.MustBeTickedValidatorExample
import com.idscodelabs.compose_form.examples.validators.NotEmptyIfOtherNotPopulatedValidatorExample
import com.idscodelabs.compose_form.examples.validators.NotEmptyIfOtherPopulatedValidatorExample
import com.idscodelabs.compose_form.examples.validators.NotEmptyValidatorExample
import com.idscodelabs.compose_form.examples.validators.NumberOnlyValidatorExample
import com.idscodelabs.compose_form.examples.validators.RegexValidatorExample
import com.idscodelabs.compose_form.examples.validators.TimeAfterValidatorExample
import com.idscodelabs.compose_form.examples.validators.TimeBeforeValidatorExample

enum class ExampleValidator(
    override val displayName: String,
    override val contents: @Composable () -> Unit,
) : Example {
    DATE_AFTER("Date After", {
        DateAfterValidatorExample()
    }),
    DATE_BEFORE("Date Before", {
        DateBeforeValidatorExample()
    }),
    EVERY_CHARACTER("Characterwise", {
        EveryCharacterValidatorExampleCustom()
    }),
    MAX_LENGTH("Max Length", {
        MaxLengthValidatorExample()
    }),
    MIN_LENGTH("Min Length", {
        MinLengthValidatorExample()
    }),
    MULTIPLE("Multiple", {
        MultipleValidatorExample()
    }),
    MUST_BE_TICKED("Must be Ticked", {
        MustBeTickedValidatorExample()
    }),
    NOT_EMPTY_IF_OTHER_NOT_POPULATED("Required if other empty", {
        NotEmptyIfOtherNotPopulatedValidatorExample()
    }),
    NOT_EMPTY_IF_OTHER_POPULATED("Required if other populated", {
        NotEmptyIfOtherPopulatedValidatorExample()
    }),
    NOT_EMPTY("Required", {
        NotEmptyValidatorExample()
    }),
    NUMBER_ONLY("Numbers only", {
        NumberOnlyValidatorExample()
    }),
    REGEX("Regex", {
        RegexValidatorExample()
    }),
    TIME_AFTER("Time After", {
        TimeAfterValidatorExample()
    }),
    TIME_BEFORE("Time Before", {
        TimeBeforeValidatorExample()
    }),
}
