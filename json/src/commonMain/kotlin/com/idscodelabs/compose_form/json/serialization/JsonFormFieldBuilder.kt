package com.idscodelabs.compose_form.json.serialization

import com.idscodelabs.compose_form.json.fields.models.FieldModel
import com.idscodelabs.compose_form.json.fields.models.shared.ValidatorModel
import kotlinx.serialization.KSerializer
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic
import kotlinx.serialization.serializer
import kotlin.reflect.KClass


class JsonFormFieldBuilder internal constructor() {

    private val polymorphicFieldCreators: MutableList<PolymorphicCreator<FieldModel, *>> = mutableListOf()
    private val polymorphicValidatorCreators: MutableList<PolymorphicCreator<ValidatorModel, *>> = mutableListOf()

    inline fun <reified T: FieldModel> customField() {
        customField(T::class, serializer())
    }

    fun <T: FieldModel> customField(clazz: KClass<T>, serializer: KSerializer<T>) {
        polymorphicFieldCreators.add(PolymorphicCreator(clazz = clazz, serializer = serializer))
    }

    inline fun <reified T: ValidatorModel> customValidator() {
        customValidator(T::class, serializer())
    }

    fun <T: ValidatorModel> customValidator(clazz: KClass<T>, serializer: KSerializer<T>) {
        polymorphicValidatorCreators.add(PolymorphicCreator(clazz = clazz, serializer = serializer))
    }

    internal fun build(): FormTypes{
        val serializersModule = SerializersModule {
            polymorphic(FieldModel::class){
                (defaultFieldCreators + polymorphicFieldCreators).toSet().forEach {
                    it(this)
                }
            }
            polymorphic(ValidatorModel::class){
                (defaultValidatorCreators + polymorphicValidatorCreators).toSet().forEach {
                    it(this)
                }
            }
        }
        return FormTypes(serializersModule)
    }
}

