package com.idscodelabs.compose_form.examples.fields.file

import androidx.compose.runtime.Composable
import com.idscodelabs.compose_form.examples.fields.textfield.FormTextFieldExampleModel
import com.idscodelabs.compose_form.examples.helpers.ExampleForm
import com.idscodelabs.compose_form.examples.helpers.ExampleScreen
import com.idscodelabs.compose_form.form.fields.core.file.FormFileField
import com.idscodelabs.compose_form.form.fields.core.text.FormTextField
import com.idscodelabs.compose_form.form.fields.default.file.DefaultFileEntry
import com.idscodelabs.compose_form.form.fields.default.text.DefaultTextEntry
import com.idscodelabs.compose_form.validators.NotEmptyValidator
import org.jetbrains.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun FormFileFieldExample() =
    ExampleScreen {
        ExampleForm(emptyModel = ::FormFileFieldExampleModel) {
            FormFileField(
                modelProperty = FormFileFieldExampleModel::value,
                validator = NotEmptyValidator(),
                updateModel = { value = it },
            ) {
                DefaultFileEntry(hint = "Upload an Image")
            }
        }
    }
