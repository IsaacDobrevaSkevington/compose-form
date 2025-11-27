package com.idscodelabs.compose_form.examples.validators

import androidx.compose.runtime.Composable
import com.idscodelabs.compose_form.examples.fields.textfield.FormTextFieldExampleModel
import com.idscodelabs.compose_form.examples.fields.textfield.FormTextFieldExampleModelDouble
import com.idscodelabs.compose_form.examples.helpers.ExampleForm
import com.idscodelabs.compose_form.examples.helpers.ExampleScreen
import com.idscodelabs.compose_form.form.core.Form
import com.idscodelabs.compose_form.form.fields.core.text.FormTextField
import com.idscodelabs.compose_form.form.fields.default.text.DefaultTextEntry
import com.idscodelabs.compose_form.validators.MaxLengthValidator
import com.idscodelabs.compose_form.validators.NotEmptyIfOtherNotPopulatedValidator
import com.idscodelabs.compose_form.validators.core.Validator
import org.jetbrains.compose.ui.tooling.preview.Preview
import kotlin.reflect.KProperty

@Preview
@Composable
fun NotEmptyIfOtherNotPopulatedValidatorExample() =
    ExampleScreen {
        ExampleForm(emptyModel = ::FormTextFieldExampleModelDouble) {
            FormTextField(
                modelProperty = FormTextFieldExampleModelDouble::value1,
                initialValue = null,
                enabled = true,
                validator = null,
                updateModel = { value1 = it },
            ) {
                DefaultTextEntry(hint = "Value 1")
            }
            FormTextField(
                modelProperty = FormTextFieldExampleModelDouble::value2,
                initialValue = null,
                enabled = true,
                validator = NotEmptyIfOtherNotPopulatedValidator(FormTextFieldExampleModelDouble::value1),
                updateModel = { value2 = it },
            ) {
                DefaultTextEntry(hint = "Value 2")
            }
        }
    }
