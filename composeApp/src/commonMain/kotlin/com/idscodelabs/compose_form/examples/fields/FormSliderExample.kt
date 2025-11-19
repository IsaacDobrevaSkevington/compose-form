package com.idscodelabs.compose_form.examples.fields

import androidx.compose.runtime.Composable
import com.idscodelabs.compose_form.examples.helpers.ExampleForm
import com.idscodelabs.compose_form.examples.helpers.ExampleModel
import com.idscodelabs.compose_form.examples.helpers.ExampleScreen
import com.idscodelabs.compose_form.form.core.Form
import com.idscodelabs.compose_form.form.fields.core.checkbox.FormCheckBoxField
import com.idscodelabs.compose_form.form.fields.core.slider.FormSliderField
import com.idscodelabs.compose_form.form.fields.core.switch.FormSwitchField
import com.idscodelabs.compose_form.form.fields.default.checkbox.DefaultFormCheckBoxEntry
import com.idscodelabs.compose_form.form.fields.default.slider.DefaultFormSliderEntry
import com.idscodelabs.compose_form.form.fields.default.switch.DefaultFormSwitchEntry
import com.idscodelabs.compose_form.validators.MustBeTickedValidator
import org.jetbrains.compose.ui.tooling.preview.Preview

data class FormSliderExampleModel(
    override var value: Int? = null
): ExampleModel<Int>

@Preview
@Composable
fun FormSliderFieldExample() = ExampleScreen {
    ExampleForm(emptyModel = ::FormSliderExampleModel){
        FormSliderField(
            modelProperty = FormSliderExampleModel::value,
            initialValue = null,
            enabled = true,
            validator = null,
            updateModel = { value = it },
        ){
            DefaultFormSliderEntry(
                hint = "Slide Me",
            )
        }
    }
}