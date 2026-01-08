package com.idscodelabs.compose_form.form.fields.core.slider

import androidx.compose.runtime.Composable
import com.idscodelabs.compose_form.form.core.controller.FormController
import com.idscodelabs.compose_form.form.fields.default.slider.DefaultFormSliderEntry
import com.idscodelabs.compose_form.form.model.FormBox
import com.idscodelabs.compose_form.utils.StandardErrorDisplay
import com.idscodelabs.compose_form.validators.core.Validator
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
    hint: Any? = null,
    errorDisplay: @Composable FormBox<*, Int>.(error: String) -> Unit = {
        StandardErrorDisplay(it)
    },
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
            errorDisplay = errorDisplay,
        )
    },
)
