package com.idscodelabs.compose_form.json.fields.serialization

import com.idscodelabs.compose_form.json.fields.models.FieldModel
import kotlinx.serialization.KSerializer
import kotlinx.serialization.modules.PolymorphicModuleBuilder
import kotlinx.serialization.serializer
import kotlin.reflect.KClass

internal data class PolymorphicCreator<T: FieldModel>(
    private val clazz: KClass<T>,
    private val serializer: KSerializer<T>,
): (PolymorphicModuleBuilder<FieldModel>) -> Unit {

    override fun invoke(builder: PolymorphicModuleBuilder<FieldModel>) {
        builder.subclass(clazz, serializer)
    }
}

internal inline fun <reified T: FieldModel> polymorphicCreator(): PolymorphicCreator<T> =
    PolymorphicCreator(clazz = T::class, serializer = serializer())