package com.idscodelabs.compose_form.examples.fields.multiselect

import androidx.compose.runtime.Composable
import com.idscodelabs.compose_form.examples.fields.dropdown.large.FormDropdownFieldExampleModelLarge
import com.idscodelabs.compose_form.examples.fields.dropdown.large.FormDropdownFieldExampleOptionLarge
import com.idscodelabs.compose_form.examples.fields.dropdown.normal.FormDropdownFieldExampleOption
import com.idscodelabs.compose_form.examples.fields.multiselect.FormMultiselectFieldExampleModel
import com.idscodelabs.compose_form.examples.helpers.ExampleForm
import com.idscodelabs.compose_form.examples.helpers.ExampleScreen
import com.idscodelabs.compose_form.form.fields.core.dropdown.FormDropdownField
import com.idscodelabs.compose_form.form.fields.core.multiselect.FormMultiselectField
import com.idscodelabs.compose_form.form.fields.default.dropdown.DefaultFormDropdownEntry
import com.idscodelabs.compose_form.form.fields.default.multiselect.DefaultFormMultiselectEntry
import com.idscodelabs.compose_form.validators.NotEmptyValidator
import org.jetbrains.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun FormMultiselectFieldExample() =
    ExampleScreen {
        ExampleForm(emptyModel = ::FormMultiselectFieldExampleModel) {
            FormMultiselectField(
                modelProperty = FormMultiselectFieldExampleModel::value,
                validator = NotEmptyValidator(),
                updateModel = { value = it },
                options = FormDropdownFieldExampleOptionLarge.options,
            ) {
                DefaultFormMultiselectEntry(hint = "Value")
            }
        }
    }
