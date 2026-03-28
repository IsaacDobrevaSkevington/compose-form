package com.idscodelabs.compose_form.form.fields.core.switch

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Switch
import androidx.compose.material3.minimumInteractiveComponentSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.idscodelabs.compose_form.form.core.controller.FormController
import com.idscodelabs.compose_form.form.model.LocalFormBox
import com.idscodelabs.compose_form.utils.StandardErrorDisplay
import com.idscodelabs.compose_form.utils.hint
import com.idscodelabs.compose_form.utils.updateModel
import com.idscodelabs.compose_form.utils.validator
import com.idscodelabs.compose_form.validators.core.Validator
import kotlin.reflect.KMutableProperty

@Composable
fun <Model> FormController<Model>.FormSwitchField(
    modelProperty: KMutableProperty<Boolean?>,
    initialValue: Boolean? = null,
    validator: Validator<Boolean>? = modelProperty.validator(),
    enabled: Boolean = true,
    hint: Any? = modelProperty.hint(),
    leftLabel: Any? = null,
    rightLabel: Any? = null,
    switch: @Composable (value: Boolean, setValue: (Boolean) -> Unit) -> Unit = { value, setValue ->
        Switch(
            checked = value,
            onCheckedChange = setValue,
            enabled = LocalFormBox.current.enabled,
        )
    },
    errorDisplay: @Composable (error: String) -> Unit = {
        StandardErrorDisplay(it)
    },
    modifier: Modifier = Modifier.fillMaxWidth(),
    hintModifier: Modifier = Modifier.fillMaxWidth(),
    leftLabelModifier: Modifier = Modifier.minimumInteractiveComponentSize(),
    rightLabelModifier: Modifier = Modifier.minimumInteractiveComponentSize(),
) = FormSwitchField(
    modelProperty = modelProperty,
    updateModel = modelProperty.updateModel(),
    initialValue = initialValue,
    validator = validator,
    enabled = enabled,
    hint = hint,
    leftLabel = leftLabel,
    rightLabel = rightLabel,
    modifier = modifier,
    switch = switch,
    hintModifier = hintModifier,
    leftLabelModifier = leftLabelModifier,
    rightLabelModifier = rightLabelModifier,
    errorDisplay = errorDisplay,
)
