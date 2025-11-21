package com.idscodelabs.compose_form.examples.fields.slider

import androidx.compose.runtime.Composable
import com.idscodelabs.compose_form.examples.helpers.ExampleForm
import com.idscodelabs.compose_form.examples.helpers.ExampleModel
import com.idscodelabs.compose_form.examples.helpers.ExampleScreen
import com.idscodelabs.compose_form.form.fields.core.slider.FormSliderField
import com.idscodelabs.compose_form.form.fields.default.slider.DefaultFormSliderEntry
import org.jetbrains.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun FormSliderFieldExample() =
    ExampleScreen {
        ExampleForm(emptyModel = ::FormSliderExampleModel) {
            FormSliderField(
                modelProperty = FormSliderExampleModel::value,
                updateModel = { value = it },
            ) {
                DefaultFormSliderEntry(
                    hint = "Slide Me",
                )
            }
        }
    }
