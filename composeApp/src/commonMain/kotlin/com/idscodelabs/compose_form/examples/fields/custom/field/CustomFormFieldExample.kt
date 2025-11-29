package com.idscodelabs.compose_form.examples.fields.custom.field

import androidx.compose.runtime.Composable
import com.idscodelabs.compose_form.examples.fields.custom.field.CustomFormFieldExampleModel
import com.idscodelabs.compose_form.examples.fields.custom.field.SuggestionField
import com.idscodelabs.compose_form.examples.fields.dropdown.large.FormDropdownFieldExampleOptionLarge
import com.idscodelabs.compose_form.examples.fields.dropdown.normal.FormDropdownFieldExampleOption
import com.idscodelabs.compose_form.examples.helpers.ExampleForm
import com.idscodelabs.compose_form.examples.helpers.ExampleScreen
import com.idscodelabs.compose_form.form.fields.default.dropdown.DefaultAutocompleteFormDropdownEntry
import com.idscodelabs.compose_form.validators.NotEmptyValidator
import org.jetbrains.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun CustomFormFieldExample() =
    ExampleScreen {
        ExampleForm(emptyModel = ::CustomFormFieldExampleModel) {
            SuggestionField(
                modelProperty = CustomFormFieldExampleModel::value,
                validator = NotEmptyValidator(),
                updateModel = { value = it },
                suggestions = FormDropdownFieldExampleOptionLarge.options,
            ) {
                DefaultAutocompleteFormDropdownEntry(
                    hint = "Value",
                    placeholder = "Start typing...",
                    filterFunction = { item, value ->
                        !value.isBlank() && item.startsWith(value, ignoreCase = true)
                    },
                )
            }
        }
    }
