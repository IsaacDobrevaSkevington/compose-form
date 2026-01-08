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
import com.idscodelabs.compose_form.styles.LocalFormStyle
import com.idscodelabs.compose_form.utils.StandardErrorDisplay
import com.idscodelabs.compose_form.validators.core.Validator
import kotlin.reflect.KProperty

@Composable
fun <Model, Item : ListDisplayable> FormController<Model>.FormRadioField(
    modelProperty: KProperty<*>,
    updateModel: Model.(Item?) -> Unit,
    options: List<Item>,
    initialValue: Item? = null,
    validator: Validator<Item>? = null,
    enabled: Boolean = true,
    hint: Any?,
    modifier: Modifier = Modifier,
    textModifier: Modifier = Modifier,
    radioButton: @Composable RadioFormBox<*, Item>.(DisplayableOption<Item>, Int) -> Unit = { item, index ->
        Row(
            Modifier
                .fillMaxWidth()
                .selectable(
                    selected = index == value,
                    onClick = { setValue(index) },
                ).minimumInteractiveComponentSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(LocalFormStyle.current.fieldRowSpacing),
        ) {
            RadioButton(
                selected = (index == value),
                onClick = {
                    if (valueSnapshot == index) {
                        setValue(-1)
                    } else {
                        setValue(index)
                    }
                },
                enabled = enabled,
            )
            Text(
                text = item.label,
            )
        }
    },
    errorDisplay: @Composable RadioFormBox<*, Item>.(error: String) -> Unit = {
        StandardErrorDisplay(it)
    },
) = FormRadioField(
    modelProperty = modelProperty,
    updateModel = updateModel,
    options = options,
    initialValue = initialValue,
    validator = validator,
    enabled = enabled,
) {
    DefaultFormRadioEntry(
        hint = hint,
        radioButton = radioButton,
        modifier = modifier,
        textModifier = textModifier,
        errorDisplay = errorDisplay,
    )
}
