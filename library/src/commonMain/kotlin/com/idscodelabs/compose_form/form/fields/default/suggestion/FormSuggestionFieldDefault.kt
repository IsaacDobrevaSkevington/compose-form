package com.idscodelabs.compose_form.form.fields.default.suggestion

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.unit.dp
import com.idscodelabs.compose_form.form.core.controller.FormController
import com.idscodelabs.compose_form.form.fields.core.base.DisplayableOption
import com.idscodelabs.compose_form.form.fields.core.base.ListDisplayable
import com.idscodelabs.compose_form.form.fields.core.suggestion.FormSuggestionField
import com.idscodelabs.compose_form.form.fields.core.suggestion.SuggestionFormBox
import com.idscodelabs.compose_form.form.fields.default.dropdown.DefaultDropdownMenuItem
import com.idscodelabs.compose_form.form.icons.Icons
import com.idscodelabs.compose_form.utils.IconButton
import com.idscodelabs.compose_form.validators.core.Validator
import kotlin.reflect.KProperty

@Composable
fun <Model, Suggestion : ListDisplayable> FormController<Model>.FormSuggestionField(
    modelProperty: KProperty<*>,
    updateModel: Model.(String?) -> Unit,
    getSuggestions: suspend (String) -> List<Suggestion>,
    debounce: Long = 300L,
    initialValue: String? = null,
    validator: Validator<String>? = null,
    enabled: Boolean = true,
    textFieldModifier: Modifier = Modifier.fillMaxWidth(),
    exposedDropdownBoxModifier: Modifier = Modifier,
    exposedDropdownMenuModifier: Modifier = Modifier,
    hint: Any? = null,
    placeholder: Any? = hint,
    isLast: Boolean = false,
    leadingIcon: (@Composable SuggestionFormBox<Model, Suggestion>.() -> Unit)? = null,
    clearIcon: (@Composable SuggestionFormBox<Model, Suggestion>.(onClick: () -> Unit) -> Unit)? = {
        IconButton(Icons.Close, "Clear Icon") { it() }
    },
    expandIcon: @Composable SuggestionFormBox<Model, Suggestion>.(expanded: Boolean) -> Unit = {
        IconButton(Icons.ArrowDropDown, "Expand Icon", iconModifier = Modifier.rotate(if (it) 180f else 0f)) {}
    },
    menuItem: @Composable SuggestionFormBox<Model, Suggestion>.(
        item: DisplayableOption<Suggestion>,
        setExpanded: (Boolean) -> Unit,
    ) -> Unit = { item, setExpanded ->
        DefaultDropdownMenuItem(item, setExpanded)
    },
    loadingView: @Composable () -> Unit = {
        CircularProgressIndicator(Modifier.size(48.dp).padding(8.dp))
    },
) = FormSuggestionField(
    modelProperty = modelProperty,
    updateModel = updateModel,
    getSuggestions = getSuggestions,
    debounce = debounce,
    initialValue = initialValue,
    validator = validator,
    enabled = enabled,
) {
    DefaultSuggestionDropdownEntry(
        textFieldModifier = textFieldModifier,
        exposedDropdownBoxModifier = exposedDropdownBoxModifier,
        exposedDropdownMenuModifier = exposedDropdownMenuModifier,
        hint = hint,
        placeholder = placeholder,
        isLast = isLast,
        leadingIcon = leadingIcon,
        clearIcon = clearIcon,
        expandIcon = expandIcon,
        menuItem = menuItem,
        loadingView = loadingView,
    )
}
