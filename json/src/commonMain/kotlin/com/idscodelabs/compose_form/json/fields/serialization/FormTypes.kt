package com.idscodelabs.compose_form.json.fields.serialization

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule


val LocalFormTypes = compositionLocalOf { FormTypes {  } }


class FormTypes internal constructor(
    val serializersModule: SerializersModule,
) {
    val json by lazy {
        Json { this@Json.serializersModule = this@FormTypes.serializersModule }
    }
}

@Composable
fun ProvideFormTypes(
    formTypes: FormTypes,
    block: @Composable ()->Unit
){
    CompositionLocalProvider(LocalFormTypes provides formTypes) {
        block()
    }
}

fun FormTypes(block: JsonFormFieldBuilder.() -> Unit): FormTypes {
    val builder = JsonFormFieldBuilder()
    builder.block()
    return builder.build()
}