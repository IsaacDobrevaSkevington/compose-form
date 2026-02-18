package com.idscodelabs.compose_form.examples.logic.compositionlocal

import androidx.compose.runtime.Composable
import com.idscodelabs.compose_form.examples.fields.textfield.FormTextFieldExampleModel
import com.idscodelabs.compose_form.examples.helpers.ExampleForm
import com.idscodelabs.compose_form.examples.helpers.ExampleScreen
import com.idscodelabs.compose_form.form.core.ui.UntypedFormExtension
import com.idscodelabs.compose_form.form.fields.core.text.FormTextField
import com.idscodelabs.compose_form.validators.NotEmptyValidator
import org.jetbrains.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun ReusableValueChangeAnalyticsButtonExample() =
    ExampleScreen {
        ExampleForm(emptyModel = ::FormTextFieldExampleModel) {
            FormTextField(
                modelProperty = FormTextFieldExampleModel::value,
                validator = NotEmptyValidator(),
                updateModel = { value = it },
                hint = "Value",
            )
            ValueChangedSettledAnalytics()
        }
    }

private object Analytics {
    fun logValueChanged() {}
}

@Composable
fun ValueChangedSettledAnalytics() =
    UntypedFormExtension {
        ValueChangedEffect(debounceMillis = 5000) {
            Analytics.logValueChanged()
        }
    }
