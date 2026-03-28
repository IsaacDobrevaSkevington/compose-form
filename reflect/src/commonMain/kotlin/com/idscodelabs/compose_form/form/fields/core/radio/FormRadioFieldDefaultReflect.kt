package com.idscodelabs.compose_form.form.fields.core.radio

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
import com.idscodelabs.compose_form.form.core.controller.FormController
import com.idscodelabs.compose_form.form.fields.core.base.DisplayableOption
import com.idscodelabs.compose_form.form.fields.core.base.ListDisplayable
import com.idscodelabs.compose_form.form.fields.default.radio.DefaultRadioButton
import com.idscodelabs.compose_form.styles.LocalFormStyle
import com.idscodelabs.compose_form.utils.StandardErrorDisplay
import com.idscodelabs.compose_form.utils.hint
import com.idscodelabs.compose_form.utils.updateModel
import com.idscodelabs.compose_form.utils.validator
import com.idscodelabs.compose_form.validators.core.Validator
import kotlin.reflect.KMutableProperty

@Composable
fun <Model, Item : ListDisplayable> FormController<Model>.FormRadioField(
    modelProperty: KMutableProperty<Item?>,
    options: List<Item>,
    initialValue: Item? = null,
    validator: Validator<Item>? = modelProperty.validator(),
    enabled: Boolean = true,
    hint: Any? = modelProperty.hint(),
    radioButton: @Composable (item: DisplayableOption<Item>, selected: Boolean, onClick: () -> Unit) -> Unit = { item, selected, onClick ->
        DefaultRadioButton(item, selected, onClick)
    },
    errorDisplay: @Composable (error: String) -> Unit = {
        StandardErrorDisplay(it)
    },
    modifier: Modifier = Modifier,
    textModifier: Modifier = Modifier,
) = FormRadioField(
    modelProperty = modelProperty,
    updateModel = modelProperty.updateModel(),
    options = options,
    initialValue = initialValue,
    validator = validator,
    enabled = enabled,
    hint = hint,
    radioButton = radioButton,
    modifier = modifier,
    textModifier = textModifier,
    errorDisplay = errorDisplay,
)
