package com.idscodelabs.compose_form.examples.fields.dropdown.normal

import androidx.compose.runtime.Composable
import com.idscodelabs.compose_form.examples.fields.dropdown.normal.FormDropdownFieldExampleModel
import com.idscodelabs.compose_form.examples.helpers.ExampleForm
import com.idscodelabs.compose_form.examples.helpers.ExampleScreen
import com.idscodelabs.compose_form.form.fields.core.dropdown.FormDropdownField
import com.idscodelabs.compose_form.form.fields.default.dropdown.DefaultAutocompleteFormDropdownEntry
import com.idscodelabs.compose_form.validators.NotEmptyValidator
import org.jetbrains.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun FormAutocompleteDropdownFieldExample() =
    ExampleScreen {
        ExampleForm(emptyModel = ::FormDropdownFieldExampleModel) {
            FormDropdownField(
                modelProperty = FormDropdownFieldExampleModel::value,
                validator = NotEmptyValidator(),
                updateModel = { value = it },
                options = FormDropdownFieldExampleOption.entries,
            ) {
                DefaultAutocompleteFormDropdownEntry(hint = "Value")
            }
        }
    }
