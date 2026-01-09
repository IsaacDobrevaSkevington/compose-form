package com.idscodelabs.compose_form.form.fields.core.suggestion

import androidx.compose.runtime.Composable
import com.idscodelabs.compose_form.form.core.controller.FormController
import com.idscodelabs.compose_form.form.fields.core.base.IFormFieldImplementation
import com.idscodelabs.compose_form.form.fields.core.base.ListDisplayable
import com.idscodelabs.compose_form.utils.updateModel
import com.idscodelabs.compose_form.utils.validator
import com.idscodelabs.compose_form.validators.core.Validator
import kotlin.reflect.KMutableProperty

@Composable
fun <Model, Suggestion : ListDisplayable> FormController<Model>.FormSuggestionField(
    modelProperty: KMutableProperty<String?>,
    getSuggestions: suspend (String) -> List<Suggestion>,
    debounce: Long = 300L,
    initialValue: String? = null,
    validator: Validator<String>? = modelProperty.validator(),
    enabled: Boolean = true,
    implementation: IFormFieldImplementation<SuggestionFormBox<Model, Suggestion>>,
) = FormSuggestionField(
    modelProperty = modelProperty,
    updateModel = modelProperty.updateModel(),
    getSuggestions = getSuggestions,
    debounce = debounce,
    initialValue = initialValue,
    validator = validator,
    enabled = enabled,
    implementation = implementation
)
