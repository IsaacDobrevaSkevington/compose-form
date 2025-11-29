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
import com.idscodelabs.compose_form.examples.fields.multiselect.FormMultiselectFieldExample
import com.idscodelabs.compose_form.examples.fields.radio.FormRadioFieldExample
import com.idscodelabs.compose_form.examples.fields.slider.FormSliderFieldExample
import com.idscodelabs.compose_form.examples.fields.textfield.FormTextFieldExample
import com.idscodelabs.compose_form.examples.fields.time.FormTimeFieldExample
import com.idscodelabs.compose_form.examples.logic.valuechange.FormValueChangeExample
import com.idscodelabs.compose_form.examples.logic.viewmodel.FormViewModelExample
import com.idscodelabs.compose_form.examples.ui.style.FormStyleExample

enum class ExampleField(
    override val displayName: String,
    override val contents: @Composable () -> Unit,
) : Example {
    CHECKBOX("Checkbox", {
        com.idscodelabs.compose_form.examples.fields.checkbox
            .FormCheckBoxFieldExample()
    }),
    DATE("Date", {
        com.idscodelabs.compose_form.examples.fields.date
            .FormDateFieldExample()
    }),
    AUTOCOMPLETE_DROPDOWN("Autocomplete Dropdown", {
        com.idscodelabs.compose_form.examples.fields.dropdown.normal
            .FormAutocompleteDropdownFieldExample()
    }),
    DROPDOWN("Dropdown", {
        com.idscodelabs.compose_form.examples.fields.dropdown.normal
            .FormDropdownFieldExample()
    }),
    AUTOCOMPLETE_DROPDOWN_LARGE("Autocomplete Dropdown (Long List)", {
        com.idscodelabs.compose_form.examples.fields.dropdown.large
            .FormAutocompleteDropdownFieldExampleLarge()
    }),
    DROPDOWN_LARGE("Dropdown (Long List)", {
        com.idscodelabs.compose_form.examples.fields.dropdown.large
            .FormDropdownFieldExampleLarge()
    }),
    RADIO("Radio", {
        com.idscodelabs.compose_form.examples.fields.radio
            .FormRadioFieldExample()
    }),
    SLIDER("Slider", {
        com.idscodelabs.compose_form.examples.fields.slider
            .FormSliderFieldExample()
    }),
    TEXT_FIELD("Text Field", {
        com.idscodelabs.compose_form.examples.fields.textfield
            .FormTextFieldExample()
    }),
    TIME("Time", {
        com.idscodelabs.compose_form.examples.fields.time
            .FormTimeFieldExample()
    }),
    VALUE_CHANGE("Value Change", {
        com.idscodelabs.compose_form.examples.logic.valuechange
            .FormValueChangeExample()
    }),
    VIEW_MODEL("ViewModel", {
        com.idscodelabs.compose_form.examples.logic.viewmodel
            .FormViewModelExample()
    }),
    STYLE("Styling", {
        com.idscodelabs.compose_form.examples.ui.style
            .FormStyleExample()
    }),
    CUSTOM_ENTRY("Custom Entry", {
        com.idscodelabs.compose_form.examples.fields.custom.entry
            .CustomFormFieldEntryExample()
    }),
    CUSTOM_FIELD("Custom Field", {
        com.idscodelabs.compose_form.examples.fields.custom.field
            .CustomFormFieldExample()
    }),
    MULTISELECT("Multiselect", {
        com.idscodelabs.compose_form.examples.fields.multiselect
            .FormMultiselectFieldExample()
    }),
}
