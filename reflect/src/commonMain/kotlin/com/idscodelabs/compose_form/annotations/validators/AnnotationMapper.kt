package com.idscodelabs.compose_form.annotations.validators

import androidx.compose.runtime.Composable
import com.idscodelabs.compose_form.form.fields.core.date.LocalFormDateFormatter
import com.idscodelabs.compose_form.validators.DateBeforeValidator
import com.idscodelabs.compose_form.validators.NotEmptyValidator
import com.idscodelabs.compose_form.validators.core.Validator
import kotlinx.datetime.LocalDate
import kotlin.reflect.KClass
import kotlin.reflect.full.memberFunctions

object AnnotationMapper {

    @Composable
    fun <Value: Any> map(annotation: Annotation, clazz: KClass<Value>): Validator<Value>?{
        return when(annotation){
            is NotEmpty -> annotation.validator()
            is DateAfter -> annotation.validator().cast(clazz)
            else -> null
        }
    }
}

@Suppress("UNCHECKED_CAST")
fun <NewValue: Any> Validator<*>.cast(clazz: KClass<NewValue>): Validator<NewValue>?{
    val clazzValidatorFunction = clazz.memberFunctions.first { it.name == "validate" }
    val validatorFunction = this::validate
    return if(clazzValidatorFunction.parameters.first().type == validatorFunction.parameters.first().type){
        this as Validator<NewValue>
    } else {
        null
    }
}