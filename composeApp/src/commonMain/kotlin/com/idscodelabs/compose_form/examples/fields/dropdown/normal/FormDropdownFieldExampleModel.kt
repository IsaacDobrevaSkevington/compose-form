package com.idscodelabs.compose_form.examples.fields.dropdown.normal

import com.idscodelabs.compose_form.examples.helpers.ExampleModel

data class FormDropdownFieldExampleModel(
    override var value: FormDropdownFieldExampleOption? = null,
) : ExampleModel<FormDropdownFieldExampleOption>
