package com.idscodelabs.compose_form.examples.validators

import androidx.compose.runtime.Composable
import com.idscodelabs.compose_form.examples.fields.dropdown.large.FormDropdownFieldExampleOptionLarge
import com.idscodelabs.compose_form.examples.fields.multiselect.FormMultiselectFieldExampleModel
import com.idscodelabs.compose_form.examples.fields.textfield.FormTextFieldExampleModel
import com.idscodelabs.compose_form.examples.helpers.ExampleForm
import com.idscodelabs.compose_form.examples.helpers.ExampleScreen
import com.idscodelabs.compose_form.form.fields.core.multiselect.FormMultiselectField
import com.idscodelabs.compose_form.form.fields.core.text.FormTextField
import com.idscodelabs.compose_form.form.fields.default.multiselect.DefaultFormMultiselectEntry
import com.idscodelabs.compose_form.form.fields.default.text.DefaultTextEntry
import com.idscodelabs.compose_form.validators.MaxCountValidator
import com.idscodelabs.compose_form.validators.MinCountValidator
import com.idscodelabs.compose_form.validators.MinLengthValidator
import com.idscodelabs.compose_form.validators.NotEmptyValidator
import org.jetbrains.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun MaxCountValidatorExample() =
    ExampleScreen {
        ExampleForm(emptyModel = ::FormMultiselectFieldExampleModel) {
            FormMultiselectField(
                modelProperty = FormMultiselectFieldExampleModel::value,
                validator = NotEmptyValidator<List<FormDropdownFieldExampleOptionLarge>>() + MaxCountValidator(3),
                updateModel = { value = it },
                options = FormDropdownFieldExampleOptionLarge.options,
            ) {
                DefaultFormMultiselectEntry(hint = "Select up to 3 options")
            }
        }
    }
