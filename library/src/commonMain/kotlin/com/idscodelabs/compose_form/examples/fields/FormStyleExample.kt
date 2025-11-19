package com.idscodelabs.compose_form.examples.fields

import androidx.compose.runtime.Composable
import com.idscodelabs.compose_form.examples.helpers.ExampleScreen
import com.idscodelabs.compose_form.form.core.Form
import com.idscodelabs.compose_form.form.fields.core.text.FormTextField
import com.idscodelabs.compose_form.form.fields.default.text.DefaultTextEntry
import com.idscodelabs.compose_form.styles.FormFieldStyle
import com.idscodelabs.compose_form.validators.NotEmptyValidator
import org.jetbrains.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun FormStyleExample() = ExampleScreen {
    Form(emptyModel = ::FormTextFieldExampleModel){
        FormFieldStyle.Outlined{
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
        FormFieldStyle.Filled{
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
}