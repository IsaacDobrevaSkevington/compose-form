package com.idscodelabs.compose_form.form.fields.core.checkbox

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.minimumInteractiveComponentSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.idscodelabs.compose_form.form.core.controller.FormController
import com.idscodelabs.compose_form.form.model.FormBox
import com.idscodelabs.compose_form.utils.StandardErrorDisplay
import com.idscodelabs.compose_form.utils.updateModel
import com.idscodelabs.compose_form.validators.core.Validator
import kotlin.reflect.KMutableProperty

@Composable
fun <Model> FormController<Model>.FormCheckBoxField(
    modelProperty: KMutableProperty<Boolean?>,
    hint: Any,
    initialValue: Boolean? = null,
    validator: Validator<Boolean>? = null,
    enabled: Boolean = true,
    errorDisplay: @Composable FormBox<*, Boolean>.(error: String) -> Unit = {
        StandardErrorDisplay(it)
    },
    modifier: Modifier = Modifier.fillMaxWidth(),
    checkboxModifier: Modifier = Modifier.minimumInteractiveComponentSize(),
    textModifier: Modifier = Modifier.minimumInteractiveComponentSize(),
    ) = FormCheckBoxField(
    modelProperty = modelProperty,
    updateModel = modelProperty.updateModel(),
    initialValue = initialValue,
    validator = validator,
    enabled = enabled,
    hint = hint,
    modifier = modifier,
    checkboxModifier = checkboxModifier,
    textModifier = textModifier,
    errorDisplay = errorDisplay,
)
