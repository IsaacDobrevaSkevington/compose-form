package com.idscodelabs.compose_form.form.fields.default.radio

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.minimumInteractiveComponentSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.idscodelabs.compose_form.form.fields.core.base.DisplayableOption
import com.idscodelabs.compose_form.form.fields.core.base.ListDisplayable
import com.idscodelabs.compose_form.form.fields.core.radio.RadioFormBox
import com.idscodelabs.compose_form.form.model.LocalFormBox
import com.idscodelabs.compose_form.styles.LocalFormStyle

@Composable
fun <Item : ListDisplayable> DefaultRadioButton(
    item: DisplayableOption<Item>,
    selected: Boolean,
    onClick: () -> Unit,
) {
    val enabled = LocalFormBox.current.enabled
    Row(
        Modifier
            .fillMaxWidth()
            .selectable(
                enabled = enabled,
                selected = selected,
                onClick = { onClick() },
            ).minimumInteractiveComponentSize(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(LocalFormStyle.current.fieldRowSpacing),
    ) {
        RadioButton(
            selected = selected,
            onClick = onClick,
            enabled = enabled,
        )
        Text(
            text = item.label,
        )
    }
}
