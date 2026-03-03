package com.idscodelabs.compose_form.json.serialization

import com.idscodelabs.compose_form.json.fields.models.FieldModel
import com.idscodelabs.compose_form.json.validator.models.ValidatorModel
import kotlinx.serialization.KSerializer
import kotlinx.serialization.modules.PolymorphicModuleBuilder
import kotlinx.serialization.serializer
import kotlin.reflect.KClass

internal data class PolymorphicCreator<T: Any, U: T>(
    private val clazz: KClass<U>,
    private val serializer: KSerializer<U>,
): (PolymorphicModuleBuilder<T>) -> Unit {

    override fun invoke(builder: PolymorphicModuleBuilder<T>) {
        builder.subclass(clazz, serializer)
    }
}


internal inline fun <T: Any, reified U: T> polymorphicCreator(): PolymorphicCreator<T, U> =
    PolymorphicCreator(clazz = U::class, serializer = serializer())

internal inline fun <reified U: FieldModel> polymorphicFieldCreator() = polymorphicCreator<FieldModel, U>()
internal inline fun <reified U: ValidatorModel> polymorphicValidatorCreator() = polymorphicCreator<ValidatorModel, U>()