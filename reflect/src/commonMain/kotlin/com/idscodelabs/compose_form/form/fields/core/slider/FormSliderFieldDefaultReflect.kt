package com.idscodelabs.compose_form.form.fields.core.slider

import androidx.compose.material3.Slider
import androidx.compose.runtime.Composable
import com.idscodelabs.compose_form.form.core.controller.FormController
import com.idscodelabs.compose_form.form.model.LocalFormBox
import com.idscodelabs.compose_form.utils.StandardErrorDisplay
import com.idscodelabs.compose_form.utils.hint
import com.idscodelabs.compose_form.utils.updateModel
import com.idscodelabs.compose_form.utils.validator
import com.idscodelabs.compose_form.validators.core.Validator
import kotlin.math.roundToInt
import kotlin.reflect.KMutableProperty

@Composable
fun <Model> FormController<Model>.FormSliderField(
    modelProperty: KMutableProperty<Int?>,
    initialValue: Int? = null,
    validator: Validator<Int>? = modelProperty.validator(),
    enabled: Boolean = true,
    start: Int = 0,
    end: Int = 100,
    slider: @Composable (value: Int, setValue: (Int) -> Unit) -> Unit = { value, setValue ->
        Slider(
            value = value.coerceIn(start, end).toFloat(),
            onValueChange = {
                setValue(it.roundToInt())
            },
            valueRange = start.toFloat()..end.toFloat(),
            enabled = LocalFormBox.current.enabled,
        )
    },
    errorDisplay: @Composable (error: String) -> Unit = {
        StandardErrorDisplay(it)
    },
    hint: Any? = modelProperty.hint(),
) = FormSliderField(
    modelProperty = modelProperty,
    updateModel = modelProperty.updateModel(),
    initialValue = initialValue,
    validator = validator,
    enabled = enabled,
    hint = hint,
    start = start,
    end = end,
    errorDisplay = errorDisplay,
    slider = slider,
)
