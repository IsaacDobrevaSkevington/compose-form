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
        FormCheckBoxFieldExample()
    }),
    DATE("Date", {
        FormDateFieldExample()
    }),
    AUTOCOMPLETE_DROPDOWN("Autocomplete Dropdown", {
        FormAutocompleteDropdownFieldExample()
    }),
    DROPDOWN("Dropdown", {
        FormDropdownFieldExample()
    }),
    AUTOCOMPLETE_DROPDOWN_LARGE("Autocomplete Dropdown (Long List)", {
        FormAutocompleteDropdownFieldExampleLarge()
    }),
    DROPDOWN_LARGE("Dropdown (Long List)", {
        FormDropdownFieldExampleLarge()
    }),
    RADIO("Radio", {
        FormRadioFieldExample()
    }),
    SLIDER("Slider", {
        FormSliderFieldExample()
    }),
    TEXT_FIELD("Text Field", {
        FormTextFieldExample()
    }),
    TIME("Time", {
        FormTimeFieldExample()
    }),
    VALUE_CHANGE("Value Change", {
        FormValueChangeExample()
    }),
    VIEW_MODEL("ViewModel", {
        FormViewModelExample()
    }),
    STYLE("Styling", {
        FormStyleExample()
    }),
    CUSTOM_ENTRY("Custom Entry", {
        CustomFormFieldEntryExample()
    }),
    CUSTOM_FIELD("Custom Field", {
        CustomFormFieldExample()
    }),
    MULTISELECT("Multiselect", {
        FormMultiselectFieldExample()
    }),
}
