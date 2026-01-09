package com.idscodelabs.compose_form.form.fields.core.slider

import androidx.compose.runtime.Composable
import com.idscodelabs.compose_form.form.core.controller.FormController
import com.idscodelabs.compose_form.form.model.FormBox
import com.idscodelabs.compose_form.utils.StandardErrorDisplay
import com.idscodelabs.compose_form.utils.hint
import com.idscodelabs.compose_form.utils.updateModel
import com.idscodelabs.compose_form.utils.validator
import com.idscodelabs.compose_form.validators.core.Validator
import kotlin.reflect.KMutableProperty

@Composable
fun <Model> FormController<Model>.FormSliderField(
    modelProperty: KMutableProperty<Int?>,
    initialValue: Int? = null,
    validator: Validator<Int>? = modelProperty.validator(),
    enabled: Boolean = true,
    hint: Any? = modelProperty.hint(),
    errorDisplay: @Composable FormBox<*, Int>.(error: String) -> Unit = {
        StandardErrorDisplay(it)
    },
    start: Int = 0,
    end: Int = 100,
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
)
