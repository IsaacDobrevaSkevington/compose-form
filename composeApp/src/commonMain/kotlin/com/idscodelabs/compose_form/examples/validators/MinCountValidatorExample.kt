package com.idscodelabs.compose_form.examples.validators

import androidx.compose.runtime.Composable
import com.idscodelabs.compose_form.examples.fields.dropdown.large.FormDropdownFieldExampleOptionLarge
import com.idscodelabs.compose_form.examples.fields.multiselect.FormMultiselectFieldExampleModel
import com.idscodelabs.compose_form.examples.helpers.ExampleForm
import com.idscodelabs.compose_form.examples.helpers.ExampleScreen
import com.idscodelabs.compose_form.form.fields.core.multiselect.FormMultiselectField
import com.idscodelabs.compose_form.form.fields.default.multiselect.DefaultFormMultiselectEntry
import com.idscodelabs.compose_form.validators.MinCountValidator
import org.jetbrains.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun MinCountValidatorExample() =
    ExampleScreen {
        ExampleForm(emptyModel = ::FormMultiselectFieldExampleModel) {
            FormMultiselectField(
                modelProperty = FormMultiselectFieldExampleModel::value,
                validator = MinCountValidator(3),
                updateModel = { value = it },
                options = FormDropdownFieldExampleOptionLarge.options,
            ) {
                DefaultFormMultiselectEntry(hint = "Select at least 3 options")
            }
        }
    }
