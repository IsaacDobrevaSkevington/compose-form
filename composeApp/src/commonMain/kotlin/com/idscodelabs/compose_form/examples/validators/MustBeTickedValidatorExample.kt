package com.idscodelabs.compose_form.examples.validators

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.idscodelabs.compose_form.examples.fields.checkbox.FormCheckBoxExampleModel
import com.idscodelabs.compose_form.examples.helpers.ExampleForm
import com.idscodelabs.compose_form.examples.helpers.ExampleScreen
import com.idscodelabs.compose_form.form.fields.core.checkbox.FormCheckBoxField
import com.idscodelabs.compose_form.form.fields.default.checkbox.DefaultFormCheckBoxEntry
import com.idscodelabs.compose_form.validators.MustBeTickedValidator

@Preview
@Composable
fun MustBeTickedValidatorExample() =
    ExampleScreen {
        ExampleForm(emptyModel = ::FormCheckBoxExampleModel) {
            FormCheckBoxField(
                modelProperty = FormCheckBoxExampleModel::value,
                validator = MustBeTickedValidator(),
                updateModel = { value = it },
            ) {
                DefaultFormCheckBoxEntry(hint = "Check Me")
            }
        }
    }
