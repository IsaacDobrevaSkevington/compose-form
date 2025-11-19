package com.idscodelabs.compose_form.form.fields.core.radio

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.TextFieldValue
import com.idscodelabs.compose_form.form.core.FormScope
import com.idscodelabs.compose_form.form.fields.core.base.FormFieldImplementation
import com.idscodelabs.compose_form.form.fields.core.base.FormFieldImplementationParameters
import com.idscodelabs.compose_form.form.fields.core.base.FormFieldWrapper
import com.idscodelabs.compose_form.form.fields.core.base.IFormFieldImplementation
import com.idscodelabs.compose_form.form.fields.core.base.ListDisplayable
import com.idscodelabs.compose_form.form.fields.core.dropdown.DropdownFormFieldImplementationParameters
import com.idscodelabs.compose_form.form.fields.strings.asDisplayString
import com.idscodelabs.compose_form.form.model.FormBox
import com.idscodelabs.compose_form.validators.InvalidOptionValidator
import com.idscodelabs.compose_form.validators.core.Validator
import kotlin.reflect.KProperty

@Composable
fun <Model, Item : ListDisplayable> FormScope<Model>.FormRadioField(
    modelProperty: KProperty<Item?>,
    initialValue: Item?,
    enabled: Boolean,
    validator: Validator?,
    updateModel: Model.(Item?) -> Unit,
    options: List<Item>,
    invalidOptionError: Any = "Invalid Option",
    implementation: IFormFieldImplementation<RadioFormFieldImplementationParameters<Item>>,
) {
    val sortedOptions =
        remember(options) {
            options.sortedBy { it.position }
        }
    val displayableOptions = sortedOptions.map { it to it.label.asDisplayString() }

    val displayableOptionsListString =
        remember(displayableOptions) {
            displayableOptions.map { it.second }
        }

    FormFieldWrapper(
        modelProperty = modelProperty,
        initialValue = initialValue,
        enabled = enabled,
        validator = InvalidOptionValidator(displayableOptionsListString, invalidOptionError) + validator,
        updateModel = updateModel,
        implementation = implementation,
        formImplementationMapper = { RadioFormFieldImplementationParameters(this, options) },
        valueToStored = { item ->
            displayableOptions.indexOfFirst { it.first.key == item?.key }.takeIf { it != -1 }
        },
        storedToString = { it.toString() },
        stringToValue = { string ->
            string?.toIntOrNull()?.takeIf { it != -1 }?.let {
                sortedOptions.getOrNull(it)
            }
        },
        rememberState = {
            rememberSaveable(it) {
                mutableIntStateOf(-1)
            }
        },
    )
}
