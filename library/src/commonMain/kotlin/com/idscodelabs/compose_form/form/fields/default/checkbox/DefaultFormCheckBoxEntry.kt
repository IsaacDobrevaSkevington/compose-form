package com.idscodelabs.compose_form.form.fields.default.checkbox

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.idscodelabs.compose_form.form.fields.strings.asDisplayString
import com.idscodelabs.compose_form.form.model.FormBox
import com.idscodelabs.compose_form.styles.LocalFormStyle
import com.idscodelabs.compose_form.utils.StandardErrorDisplay
import io.github.vinceglb.filekit.PlatformFile

/**
 * Default form check box entry
 *
 * @param hint The hint to display next to the checkbox
 * @param modifier The [Modifier] for the whole container
 * @param checkboxModifier The [Modifier] applied to the checkbox
 * @param textModifier The [Modifier] applied to the text
 * @param errorDisplay The way the error should be displayed. Error is received as as parameter.
 * This composable will only be displayed when the FormBox error is not null
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormBox<*, Boolean>.DefaultFormCheckBoxEntry(
    hint: Any? = null,
    modifier: Modifier = Modifier.fillMaxWidth(),
    checkboxModifier: Modifier = Modifier.minimumInteractiveComponentSize(),
    textModifier: Modifier = Modifier.minimumInteractiveComponentSize(),
    errorDisplay: @Composable FormBox<*, Boolean>.(error: String) -> Unit = {
        StandardErrorDisplay(it)
    },
) {
    Column(
        modifier,
        verticalArrangement = Arrangement.spacedBy(LocalFormStyle.current.fieldColumnSpacing),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(LocalFormStyle.current.fieldRowSpacing),
        ) {
            Checkbox(
                checked = value,
                onCheckedChange = ::setValue,
                modifier = checkboxModifier.primaryFocusable(),
                enabled = enabled,
            )
            hint?.let {
                Text(
                    text = it.asDisplayString(),
                    modifier =
                        textModifier.clickable(
                            enabled = enabled,
                            onClick = { setValue(!valueSnapshot) },
                        ),
                )
            }
        }

        error?.let {
            errorDisplay(it)
        }
    }
}
