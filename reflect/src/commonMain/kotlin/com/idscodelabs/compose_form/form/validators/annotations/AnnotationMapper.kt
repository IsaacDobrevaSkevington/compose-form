package com.idscodelabs.compose_form.form.validators.annotations

import com.idscodelabs.compose_form.validators.NotEmptyValidator
import com.idscodelabs.compose_form.validators.core.Validator

object AnnotationMapper {

    fun <Value> map(annotation: Annotation): Validator<Value>?{
        return when(annotation){
            is NotEmpty -> NotEmptyValidator(annotation.error)
            else -> null
        }
    }
}