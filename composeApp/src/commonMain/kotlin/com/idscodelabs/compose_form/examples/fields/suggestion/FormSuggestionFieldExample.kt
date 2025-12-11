package com.idscodelabs.compose_form.examples.fields.suggestion

import androidx.compose.runtime.Composable
import com.idscodelabs.compose_form.examples.fields.dropdown.large.FormDropdownFieldExampleModelLarge
import com.idscodelabs.compose_form.examples.fields.dropdown.large.FormDropdownFieldExampleOptionLarge
import com.idscodelabs.compose_form.examples.helpers.ExampleForm
import com.idscodelabs.compose_form.examples.helpers.ExampleScreen
import com.idscodelabs.compose_form.form.fields.core.dropdown.FormDropdownField
import com.idscodelabs.compose_form.form.fields.core.suggestion.FormSuggestionField
import com.idscodelabs.compose_form.form.fields.default.dropdown.DefaultAutocompleteFormDropdownEntry
import com.idscodelabs.compose_form.form.fields.default.suggestion.DefaultSuggestionDropdownEntry
import com.idscodelabs.compose_form.validators.NotEmptyValidator
import kotlinx.coroutines.delay
import org.jetbrains.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun FormSuggestionFieldExample() =
    ExampleScreen {
        ExampleForm(emptyModel = ::FormSuggestionFieldExampleModel) {
            FormSuggestionField(
                modelProperty = FormSuggestionFieldExampleModel::value,
                validator = NotEmptyValidator(),
                updateModel = { value = it },
                getSuggestions = { value ->
                    if (value.isNotEmpty()) {
                        delay(1000)
                        FormSuggestionFieldExampleOption.options.filter {
                            it.label.startsWith(value, ignoreCase = true)
                        }
                    } else {
                        emptyList()
                    }
                },
            ) {
                DefaultSuggestionDropdownEntry(hint = "Value")
            }
        }
    }
