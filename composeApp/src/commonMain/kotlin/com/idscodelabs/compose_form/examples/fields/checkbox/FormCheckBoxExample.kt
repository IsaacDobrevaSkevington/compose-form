package com.idscodelabs.compose_form.examples.fields.checkbox

import androidx.compose.runtime.Composable
import com.idscodelabs.compose_form.examples.fields.checkbox.FormCheckBoxExampleModel
import com.idscodelabs.compose_form.examples.helpers.ExampleForm
import com.idscodelabs.compose_form.examples.helpers.ExampleScreen
import com.idscodelabs.compose_form.form.core.FormScope
import com.idscodelabs.compose_form.form.fields.core.checkbox.FormCheckBoxField
import com.idscodelabs.compose_form.form.fields.default.checkbox.DefaultFormCheckBoxEntry
import com.idscodelabs.compose_form.validators.MustBeTickedValidator
import org.jetbrains.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun FormCheckBoxFieldExample() =
    ExampleScreen {
        ExampleForm(emptyModel = ::FormCheckBoxExampleModel) {
            FormCheckBoxFieldSample()
        }
    }

@Composable
fun FormScope<FormCheckBoxExampleModel>.FormCheckBoxFieldSample() =
    FormCheckBoxField(
        modelProperty = FormCheckBoxExampleModel::value,
        validator = MustBeTickedValidator(),
        updateModel = { value = it },
    ) {
        DefaultFormCheckBoxEntry(hint = "Check Me")
    }
