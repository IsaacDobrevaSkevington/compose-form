package com.idscodelabs.compose_form.annotations.validators

import androidx.compose.runtime.Composable
import com.idscodelabs.compose_form.validators.NumberOnlyValidator
import com.idscodelabs.compose_form.validators.core.Validator
import kotlin.reflect.full.isSubtypeOf
import kotlin.reflect.full.memberFunctions

object AnnotationMapper {
    @Composable
    fun <Value : Any> map(annotation: Annotation): Validator<Value>? =
        when (annotation) {
            is DateAfter -> annotation.validator()
            is MaxLength -> annotation.validator()
            is MinLength -> annotation.validator()
            is EveryCharacter -> annotation.validator()
            is MustBeTicked -> annotation.validator()
            is NotEmpty -> annotation.validator()
            is NumberOnly -> annotation.validator()
            is Regex -> annotation.validator()
            is TimeAfter -> annotation.validator()
            is TimeBefore -> annotation.validator()
            is com.idscodelabs.compose_form.annotations.validators.Validator<*> -> annotation.validator()
            is ComposeValidator<*> -> annotation.validator()
            else -> null
        }?.cast()
}

@Suppress("UNCHECKED_CAST")
fun <NewValue : Any> Validator<*>.cast(): Validator<NewValue>? {
    val clazzValidatorFunctionType =
        Validator<NewValue> {
            _,
            _,
            ->
            null
        }::class.memberFunctions.first { it.name == "validate" }.parameters.first().type
    val validatorFunctionType = this::validate.parameters.first().type
    return if (clazzValidatorFunctionType.isSubtypeOf(validatorFunctionType)) {
        this as Validator<NewValue>
    } else {
        null
    }
}
