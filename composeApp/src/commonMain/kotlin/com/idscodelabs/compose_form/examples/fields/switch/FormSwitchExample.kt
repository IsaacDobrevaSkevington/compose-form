package com.idscodelabs.compose_form.examples.fields.switch

import androidx.compose.runtime.Composable
import com.idscodelabs.compose_form.examples.fields.switch.FormSwitchExampleModel
import com.idscodelabs.compose_form.examples.helpers.ExampleForm
import com.idscodelabs.compose_form.examples.helpers.ExampleScreen
import com.idscodelabs.compose_form.form.fields.core.switch.FormSwitchField
import com.idscodelabs.compose_form.form.fields.default.switch.DefaultFormSwitchEntry
import org.jetbrains.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun FormSwitchFieldExample() =
    ExampleScreen {
        ExampleForm(emptyModel = ::FormSwitchExampleModel) {
            FormSwitchField(
                modelProperty = FormSwitchExampleModel::value,
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
