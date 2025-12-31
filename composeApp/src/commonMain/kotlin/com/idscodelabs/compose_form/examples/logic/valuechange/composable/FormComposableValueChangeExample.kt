package com.idscodelabs.compose_form.examples.logic.valuechange.composable

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
fun FormComposableValueChangeExample() =
    ExampleScreen {
        ExampleForm(emptyModel = ::FormComposableValueChangeExampleModel) {
            val value by collectValueAsState()
            FormDropdownField(
                modelProperty = FormComposableValueChangeExampleModel::option,
                validator = NotEmptyValidator(),
                updateModel = { option = it },
                options = FormComposableValueChangeExampleOption.entries,
            ) {
                DefaultFormDropdownEntry(hint = "Select Field Type")
            }

            when (value.option) {
                FormComposableValueChangeExampleOption.DATE_FIELD -> {
                    FormDateField(
                        modelProperty = FormComposableValueChangeExampleModel::date,
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

                FormComposableValueChangeExampleOption.TIME_FIELD -> {
                    FormTimeField(
                        modelProperty = FormComposableValueChangeExampleModel::time,
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
