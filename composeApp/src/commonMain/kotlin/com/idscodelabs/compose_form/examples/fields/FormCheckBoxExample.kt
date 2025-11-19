package com.idscodelabs.compose_form.examples.fields

import androidx.compose.runtime.Composable
import com.idscodelabs.compose_form.examples.helpers.ExampleForm
import com.idscodelabs.compose_form.examples.helpers.ExampleModel
import com.idscodelabs.compose_form.examples.helpers.ExampleScreen
import com.idscodelabs.compose_form.form.core.Form
import com.idscodelabs.compose_form.form.fields.core.checkbox.FormCheckBoxField
import com.idscodelabs.compose_form.form.fields.default.checkbox.DefaultFormCheckBoxEntry
import com.idscodelabs.compose_form.validators.MustBeTickedValidator
import org.jetbrains.compose.ui.tooling.preview.Preview

data class FormCheckBoxExampleModel(
    override var value: Boolean? = null,
) : ExampleModel<Boolean>

@Preview
@Composable
fun FormCheckBoxFieldExample() =
    ExampleScreen {
        ExampleForm(emptyModel = ::FormCheckBoxExampleModel) {
            FormCheckBoxField(
                modelProperty = FormCheckBoxExampleModel::value,
                initialValue = null,
                enabled = true,
                validator = MustBeTickedValidator(),
                updateModel = { value = it },
            ) {
                DefaultFormCheckBoxEntry(hint = "Check Me")
            }
        }
    }
