package com.idscodelabs.compose_form.form.fields.core.base

import androidx.compose.runtime.Composable
import com.idscodelabs.compose_form.form.core.FormViewModel
import com.idscodelabs.compose_form.validators.core.Validator
import kotlin.io.encoding.Base64
import kotlin.reflect.KProperty

@Composable
fun <Model> FormViewModel<Model>.ByteArrayFieldFormFieldWrapper(
    modelProperty: KProperty<*>,
    initialValue: ByteArray?,
    enabled: Boolean,
    validator: Validator?,
    updateModel: Model.(ByteArray?) -> Unit,
    implementation: FormFieldImplementation<ByteArray>,
) = FormFieldWrapper(
    modelProperty = modelProperty,
    initialValue = initialValue,
    enabled = enabled,
    validator = validator,
    updateModel = updateModel,
    implementation = implementation,
    valueToString = { it?.let(Base64::encode) },
    stringToValue = { it?.let(Base64::decode) ?: ByteArray(0) },
    formImplementationMapper = { this },
)
