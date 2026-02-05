package com.idscodelabs.compose_form.annotations.validators

import androidx.compose.runtime.Composable
import com.idscodelabs.compose_form.utils.zeroArgConstructor
import kotlin.reflect.KClass
import com.idscodelabs.compose_form.validators.core.Validator as CoreValidator

@Target(AnnotationTarget.PROPERTY)
@MustBeDocumented
@Repeatable
annotation class Validator<T>(
    val validatorClazz: KClass<out CoreValidator<T>>,
)

@Composable
fun <Value> Validator<Value>.validator(): CoreValidator<Value>? = validatorClazz.zeroArgConstructor()?.invoke()
