package com.idscodelabs.compose_form.examples.fields

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.intl.Locale
import com.idscodelabs.compose_form.examples.helpers.ExampleForm
import com.idscodelabs.compose_form.examples.helpers.ExampleModel
import com.idscodelabs.compose_form.examples.helpers.ExampleScreen
import com.idscodelabs.compose_form.form.core.Form
import com.idscodelabs.compose_form.form.fields.core.dropdown.FormDropdownField
import com.idscodelabs.compose_form.form.fields.core.base.ListDisplayable
import com.idscodelabs.compose_form.form.fields.core.radio.FormRadioField
import com.idscodelabs.compose_form.form.fields.default.dropdown.DefaultAutocompleteFormDropdownEntry
import com.idscodelabs.compose_form.form.fields.default.dropdown.DefaultFormDropdownEntry
import com.idscodelabs.compose_form.form.fields.default.radio.DefaultFormRadioEntry
import com.idscodelabs.compose_form.validators.NotEmptyValidator
import kotlinx.datetime.LocalTime
import org.jetbrains.compose.ui.tooling.preview.Preview

enum class FormRadioFieldExampleOption: ListDisplayable {
    OPTION_1,
    OPTION_2;

    override val key: Any
        get() = ordinal

    override val label: Any
        get() = name.replace("_", " ").lowercase().capitalize(Locale.current)

    override val position: Int
        get() = ordinal

}
data class FormRadioFieldExampleModel(
    override var value: FormRadioFieldExampleOption? = null
): ExampleModel<FormRadioFieldExampleOption>

@Preview
@Composable
fun FormRadioFieldExample() = ExampleScreen {
    ExampleForm(emptyModel = ::FormRadioFieldExampleModel){
        FormRadioField(
            modelProperty = FormRadioFieldExampleModel::value,
            initialValue = null,
            enabled = true,
            validator = NotEmptyValidator(),
            updateModel = { value = it },
            options = FormRadioFieldExampleOption.entries
        ){
            DefaultFormRadioEntry(hint = "Value")
        }
    }
}
