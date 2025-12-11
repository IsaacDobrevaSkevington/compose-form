package com.idscodelabs.compose_form.form.fields.default.switch

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.minimumInteractiveComponentSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import com.idscodelabs.compose_form.form.core.FormScope
import com.idscodelabs.compose_form.form.core.IconButton
import com.idscodelabs.compose_form.form.fields.core.base.DisplayableOption
import com.idscodelabs.compose_form.form.fields.core.base.ListDisplayable
import com.idscodelabs.compose_form.form.fields.core.multiselect.FormMultiselectField
import com.idscodelabs.compose_form.form.fields.core.multiselect.MultiselectFormBox
import com.idscodelabs.compose_form.form.fields.core.radio.FormRadioField
import com.idscodelabs.compose_form.form.fields.core.radio.RadioFormBox
import com.idscodelabs.compose_form.form.fields.core.slider.FormSliderField
import com.idscodelabs.compose_form.form.fields.core.switch.FormSwitchField
import com.idscodelabs.compose_form.form.fields.default.multiselect.DefaultFormMultiselectEntry
import com.idscodelabs.compose_form.form.fields.default.multiselect.DefaultMultiselectMenuItem
import com.idscodelabs.compose_form.form.fields.default.radio.DefaultFormRadioEntry
import com.idscodelabs.compose_form.form.fields.default.slider.DefaultFormSliderEntry
import com.idscodelabs.compose_form.form.icons.Icons
import com.idscodelabs.compose_form.styles.LocalFormStyle
import com.idscodelabs.compose_form.validators.core.Validator
import kotlin.reflect.KProperty

@Composable
fun <Model> FormScope<Model>.FormSwitchField(
    modelProperty: KProperty<*>,
    updateModel: Model.(Boolean?) -> Unit,
    initialValue: Boolean? = null,
    validator: Validator<Boolean>? = null,
    enabled: Boolean = true,
    hint: Any? = null,
    leftLabel: Any? = null,
    rightLabel: Any? = null,
    modifier: Modifier = Modifier.fillMaxWidth(),
    switchModifier: Modifier = Modifier.minimumInteractiveComponentSize(),
    hintModifier: Modifier = Modifier.fillMaxWidth(),
    leftLabelModifier: Modifier = Modifier.minimumInteractiveComponentSize(),
    rightLabelModifier: Modifier = Modifier.minimumInteractiveComponentSize(),
    errorModifier: Modifier = Modifier.fillMaxWidth(),
) = FormSwitchField(
    modelProperty = modelProperty,
    updateModel = updateModel,
    initialValue = initialValue,
    validator = validator,
    enabled = enabled,
) {
    DefaultFormSwitchEntry(
        hint = hint,
        leftLabel = leftLabel,
        rightLabel = rightLabel,
        modifier = modifier,
        switchModifier = switchModifier,
        hintModifier = hintModifier,
        leftLabelModifier = leftLabelModifier,
        rightLabelModifier = rightLabelModifier,
        errorModifier = errorModifier,
    )
}
