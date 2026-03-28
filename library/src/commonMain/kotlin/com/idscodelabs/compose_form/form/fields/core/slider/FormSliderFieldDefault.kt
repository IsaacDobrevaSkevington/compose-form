package com.idscodelabs.compose_form.form.fields.core.slider

import androidx.compose.material3.Slider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.idscodelabs.compose_form.form.core.controller.FormController
import com.idscodelabs.compose_form.form.fields.default.slider.DefaultFormSliderEntry
import com.idscodelabs.compose_form.form.model.FormBox
import com.idscodelabs.compose_form.form.model.LocalFormBox
import com.idscodelabs.compose_form.utils.StandardErrorDisplay
import com.idscodelabs.compose_form.validators.core.Validator
import kotlin.math.roundToInt
import kotlin.reflect.KProperty

@Composable
fun <Model> FormController<Model>.FormSliderField(
    modelProperty: KProperty<*>,
    updateModel: Model.(Int?) -> Unit,
    initialValue: Int? = null,
    validator: Validator<Int>? = null,
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
    hint: Any? = null,
) = FormSliderField(
    modelProperty = modelProperty,
    updateModel = updateModel,
    initialValue = initialValue,
    validator = validator,
    enabled = enabled,
    implementation = {
        DefaultFormSliderEntry(
            hint = hint,
            start = start,
            end = end,
            slider = slider,
            errorDisplay = errorDisplay,
        )
    },
)
