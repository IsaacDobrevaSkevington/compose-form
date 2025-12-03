package com.idscodelabs.compose_form.utils

import kotlin.reflect.KParameter
import kotlin.reflect.KProperty
import kotlin.reflect.KType
import kotlin.reflect.KTypeParameter
import kotlin.reflect.KVisibility

actual class VirtualKProperty actual constructor(
    actual override val name: String,
) : KProperty<String> {
    override val annotations: List<Annotation> = VirtualKProperty::name.annotations
    override val getter: KProperty.Getter<String> = VirtualKProperty::name.getter
    override val isAbstract: Boolean = VirtualKProperty::name.isAbstract
    override val isConst: Boolean = VirtualKProperty::name.isConst
    override val isFinal: Boolean = VirtualKProperty::name.isFinal
    override val isLateinit: Boolean = VirtualKProperty::name.isLateinit
    override val isOpen: Boolean = VirtualKProperty::name.isOpen
    override val isSuspend: Boolean = VirtualKProperty::name.isSuspend
    override val parameters: List<KParameter> = VirtualKProperty::name.parameters
    override val returnType: KType = VirtualKProperty::name.returnType
    override val typeParameters: List<KTypeParameter> = VirtualKProperty::name.typeParameters
    override val visibility: KVisibility? = VirtualKProperty::class.visibility

    override fun call(vararg args: Any?): String = VirtualKProperty::name.call(args)

    override fun callBy(args: Map<KParameter, Any?>): String = VirtualKProperty::name.callBy(args)
}
