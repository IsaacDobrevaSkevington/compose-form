package com.idscodelabs.compose_form.form.fields.default.radio

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.minimumInteractiveComponentSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.unit.dp
import com.idscodelabs.compose_form.form.fields.core.base.ListDisplayable
import com.idscodelabs.compose_form.form.fields.core.radio.RadioFormFieldImplementationParameters
import com.idscodelabs.compose_form.form.fields.strings.asDisplayString
import com.idscodelabs.compose_form.styles.LocalFormStyle

@Composable
fun <Item : ListDisplayable> RadioFormFieldImplementationParameters<Item>.DefaultFormRadioEntry(
    hint: Any?,
    modifier: Modifier = Modifier,
    textModifier: Modifier = Modifier,
    errorModifier: Modifier = Modifier,
    radioButton: @Composable (Item, Int) -> Unit = {item, index ->
        Row(
            Modifier
                .fillMaxWidth()
                .selectable(
                    selected = index == value,
                    onClick = { setValue(index) }
                )
                .minimumInteractiveComponentSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(LocalFormStyle.current.fieldRowSpacing)
        ) {
            RadioButton(
                selected = (index == value),
                onClick = {
                    if(value == index){
                        setValue(-1)
                    } else {
                        setValue(index)
                    }
                },
                enabled = enabled
            )
            Text(
                text = item.label.asDisplayString(),
            )
        }
    }
){
    Column(
        modifier = modifier.focusRequester(focusRequester),
        verticalArrangement = Arrangement.spacedBy(LocalFormStyle.current.fieldColumnSpacing)
    ) {
        hint?.let {
            Text(
                modifier = textModifier,
                text = it.asDisplayString(),
                style = MaterialTheme.typography.titleMedium
            )
        }

        options.forEachIndexed { index, item ->
            radioButton(item, index)
        }

        if (error != null) {
            Text(
                modifier = errorModifier,
                text = error.asDisplayString(),
                color = MaterialTheme.colorScheme.error
            )
        }
    }
}