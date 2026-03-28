package com.idscodelabs.compose_form.examples.fields.custom.entry

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.material3.minimumInteractiveComponentSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.idscodelabs.compose_form.form.fields.core.base.ListDisplayable
import com.idscodelabs.compose_form.form.fields.core.radio.RadioFormBox
import com.idscodelabs.compose_form.form.fields.default.radio.DefaultFormRadioEntry
import com.idscodelabs.compose_form.styles.LocalFormStyle

/**
 * An implementation of the form radio field using checkboxes instead of radio buttons
 */
@Composable
fun <Model, Item : ListDisplayable> RadioFormBox<Model, Item>.CustomFormFieldRadioEntry(hint: Any?) =
    DefaultFormRadioEntry(
        hint = hint,
        radioButton = { item, selected, onClick ->
            Row(
                Modifier
                    .fillMaxWidth()
                    .selectable(
                        enabled = enabled,
                        selected = selected,
                        onClick = onClick,
                    ).minimumInteractiveComponentSize(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(LocalFormStyle.current.fieldRowSpacing),
            ) {
                Checkbox(
                    checked = selected,
                    onCheckedChange = { onClick() },
                    enabled = enabled,
                )
                Text(
                    text = item.label,
                )
            }
        },
    )
