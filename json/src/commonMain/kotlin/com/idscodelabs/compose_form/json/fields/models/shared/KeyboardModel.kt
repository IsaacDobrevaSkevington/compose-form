package com.idscodelabs.compose_form.json.fields.models.shared

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Stable
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import kotlinx.serialization.Serializable


enum class KeyboardCapitalizationSpec(
    val value: KeyboardCapitalization,
) {
    Unspecified(KeyboardCapitalization.Unspecified),
    None(KeyboardCapitalization.None),
    Characters(KeyboardCapitalization.Characters),
    Words(KeyboardCapitalization.Words),
    Sentences(KeyboardCapitalization.Sentences),
}

enum class KeyboardTypeSpec(
    val value: KeyboardType,
) {
    Unspecified(KeyboardType.Unspecified),
    Text(KeyboardType.Text),
    Ascii(KeyboardType.Ascii),
    Number(KeyboardType.Number),
    Phone(KeyboardType.Phone),
    Uri(KeyboardType.Uri),
    Email(KeyboardType.Email),
    Password(KeyboardType.Password),
    NumberPassword(KeyboardType.NumberPassword),
    Decimal(KeyboardType.Decimal)
}

enum class ImeActionSpec(
    val value: ImeAction,
) {
    Unspecified(ImeAction.Unspecified),
    Default(ImeAction.Default),
    None(ImeAction.None),
    Go(ImeAction.Go),
    Search(ImeAction.Search),
    Send(ImeAction.Send),
    Previous(ImeAction.Previous),
    Next(ImeAction.Next),
    Done(ImeAction.Done)
}

@Serializable
data class KeyboardModel(
    val capitalization: KeyboardCapitalizationSpec = KeyboardCapitalizationSpec.Unspecified,
    val autoCorrectEnabled: Boolean? = null,
    val keyboardType: KeyboardTypeSpec = KeyboardTypeSpec.Unspecified,
    val imeAction: ImeActionSpec = ImeActionSpec.Unspecified,
    val showKeyboardOnFocus: Boolean? = null,
) {

    fun toKeyboardOptions(): KeyboardOptions = KeyboardOptions(
        capitalization = capitalization.value,
        autoCorrectEnabled = autoCorrectEnabled,
        keyboardType = keyboardType.value,
        imeAction = imeAction.value,
        showKeyboardOnFocus = showKeyboardOnFocus
    )
}