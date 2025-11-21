package com.idscodelabs.compose_form.examples.logic.valuechange

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import com.idscodelabs.compose_form.examples.helpers.ExampleForm
import com.idscodelabs.compose_form.examples.helpers.ExampleScreen
import com.idscodelabs.compose_form.form.fields.core.date.FormDateField
import com.idscodelabs.compose_form.form.fields.core.dropdown.FormDropdownField
import com.idscodelabs.compose_form.form.fields.core.time.FormTimeField
import com.idscodelabs.compose_form.form.fields.default.date.DefaultDateEntry
import com.idscodelabs.compose_form.form.fields.default.dropdown.DefaultFormDropdownEntry
import com.idscodelabs.compose_form.form.fields.default.time.DefaultTimeEntry
import com.idscodelabs.compose_form.validators.NotEmptyValidator
import org.jetbrains.compose.ui.tooling.preview.Preview
import kotlin.time.ExperimentalTime

@OptIn(ExperimentalMaterial3Api::class, ExperimentalTime::class)
@Preview
@Composable
fun FormValueChangeExample() =
    ExampleScreen {
        ExampleForm(emptyModel = ::FormValueChangeExampleModel) {
            val value by collectValueAsState()
            FormDropdownField(
                modelProperty = FormValueChangeExampleModel::option,
                validator = NotEmptyValidator(),
                updateModel = { option = it },
                options = FormValueChangeExampleOption.entries,
            ) {
                DefaultFormDropdownEntry(hint = "Select Field Type")
            }

            when (value.option) {
                FormValueChangeExampleOption.DATE_FIELD -> {
                    FormDateField(
                        modelProperty = FormValueChangeExampleModel::date,
                        validator =
                            NotEmptyValidator(),
                        updateModel = { date = it },
                    ) {
                        DefaultDateEntry(
                            hint = "Date",
                            allowTyping = false,
                        )
                    }
                }

                FormValueChangeExampleOption.TIME_FIELD -> {
                    FormTimeField(
                        modelProperty = FormValueChangeExampleModel::time,
                        validator = NotEmptyValidator(),
                        updateModel = { time = it },
                    ) {
                        DefaultTimeEntry(hint = "Time")
                    }
                }

                else -> {}
            }
        }
    }
