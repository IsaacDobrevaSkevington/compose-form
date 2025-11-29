package com.idscodelabs.compose_form.examples.fields.textfield

import com.idscodelabs.compose_form.examples.helpers.ExampleModel

data class FormTextFieldExampleModelDouble(
    var value1: String? = null,
    var value2: String? = null,
) : com.idscodelabs.compose_form.examples.helpers.ExampleModel<Pair<String, String>> {
    override var value: Pair<String, String>? = null
        get() = if (value1 != null && value2 != null) value1!! to value2!! else null
}
