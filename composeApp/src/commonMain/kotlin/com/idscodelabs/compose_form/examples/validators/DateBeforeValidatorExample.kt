package com.idscodelabs.compose_form.examples.validators

import androidx.compose.runtime.Composable
import com.idscodelabs.compose_form.examples.fields.date.FormDateFieldExampleModel
import com.idscodelabs.compose_form.examples.helpers.ExampleForm
import com.idscodelabs.compose_form.examples.helpers.ExampleScreen
import com.idscodelabs.compose_form.form.fields.core.date.FormDateField
import com.idscodelabs.compose_form.form.fields.default.date.DefaultDateEntry
import com.idscodelabs.compose_form.validators.DateBeforeValidator
import kotlinx.datetime.LocalDate
import org.jetbrains.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun DateBeforeValidatorExample() =
    ExampleScreen {
        ExampleForm(emptyModel = ::FormDateFieldExampleModel) {
            FormDateField(
                modelProperty = FormDateFieldExampleModel::value,
                initialValue = null,
                enabled = true,
                validator = DateBeforeValidator(LocalDate(2000, 1, 1)),
                updateModel = { value = it },
            ) {
                DefaultDateEntry(hint = "Value")
            }
        }
    }
