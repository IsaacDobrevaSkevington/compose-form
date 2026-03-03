package com.idscodelabs.compose_form.json.serialization

import com.idscodelabs.compose_form.json.fields.models.FieldModel
import com.idscodelabs.compose_form.json.fields.models.FormTextFieldModel
import com.idscodelabs.compose_form.json.validator.models.NotEmptyValidatorModel
import com.idscodelabs.compose_form.json.validator.models.ValidatorModel

internal val defaultFieldCreators = arrayOf<PolymorphicCreator<FieldModel, *>>(
    polymorphicFieldCreator<FormTextFieldModel>()
)

internal val defaultValidatorCreators = arrayOf<PolymorphicCreator<ValidatorModel, *>>(
    polymorphicValidatorCreator<NotEmptyValidatorModel>()
)
