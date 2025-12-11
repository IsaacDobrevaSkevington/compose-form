package com.idscodelabs.compose_form.examples.fields.suggestion

import com.idscodelabs.compose_form.examples.helpers.ExampleModel

data class FormSuggestionFieldExampleModel(
    override var value: String? = null,
) : ExampleModel<String>
