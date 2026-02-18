package com.idscodelabs.compose_form.json.fields.serialization

import com.idscodelabs.compose_form.json.fields.models.FormTextFieldModel

internal val defaultFieldCreators = arrayOf<PolymorphicCreator<*>>(
    polymorphicCreator<FormTextFieldModel>()
)
