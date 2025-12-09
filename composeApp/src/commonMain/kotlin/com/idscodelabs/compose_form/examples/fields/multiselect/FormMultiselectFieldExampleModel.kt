package com.idscodelabs.compose_form.examples.fields.multiselect

import com.idscodelabs.compose_form.examples.fields.dropdown.large.FormDropdownFieldExampleOptionLarge
import com.idscodelabs.compose_form.examples.helpers.ExampleModel

data class FormMultiselectFieldExampleModel(
    override var value: List<FormDropdownFieldExampleOptionLarge>? = null,
) : ExampleModel<List<FormDropdownFieldExampleOptionLarge>>
