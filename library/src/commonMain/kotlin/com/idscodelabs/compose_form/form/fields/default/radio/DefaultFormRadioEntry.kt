package com.idscodelabs.compose_form.form.fields.default.radio

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.minimumInteractiveComponentSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.idscodelabs.compose_form.form.fields.core.base.DisplayableOption
import com.idscodelabs.compose_form.form.fields.core.base.ListDisplayable
import com.idscodelabs.compose_form.form.fields.core.radio.RadioFormBox
import com.idscodelabs.compose_form.form.fields.strings.asDisplayString
import com.idscodelabs.compose_form.form.model.FormBox
import com.idscodelabs.compose_form.styles.LocalFormStyle
import com.idscodelabs.compose_form.utils.StandardErrorDisplay

/**
 * Default form radio group entry
 *
 * @param Item The type of [ListDisplayable] to be displayed
 * @param hint Hint for the radio group
 * @param modifier Modifier for the whole radio group
 * @param textModifier Modifier for the hint text
 * @param radioButton Composable display of the radio button
 * @param errorDisplay Composable display of the error
 */
@Composable
fun <Item : ListDisplayable> RadioFormBox<*, Item>.DefaultFormRadioEntry(
    hint: Any?,
    modifier: Modifier = Modifier,
    textModifier: Modifier = Modifier,
    radioButton: @Composable RadioFormBox<*, Item>.(DisplayableOption<Item>, Int) -> Unit = { item, index ->
        DefaultRadioButton(item, index)
    },
    errorDisplay: @Composable RadioFormBox<*, Item>.(error: String) -> Unit = {
        StandardErrorDisplay(it)
    },
) {
    Column(
        modifier = modifier.primaryFocusable(),
        verticalArrangement = Arrangement.spacedBy(LocalFormStyle.current.fieldColumnSpacing),
    ) {
        hint?.let {
            Text(
                modifier = textModifier,
                text = it.asDisplayString(),
                style = MaterialTheme.typography.titleMedium,
            )
        }

        options.forEachIndexed { index, item ->
            radioButton(item, index)
        }

        error?.let {
            errorDisplay(it)
        }
    }
}
