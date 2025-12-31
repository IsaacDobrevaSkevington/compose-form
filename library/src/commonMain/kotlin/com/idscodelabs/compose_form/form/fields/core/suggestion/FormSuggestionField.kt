package com.idscodelabs.compose_form.form.fields.core.suggestion

import androidx.compose.runtime.Composable
import com.idscodelabs.compose_form.form.core.controller.FormController
import com.idscodelabs.compose_form.form.fields.core.base.IFormFieldImplementation
import com.idscodelabs.compose_form.form.fields.core.base.ListDisplayable
import com.idscodelabs.compose_form.form.fields.core.base.TextFieldFormFieldWrapper
import com.idscodelabs.compose_form.validators.core.Validator
import kotlin.reflect.KProperty

@Composable
fun <Model, Suggestion : ListDisplayable> FormController<Model>.FormSuggestionField(
    modelProperty: KProperty<*>,
    updateModel: Model.(String?) -> Unit,
    getSuggestions: suspend (String) -> List<Suggestion>,
    debounce: Long = 300L,
    initialValue: String? = null,
    validator: Validator<String>? = null,
    enabled: Boolean = true,
    implementation: IFormFieldImplementation<SuggestionFormBox<Model, Suggestion>>,
) {
    TextFieldFormFieldWrapper(
        modelProperty = modelProperty,
        initialValue = initialValue,
        enabled = enabled,
        validator = validator,
        updateModel = updateModel,
        implementation = implementation,
        formImplementationMapper = { rememberAsSuggestionFormBox(debounce, getSuggestions) },
        valueToString = { it },
        stringToValue = { it },
    )
}
