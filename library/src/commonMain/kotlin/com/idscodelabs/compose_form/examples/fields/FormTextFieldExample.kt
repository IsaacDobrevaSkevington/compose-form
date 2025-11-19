package com.idscodelabs.compose_form.examples.fields

import androidx.compose.runtime.Composable
import com.idscodelabs.compose_form.examples.helpers.ExampleForm
import com.idscodelabs.compose_form.examples.helpers.ExampleModel
import com.idscodelabs.compose_form.examples.helpers.ExampleScreen
import com.idscodelabs.compose_form.form.core.Form
import com.idscodelabs.compose_form.form.fields.core.text.FormTextField
import com.idscodelabs.compose_form.form.fields.default.text.DefaultTextEntry
import com.idscodelabs.compose_form.validators.NotEmptyValidator
import kotlinx.datetime.LocalTime
import org.jetbrains.compose.ui.tooling.preview.Preview

data class FormTextFieldExampleModel(
    override var value: String? = null
): ExampleModel<String>

@Preview
@Composable
fun FormTextFieldExample() = ExampleScreen {
    ExampleForm(emptyModel = ::FormTextFieldExampleModel){
        FormTextField(
            modelProperty = FormTextFieldExampleModel::value,
            initialValue = null,
            enabled = true,
            validator = NotEmptyValidator(),
            updateModel = { value = it },
        ){
            DefaultTextEntry(hint = "Value")
        }
    }
}