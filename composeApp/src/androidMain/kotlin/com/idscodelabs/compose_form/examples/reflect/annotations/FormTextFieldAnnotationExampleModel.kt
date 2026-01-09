package com.idscodelabs.compose_form.examples.reflect.annotations

import com.idscodelabs.compose_form.annotations.fields.Hint
import com.idscodelabs.compose_form.annotations.validators.NotEmpty
import com.idscodelabs.compose_form.examples.helpers.ExampleModel

data class FormTextFieldAnnotationExampleModel(

    @NotEmpty
    @Hint("Value")
    override var value: String? = null,

) : ExampleModel<String>
