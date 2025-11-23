package com.idscodelabs.compose_form.examples.fields.custom.entry

import androidx.compose.runtime.Composable
import com.idscodelabs.compose_form.examples.fields.radio.FormRadioFieldExampleModel
import com.idscodelabs.compose_form.examples.fields.radio.FormRadioFieldExampleOption
import com.idscodelabs.compose_form.examples.helpers.ExampleForm
import com.idscodelabs.compose_form.examples.helpers.ExampleScreen
import com.idscodelabs.compose_form.form.fields.core.radio.FormRadioField
import com.idscodelabs.compose_form.validators.NotEmptyValidator
import org.jetbrains.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun CustomFormFieldEntryExample() =
    ExampleScreen {
        ExampleForm(emptyModel = ::FormRadioFieldExampleModel) {
            FormRadioField(
                modelProperty = FormRadioFieldExampleModel::value,
                validator = NotEmptyValidator(),
                updateModel = { value = it },
                options = FormRadioFieldExampleOption.entries,
            ) {
                CustomFormFieldRadioEntry(hint = "Value")
            }
        }
    }
