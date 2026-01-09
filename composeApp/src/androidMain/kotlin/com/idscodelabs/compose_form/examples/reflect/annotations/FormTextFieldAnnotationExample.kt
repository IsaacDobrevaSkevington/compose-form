package com.idscodelabs.compose_form.examples.reflect.annotations

import androidx.compose.runtime.Composable
import com.idscodelabs.compose_form.examples.fields.textfield.FormTextFieldExampleModel
import com.idscodelabs.compose_form.examples.helpers.ExampleScreen
import com.idscodelabs.compose_form.examples.reflect.base.ExampleFormLayout
import com.idscodelabs.compose_form.form.fields.core.text.FormTextField
import com.idscodelabs.compose_form.ui.Form
import org.jetbrains.compose.ui.tooling.preview.Preview

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
