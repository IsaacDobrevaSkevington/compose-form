package com.idscodelabs.compose_form.examples.fields

import androidx.compose.runtime.Composable
import com.idscodelabs.compose_form.examples.helpers.ExampleForm
import com.idscodelabs.compose_form.examples.helpers.ExampleModel
import com.idscodelabs.compose_form.examples.helpers.ExampleScreen
import com.idscodelabs.compose_form.form.core.Form
import com.idscodelabs.compose_form.form.fields.core.date.FormDateField
import com.idscodelabs.compose_form.form.fields.core.date.rememberDatePickerMinMaxState
import com.idscodelabs.compose_form.form.fields.default.date.DefaultDateEntry
import com.idscodelabs.compose_form.validators.DateAfterValidator
import com.idscodelabs.compose_form.validators.DateBeforeValidator
import com.idscodelabs.compose_form.validators.MultipleValidator
import com.idscodelabs.compose_form.validators.NotEmptyValidator
import kotlinx.datetime.LocalDate
import org.jetbrains.compose.ui.tooling.preview.Preview
import kotlin.time.ExperimentalTime

data class FormDateFieldExampleModel(
    override var value: LocalDate? = null
): ExampleModel<LocalDate>

@OptIn(ExperimentalTime::class)
@Preview
@Composable
fun FormDateFieldExample() = ExampleScreen {
    ExampleForm(emptyModel = ::FormDateFieldExampleModel){
        val min = LocalDate(2020, 1,1)
        val max = LocalDate(2025, 2,1)
        FormDateField(
            modelProperty = FormDateFieldExampleModel::value,
            initialValue = null,
            enabled = true,
            validator = NotEmptyValidator() +
                    DateBeforeValidator(min) +
                    DateAfterValidator(max),
            updateModel = { value = it },
        ){
            DefaultDateEntry(
                hint = "Date",
                datePickerState = rememberDatePickerMinMaxState(min, max)
            )
        }
    }
}