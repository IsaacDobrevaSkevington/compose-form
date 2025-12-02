package com.idscodelabs.compose_form.form.fields.default.switch

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.text.style.TextAlign
import com.idscodelabs.compose_form.form.fields.strings.asDisplayString
import com.idscodelabs.compose_form.form.model.FormBox
import com.idscodelabs.compose_form.styles.LocalFormStyle

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormBox<*, Boolean>.DefaultFormSwitchEntry(
    hint: Any? = null,
    leftLabel: Any? = null,
    rightLabel: Any? = null,
    modifier: Modifier = Modifier.fillMaxWidth(),
    switchModifier: Modifier = Modifier.minimumInteractiveComponentSize(),
    hintModifier: Modifier = Modifier.fillMaxWidth(),
    leftLabelModifier: Modifier = Modifier.minimumInteractiveComponentSize(),
    rightLabelModifier: Modifier = Modifier.minimumInteractiveComponentSize(),
    errorModifier: Modifier = Modifier.fillMaxWidth(),
) {
    Column(
        modifier.primaryFocusable(),
        verticalArrangement = Arrangement.spacedBy(LocalFormStyle.current.fieldColumnSpacing),
    ) {
        hint?.let {
            Text(
                modifier = hintModifier,
                text = it.asDisplayString(),
            )
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(LocalFormStyle.current.fieldRowSpacing),
        ) {
            leftLabel?.let {
                Text(
                    text = it.asDisplayString(),
                    textAlign = TextAlign.Right,
                    modifier =
                        leftLabelModifier.selectable(selected = !value, enabled = enabled) {
                            setValue(false)
                        },
                )
            }

            Switch(
                modifier = switchModifier,
                checked = value,
                onCheckedChange = {
                    setValue(it)
                },
                enabled = enabled,
            )

            rightLabel?.let {
                Text(
                    text = it.asDisplayString(),
                    textAlign = TextAlign.Left,
                    modifier =
                        rightLabelModifier.selectable(selected = value, enabled = enabled) {
                            setValue(true)
                        },
                )
            }
        }

        error?.let {
            Text(
                modifier = errorModifier,
                text = it.asDisplayString(),
                color = MaterialTheme.colorScheme.error,
            )
        }
    }
}
