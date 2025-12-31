package com.idscodelabs.compose_form.form.fields.core.suggestion

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.text.input.TextFieldValue
import com.idscodelabs.compose_form.form.fields.core.base.DisplayableOption
import com.idscodelabs.compose_form.form.fields.core.base.ListDisplayable
import com.idscodelabs.compose_form.form.fields.core.dropdown.DropdownFormBox
import com.idscodelabs.compose_form.form.fields.strings.asDisplayString
import com.idscodelabs.compose_form.form.model.FormBox
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.update

/**
 * [FormBox] specific to dropdowns, also holds a list of [DisplayableOption]s to allow use in the UI
 *
 * @param Model The Model of the Form
 * @param Item The type of Item to be displayed in the dropdown
 * @property field A base [FormBox]
 * @property options A list of [Item]s which can be selected from the UI
 * @constructor Create Dropdown form box
 */
open class SuggestionFormBox<Model, Item : ListDisplayable>(
    field: FormBox<Model, TextFieldValue>,
    private val debounce: Long = 300L,
    private val getSuggestions: suspend (String) -> List<Item>,
) : FormBox<Model, TextFieldValue>(field) {
    val loadingState: MutableStateFlow<Boolean> = MutableStateFlow(false)

    @OptIn(ExperimentalCoroutinesApi::class, FlowPreview::class)
    val suggestionsState =
        valueState.debounce(debounce).mapLatest {
            loadingState.update { true }
            try {
                getSuggestions(it.text)
            } finally {
                loadingState.update { false }
            }
        }

    @Composable
    fun collectLoadingAsState(): State<Boolean> = loadingState.collectAsState()

    @Composable
    fun collectSuggestionsAsState(): State<List<Item>> = suggestionsState.collectAsState(emptyList())
}

@Composable
fun <Model, Suggestion : ListDisplayable> FormBox<Model, TextFieldValue>.rememberAsSuggestionFormBox(
    debounce: Long,
    getSuggestions: suspend (String) -> List<Suggestion>,
) = remember(this, getSuggestions, debounce) {
    SuggestionFormBox(this, debounce, getSuggestions)
}
