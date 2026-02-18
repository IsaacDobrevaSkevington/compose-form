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
import com.idscodelabs.compose_form.form.fields.default.radio.DefaultFormRadioEntry
import com.idscodelabs.compose_form.form.fields.default.radio.DefaultRadioButton
import com.idscodelabs.compose_form.styles.LocalFormStyle
import com.idscodelabs.compose_form.utils.StandardErrorDisplay
import com.idscodelabs.compose_form.validators.core.Validator
import kotlin.reflect.KProperty

/**
 * A radio form field
 *
 * @param Model The Model of the form
 * @param modelProperty The [KProperty] of the [Model] which this field updates.
 * @param initialValue The initial value for this field
 * @param enabled Whether the field is enabled
 * @param validator The [Validator] for this field
 * @param updateModel Function called which should set the correct property on the model. Most often should be `{ <variable> = it }`
 * @param hint Hint for the radio group
 * @param modifier Modifier for the whole radio group
 * @param textModifier Modifier for the hint text
 * @param radioButton Composable display of the radio button
 * @param errorDisplay Composable display of the error
 * @see [ListDisplayable]
 * @sample com.idscodelabs.compose_form.examples.fields.radio.FormRadioFieldExample
 */
@Composable
fun <Model, Item : ListDisplayable> FormController<Model>.FormRadioField(
    modelProperty: KProperty<*>,
    updateModel: Model.(Item?) -> Unit,
    options: List<Item>,
    initialValue: Item? = null,
    validator: Validator<Item>? = null,
    enabled: Boolean = true,
    hint: Any? = null,
    radioButton: @Composable RadioFormBox<*, Item>.(DisplayableOption<Item>, Int) -> Unit = { item, index ->
        DefaultRadioButton(item, index)
    },
    errorDisplay: @Composable RadioFormBox<*, Item>.(error: String) -> Unit = {
        StandardErrorDisplay(it)
    },
    modifier: Modifier = Modifier,
    textModifier: Modifier = Modifier,
) = FormRadioField(
    modelProperty = modelProperty,
    updateModel = updateModel,
    options = options,
    initialValue = initialValue,
    validator = validator,
    enabled = enabled,
    implementation = {
        DefaultFormRadioEntry(
            hint = hint,
            radioButton = radioButton,
            modifier = modifier,
            textModifier = textModifier,
            errorDisplay = errorDisplay,
        )
    },
)
