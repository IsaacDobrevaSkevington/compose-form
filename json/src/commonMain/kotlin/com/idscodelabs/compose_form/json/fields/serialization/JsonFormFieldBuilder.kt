package com.idscodelabs.compose_form.json.fields.serialization

import com.idscodelabs.compose_form.json.fields.models.FieldModel
import kotlinx.serialization.KSerializer
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic
import kotlinx.serialization.serializer
import kotlin.reflect.KClass


class JsonFormFieldBuilder internal constructor() {

    private val polymorphicCreators: MutableList<PolymorphicCreator<*>> = mutableListOf()

    inline fun <reified T: FieldModel> customField() {
        customField(T::class, serializer())
    }

    fun <T: FieldModel> customField(clazz: KClass<T>, serializer: KSerializer<T>) {
        polymorphicCreators.add(PolymorphicCreator(clazz = clazz, serializer = serializer))
    }

    internal fun build(): FormTypes{
        val serializersModule = SerializersModule {
            polymorphic(FieldModel::class){
                (defaultFieldCreators + polymorphicCreators).toSet().forEach {
                    it(this)
                }
            }
        }
        return FormTypes(serializersModule)
    }
}

