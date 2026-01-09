package com.idscodelabs.compose_form.examples.reflect.reflectionSetter

import androidx.compose.runtime.Composable
import com.idscodelabs.compose_form.examples.fields.textfield.FormTextFieldExampleModel
import com.idscodelabs.compose_form.examples.helpers.ExampleScreen
import com.idscodelabs.compose_form.examples.reflect.base.ExampleFormLayout
import com.idscodelabs.compose_form.form.fields.core.text.FormTextField
import com.idscodelabs.compose_form.ui.Form
import com.idscodelabs.compose_form.validators.NotEmptyValidator
import org.jetbrains.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun FormTextFieldReflectionExample() =
    ExampleScreen {
        Form<FormTextFieldExampleModel> {
            ExampleFormLayout {
                FormTextField(
                    modelProperty = FormTextFieldExampleModel::value,
                    validator = NotEmptyValidator(),
                    hint = "Value"
                )
            }
        }
    }
