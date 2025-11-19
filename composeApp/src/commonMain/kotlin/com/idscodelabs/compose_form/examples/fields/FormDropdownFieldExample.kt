package com.idscodelabs.compose_form.examples.fields

import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.dp
import com.idscodelabs.compose_form.examples.helpers.ExampleForm
import com.idscodelabs.compose_form.examples.helpers.ExampleModel
import com.idscodelabs.compose_form.examples.helpers.ExampleScreen
import com.idscodelabs.compose_form.form.core.Form
import com.idscodelabs.compose_form.form.fields.core.base.ListDisplayable
import com.idscodelabs.compose_form.form.fields.core.dropdown.FormDropdownField
import com.idscodelabs.compose_form.form.fields.default.dropdown.DefaultAutocompleteFormDropdownEntry
import com.idscodelabs.compose_form.form.fields.default.dropdown.DefaultFormDropdownEntry
import com.idscodelabs.compose_form.validators.NotEmptyValidator
import org.jetbrains.compose.ui.tooling.preview.Preview

class FormDropdownFieldExampleOptionLarge(
    override val key: Any,
    override val label: Any,
    override val position: Int,
) : ListDisplayable {
    override fun toString(): String = label.toString()

    companion object {
        val options =
            (0..1000).map {
                FormDropdownFieldExampleOptionLarge(it.toString(), "Option $it", it)
            }
    }
}

enum class FormDropdownFieldExampleOption : ListDisplayable {
    OPTION_1,
    OPTION_2,
    ;

    override val key: Any
        get() = ordinal

    override val label: Any
        get() = name.replace("_", " ").lowercase().capitalize(Locale.current)

    override val position: Int
        get() = ordinal
}

data class FormDropdownFieldExampleModel(
    override var value: FormDropdownFieldExampleOption? = null,
) : ExampleModel<FormDropdownFieldExampleOption>

data class FormDropdownFieldExampleModelLarge(
    override var value: FormDropdownFieldExampleOptionLarge? = null,
) : ExampleModel<FormDropdownFieldExampleOptionLarge>

@Preview
@Composable
fun FormDropdownFieldExample() =
    ExampleScreen {
        ExampleForm(emptyModel = ::FormDropdownFieldExampleModel) {
            FormDropdownField(
                modelProperty = FormDropdownFieldExampleModel::value,
                initialValue = null,
                enabled = true,
                validator = NotEmptyValidator(),
                updateModel = { value = it },
                options = FormDropdownFieldExampleOption.entries,
            ) {
                DefaultFormDropdownEntry(hint = "Value")
            }
        }
    }

@Preview
@Composable
fun FormAutocompleteDropdownFieldExample() =
    ExampleScreen {
        ExampleForm(emptyModel = ::FormDropdownFieldExampleModel) {
            FormDropdownField(
                modelProperty = FormDropdownFieldExampleModel::value,
                initialValue = null,
                enabled = true,
                validator = NotEmptyValidator(),
                updateModel = { value = it },
                options = FormDropdownFieldExampleOption.entries,
            ) {
                DefaultAutocompleteFormDropdownEntry(hint = "Value")
            }
        }
    }

@Preview
@Composable
fun FormDropdownFieldExampleLarge() =
    ExampleScreen {
        ExampleForm(emptyModel = ::FormDropdownFieldExampleModelLarge) {
            FormDropdownField(
                modelProperty = FormDropdownFieldExampleModelLarge::value,
                initialValue = null,
                enabled = true,
                validator = NotEmptyValidator(),
                updateModel = { value = it },
                options = FormDropdownFieldExampleOptionLarge.options,
            ) {
                DefaultFormDropdownEntry(hint = "Value")
            }
        }
    }

@Preview
@Composable
fun FormAutocompleteDropdownFieldExampleLarge() =
    ExampleScreen {
        ExampleForm(emptyModel = ::FormDropdownFieldExampleModelLarge) {
            FormDropdownField(
                modelProperty = FormDropdownFieldExampleModelLarge::value,
                initialValue = null,
                enabled = true,
                validator = NotEmptyValidator(),
                updateModel = { value = it },
                options = FormDropdownFieldExampleOptionLarge.options,
            ) {
                DefaultAutocompleteFormDropdownEntry(hint = "Value")
            }
        }
    }
