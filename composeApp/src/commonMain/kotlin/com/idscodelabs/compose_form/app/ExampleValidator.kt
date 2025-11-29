package com.idscodelabs.compose_form.app

import androidx.compose.runtime.Composable
import com.idscodelabs.compose_form.examples.fields.checkbox.FormCheckBoxFieldExample
import com.idscodelabs.compose_form.examples.fields.custom.entry.CustomFormFieldEntryExample
import com.idscodelabs.compose_form.examples.fields.custom.field.CustomFormFieldExample
import com.idscodelabs.compose_form.examples.fields.date.FormDateFieldExample
import com.idscodelabs.compose_form.examples.fields.dropdown.large.FormAutocompleteDropdownFieldExampleLarge
import com.idscodelabs.compose_form.examples.fields.dropdown.large.FormDropdownFieldExampleLarge
import com.idscodelabs.compose_form.examples.fields.dropdown.normal.FormAutocompleteDropdownFieldExample
import com.idscodelabs.compose_form.examples.fields.dropdown.normal.FormDropdownFieldExample
import com.idscodelabs.compose_form.examples.fields.radio.FormRadioFieldExample
import com.idscodelabs.compose_form.examples.fields.slider.FormSliderFieldExample
import com.idscodelabs.compose_form.examples.fields.textfield.FormTextFieldExample
import com.idscodelabs.compose_form.examples.fields.time.FormTimeFieldExample
import com.idscodelabs.compose_form.examples.logic.valuechange.FormValueChangeExample
import com.idscodelabs.compose_form.examples.logic.viewmodel.FormViewModelExample
import com.idscodelabs.compose_form.examples.ui.style.FormStyleExample
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
import com.idscodelabs.compose_form.validators.DateAfterValidator

enum class ExampleValidator(
    override val displayName: String,
    override val contents: @Composable () -> Unit,
) : Example {
    DATE_AFTER("Date After", {
        com.idscodelabs.compose_form.examples.validators
            .DateAfterValidatorExample()
    }),
    DATE_BEFORE("Date Before", {
        com.idscodelabs.compose_form.examples.validators
            .DateBeforeValidatorExample()
    }),
    EVERY_CHARACTER("Characterwise", {
        com.idscodelabs.compose_form.examples.validators
            .EveryCharacterValidatorExampleCustom()
    }),
    MAX_LENGTH("Max Length", {
        com.idscodelabs.compose_form.examples.validators
            .MaxLengthValidatorExample()
    }),
    MIN_LENGTH("Min Length", {
        com.idscodelabs.compose_form.examples.validators
            .MinLengthValidatorExample()
    }),
    MULTIPLE("Multiple", {
        com.idscodelabs.compose_form.examples.validators
            .MultipleValidatorExample()
    }),
    MUST_BE_TICKED("Must be Ticked", {
        com.idscodelabs.compose_form.examples.validators
            .MustBeTickedValidatorExample()
    }),
    NOT_EMPTY_IF_OTHER_NOT_POPULATED("Required if other empty", {
        com.idscodelabs.compose_form.examples.validators
            .NotEmptyIfOtherNotPopulatedValidatorExample()
    }),
    NOT_EMPTY_IF_OTHER_POPULATED("Required if other populated", {
        com.idscodelabs.compose_form.examples.validators
            .NotEmptyIfOtherPopulatedValidatorExample()
    }),
    NOT_EMPTY("Required", {
        com.idscodelabs.compose_form.examples.validators
            .NotEmptyValidatorExample()
    }),
    NUMBER_ONLY("Numbers only", {
        com.idscodelabs.compose_form.examples.validators
            .NumberOnlyValidatorExample()
    }),
    REGEX("Regex", {
        com.idscodelabs.compose_form.examples.validators
            .RegexValidatorExample()
    }),
    TIME_AFTER("Time After", {
        com.idscodelabs.compose_form.examples.validators
            .TimeAfterValidatorExample()
    }),
    TIME_BEFORE("Time Before", {
        com.idscodelabs.compose_form.examples.validators
            .TimeBeforeValidatorExample()
    }),
}
