package com.idscodelabs.compose_form.examples.validators

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.idscodelabs.compose_form.examples.fields.date.FormDateFieldExampleModel
import com.idscodelabs.compose_form.examples.helpers.ExampleForm
import com.idscodelabs.compose_form.examples.helpers.ExampleScreen
import com.idscodelabs.compose_form.form.fields.core.date.FormDateField
import com.idscodelabs.compose_form.form.fields.default.date.DefaultDateEntry
import com.idscodelabs.compose_form.validators.DateAfterValidator
import kotlinx.datetime.LocalDate

@Preview
@Composable
fun DateAfterValidatorExample() =
    ExampleScreen {
        ExampleForm(emptyModel = ::FormDateFieldExampleModel) {
            FormDateField(
                modelProperty = FormDateFieldExampleModel::value,
                initialValue = null,
                enabled = true,
                validator = DateAfterValidator(LocalDate(2000, 1, 1)),
                updateModel = { value = it },
            ) {
                DefaultDateEntry(hint = "Value")
            }
        }
    }
