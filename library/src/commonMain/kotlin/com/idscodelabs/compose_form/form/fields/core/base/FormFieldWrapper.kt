package com.idscodelabs.compose_form.form.fields.core.base

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.idscodelabs.compose_form.form.core.FormScope
import com.idscodelabs.compose_form.form.model.FormBox
import com.idscodelabs.compose_form.form.model.rememberFormBox
import com.idscodelabs.compose_form.validators.core.Validator
import kotlin.reflect.KProperty

@Composable
fun <Model, Value : Any, Parameters : FormBox<*, Value>> FormScope<Model>.FormFieldWrapper(
    modelProperty: KProperty<*>,
    initialValue: Value?,
    enabled: Boolean,
    validator: Validator?,
    updateModel: Model.(Value?) -> Unit,
    implementation: IFormFieldImplementation<Parameters>,
    valueToString: (Value?) -> String?,
    stringToValue: (String?) -> Value,
    formImplementationMapper: FormBox<Model, Value>.() -> Parameters,
    mapValue: (value: Value) -> Value = { it },
) {
    val formBox =
        rememberFormBox<Model, Value>(
            enabled = enabled,
            validator = validator,
            setModelProperty = {
                updateModel(stringToValue(it?.ifBlank { null }))
            },
            valueToString = valueToString,
            stringToValue = stringToValue,
            key = modelProperty.name,
            mapValue = mapValue,
        )

    LaunchedEffect(initialValue) {
        if (initialValue != null) {
            formBox.setValue(initialValue)
        }
    }

    val params = formBox.formImplementationMapper()

    implementation(params)

    formBox.BindLifecycle(modelProperty)
}
