package com.idscodelabs.compose_form.form.fields.core.dropdown

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.TextFieldValue
import com.idscodelabs.compose_form.form.core.FormScope
import com.idscodelabs.compose_form.form.fields.core.base.FormFieldWrapper
import com.idscodelabs.compose_form.form.fields.core.base.IFormFieldImplementation
import com.idscodelabs.compose_form.form.fields.core.base.ListDisplayable
import com.idscodelabs.compose_form.form.fields.strings.asDisplayString
import com.idscodelabs.compose_form.validators.InvalidOptionValidator
import com.idscodelabs.compose_form.validators.core.Validator
import kotlin.reflect.KProperty

@Composable
fun <Model, Item: ListDisplayable> FormScope<Model>.FormDropdownField(
    modelProperty: KProperty<Item?>,
    initialValue: Item?,
    enabled: Boolean,
    validator: Validator?,
    updateModel: Model.(Item?) -> Unit,
    options: List<Item>,
    invalidOptionError: Any = "Invalid Option",
    implementation: IFormFieldImplementation<DropdownFormFieldImplementationParameters<Item>>,
){
    val displayableOptions = options
        .sortedBy { it.position }
        .map { it to it.label.asDisplayString() }

    val displayableOptionsListString = remember(displayableOptions) {
        displayableOptions.map { it.second }
    }

    FormFieldWrapper(
        modelProperty = modelProperty,
        initialValue = initialValue,
        enabled = enabled,
        validator = InvalidOptionValidator(displayableOptionsListString, invalidOptionError) + validator,
        updateModel = updateModel,
        implementation = implementation,
        formImplementationMapper = { DropdownFormFieldImplementationParameters(this, options) },
        valueToStored = {item ->
            displayableOptions.firstOrNull { it.first.key == item?.key }?.second?.trim()?.let {selected ->
                TextFieldValue(selected, TextRange(selected.length))
            }
        },
        storedToString = {
            it?.text?.trim()
        },
        stringToValue = {string ->
            displayableOptions.firstOrNull { it.second == string }?.first
        },
        rememberState = {
            rememberSaveable(it, stateSaver = TextFieldValue.Saver){
                mutableStateOf(TextFieldValue(""))
            }
        }
    )
}