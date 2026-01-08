package com.idscodelabs.compose_form.form.fields.core.switch

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.minimumInteractiveComponentSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.idscodelabs.compose_form.form.core.controller.FormController
import com.idscodelabs.compose_form.form.fields.default.switch.DefaultFormSwitchEntry
import com.idscodelabs.compose_form.form.model.FormBox
import com.idscodelabs.compose_form.utils.StandardErrorDisplay
import com.idscodelabs.compose_form.validators.core.Validator
import kotlin.reflect.KProperty

@Composable
fun <Model> FormController<Model>.FormSwitchField(
    modelProperty: KProperty<*>,
    updateModel: Model.(Boolean?) -> Unit,
    initialValue: Boolean? = null,
    validator: Validator<Boolean>? = null,
    enabled: Boolean = true,
    hint: Any? = null,
    leftLabel: Any? = null,
    rightLabel: Any? = null,
    errorDisplay: @Composable FormBox<*, Boolean>.(error: String) -> Unit = {
        StandardErrorDisplay(it)
    },
    modifier: Modifier = Modifier.fillMaxWidth(),
    switchModifier: Modifier = Modifier.minimumInteractiveComponentSize(),
    hintModifier: Modifier = Modifier.fillMaxWidth(),
    leftLabelModifier: Modifier = Modifier.minimumInteractiveComponentSize(),
    rightLabelModifier: Modifier = Modifier.minimumInteractiveComponentSize(),
) = FormSwitchField(
    modelProperty = modelProperty,
    updateModel = updateModel,
    initialValue = initialValue,
    validator = validator,
    enabled = enabled,
    implementation = {
        DefaultFormSwitchEntry(
            hint = hint,
            leftLabel = leftLabel,
            rightLabel = rightLabel,
            modifier = modifier,
            switchModifier = switchModifier,
            hintModifier = hintModifier,
            leftLabelModifier = leftLabelModifier,
            rightLabelModifier = rightLabelModifier,
            errorDisplay = errorDisplay,
        )
    },
)
