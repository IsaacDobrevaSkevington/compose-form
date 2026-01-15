package com.idscodelabs.compose_form.annotations.validators

import androidx.compose.runtime.Composable
import com.idscodelabs.compose_form.utils.zeroArgConstructor
import kotlin.reflect.KClass
import com.idscodelabs.compose_form.validators.core.Validator as CoreValidator

fun interface ComposeValidatorFactory<T> {
    @Composable
    fun validator(): CoreValidator<T>
}

@Target(AnnotationTarget.PROPERTY)
@MustBeDocumented
annotation class ComposeValidator<T>(
    val validatorFactoryClazz: KClass<out ComposeValidatorFactory<T>>,
)

@Composable
fun <Value> ComposeValidator<Value>.validator(): CoreValidator<Value>? = validatorFactoryClazz.zeroArgConstructor()?.invoke()?.validator()
