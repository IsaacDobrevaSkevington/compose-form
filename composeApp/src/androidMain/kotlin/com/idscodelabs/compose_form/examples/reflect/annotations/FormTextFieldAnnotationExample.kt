package com.idscodelabs.compose_form.examples.reflect.annotations

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.idscodelabs.compose_form.examples.fields.textfield.FormTextFieldExampleModel
import com.idscodelabs.compose_form.examples.helpers.ExampleScreen
import com.idscodelabs.compose_form.examples.reflect.base.ExampleFormLayout
import com.idscodelabs.compose_form.form.fields.core.text.FormTextField
import com.idscodelabs.compose_form.ui.Form

@Preview
@Composable
fun FormTextFieldAnnotationExample() =
    ExampleScreen {
        Form<FormTextFieldAnnotationExampleModel> {
            ExampleFormLayout {
                FormTextField(FormTextFieldAnnotationExampleModel::value)
            }
        }
    }
