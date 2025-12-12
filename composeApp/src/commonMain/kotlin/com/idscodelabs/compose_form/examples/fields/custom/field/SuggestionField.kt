package com.idscodelabs.compose_form.examples.fields.custom.field

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.idscodelabs.compose_form.form.core.controller.FormController
import com.idscodelabs.compose_form.form.fields.core.base.DisplayableOption
import com.idscodelabs.compose_form.form.fields.core.base.IFormFieldImplementation
import com.idscodelabs.compose_form.form.fields.core.base.ListDisplayable
import com.idscodelabs.compose_form.form.fields.core.base.TextFieldFormFieldWrapper
import com.idscodelabs.compose_form.form.fields.core.dropdown.DropdownFormBox
import com.idscodelabs.compose_form.form.fields.strings.asDisplayString
import com.idscodelabs.compose_form.validators.core.Validator
import kotlin.reflect.KProperty

@Composable
fun <Model, Suggestion : ListDisplayable> FormController<Model>.SuggestionField(
    modelProperty: KProperty<*>,
    updateModel: Model.(String?) -> Unit,
    suggestions: List<Suggestion>,
    initialValue: String? = null,
    validator: Validator<String>? = null,
    enabled: Boolean = true,
    implementation: IFormFieldImplementation<DropdownFormBox<Model, Suggestion>>,
) {
    val displayableOptions =
        remember(suggestions) {
            suggestions.sortedBy { it.position }
        }.map { DisplayableOption(it, it.label.asDisplayString()) }

    TextFieldFormFieldWrapper(
        modelProperty = modelProperty,
        initialValue = initialValue,
        enabled = enabled,
        validator = validator,
        updateModel = updateModel,
        implementation = implementation,
        formImplementationMapper = { DropdownFormBox(this, displayableOptions) },
        valueToString = { it },
        stringToValue = { it },
    )
}
