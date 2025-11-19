package com.idscodelabs.compose_form.examples.fields

import androidx.compose.runtime.Composable
import com.idscodelabs.compose_form.examples.helpers.ExampleForm
import com.idscodelabs.compose_form.examples.helpers.ExampleModel
import com.idscodelabs.compose_form.examples.helpers.ExampleScreen
import com.idscodelabs.compose_form.form.core.Form
import com.idscodelabs.compose_form.form.fields.core.checkbox.FormCheckBoxField
import com.idscodelabs.compose_form.form.fields.core.switch.FormSwitchField
import com.idscodelabs.compose_form.form.fields.default.checkbox.DefaultFormCheckBoxEntry
import com.idscodelabs.compose_form.form.fields.default.switch.DefaultFormSwitchEntry
import com.idscodelabs.compose_form.validators.MustBeTickedValidator
import kotlinx.datetime.LocalTime
import org.jetbrains.compose.ui.tooling.preview.Preview

data class FormSwitchExampleModel(
    override var value: Boolean? = null,
) : ExampleModel<Boolean>

@Preview
@Composable
fun FormSwitchFieldExample() =
    ExampleScreen {
        ExampleForm(emptyModel = ::FormSwitchExampleModel) {
            FormSwitchField(
                modelProperty = FormSwitchExampleModel::value,
                initialValue = null,
                enabled = true,
                validator = null,
                updateModel = { value = it },
            ) {
                DefaultFormSwitchEntry(
                    hint = "Select from A or B",
                    leftLabel = "A",
                    rightLabel = "B",
                )
            }
        }
    }
