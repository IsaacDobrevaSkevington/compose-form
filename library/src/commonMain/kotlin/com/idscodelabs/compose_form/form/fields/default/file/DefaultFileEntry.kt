package com.idscodelabs.compose_form.form.fields.default.file

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.idscodelabs.compose_form.form.fields.strings.asDisplayString
import com.idscodelabs.compose_form.form.icons.Icons
import com.idscodelabs.compose_form.form.model.FormBox
import com.idscodelabs.compose_form.form.model.LocalFormBox
import com.idscodelabs.compose_form.form.model.add
import com.idscodelabs.compose_form.form.model.remove
import com.idscodelabs.compose_form.form.model.setValue
import com.idscodelabs.compose_form.styles.LocalFormStyle
import com.idscodelabs.compose_form.utils.StandardErrorDisplay
import com.idscodelabs.compose_form.utils.dashedBorder
import io.github.vinceglb.filekit.PlatformFile
import io.github.vinceglb.filekit.dialogs.compose.PickerResultLauncher
import io.github.vinceglb.filekit.dialogs.compose.rememberFilePickerLauncher
import io.github.vinceglb.filekit.name

@Composable
fun FormBox<*, List<PlatformFile>>.DefaultFileEntry(
    hint: Any? = null,
    instruction: Any? = null,
    editable: Boolean = true,
    multiple: Boolean = false,
    heroIcon: @Composable (() -> Unit)? = {
        DefaultFileEntryHeroIcon()
    },
    filePickerLauncher: @Composable (onAddFile: (PlatformFile) -> Unit) -> PickerResultLauncher = { onAddFile ->
        rememberFilePickerLauncher {
            it?.let {
                onAddFile(it)
            }
        }
    },
    button: @Composable (onClick: () -> Unit) -> Unit = {
        OutlinedButton(onClick = it, enabled = LocalFormBox.current.enabled) {
            Text("Browse")
        }
    },
    fileView: @Composable (file: PlatformFile, onClickFile: (file: PlatformFile) -> Unit) -> Unit = { file, onClickFile ->
        InputChip(
            onClick = { if (editable) onClickFile(file) },
            selected = true,
            enabled = editable,
            trailingIcon = { Icon(Icons.Close, contentDescription = "Clear selection") },
            label = { Text(file.name) },
        )
    },
    modifier: Modifier = Modifier.fillMaxWidth(),
    errorDisplay: @Composable (error: String) -> Unit = {
        StandardErrorDisplay(it)
    },
) {
    val filePickerLauncherInstance =
        filePickerLauncher {
            if (multiple) {
                add(it)
            } else {
                setValue(it)
            }
        }

    Column(
        modifier = modifier.primaryFocusable().alpha(if (enabled) 1f else 0.7f),
        verticalArrangement = Arrangement.spacedBy(LocalFormStyle.current.fieldColumnSpacing),
    ) {
        val borderColor =
            if (error == null) {
                MaterialTheme.colorScheme.primary.copy(alpha = 0.5f)
            } else {
                MaterialTheme.colorScheme.error
            }
        Column(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .dashedBorder(
                        strokeWidth = 1.dp,
                        color = borderColor,
                        cornerRadiusDp = 10.dp,
                    ).background(MaterialTheme.colorScheme.primary.copy(alpha = 0.1f))
                    .padding(LocalFormStyle.current.fieldColumnSpacing),
            verticalArrangement = Arrangement.spacedBy(LocalFormStyle.current.fieldColumnSpacing),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            heroIcon?.invoke()
            Spacer(Modifier.height(LocalFormStyle.current.fieldColumnSpacing))
            hint?.asDisplayString()?.let {
                Text(
                    it,
                    style = MaterialTheme.typography.titleLarge,
                    textAlign = TextAlign.Center,
                )
            }
            instruction?.asDisplayString()?.let {
                Text(
                    it,
                    style = MaterialTheme.typography.bodyMedium,
                    textAlign = TextAlign.Center,
                )
            }

            if (editable || value.isEmpty()) {
                button(filePickerLauncherInstance::launch)
            }

            FlowRow(
                verticalArrangement = Arrangement.spacedBy(LocalFormStyle.current.fieldRowSpacing),
                horizontalArrangement = Arrangement.spacedBy(LocalFormStyle.current.fieldRowSpacing),
                itemVerticalAlignment = Alignment.CenterVertically,
            ) {
                value.forEach {
                    fileView(it, ::remove)
                }
            }
        }
        AnimatedContent(error) { animatedError ->
            animatedError?.let {
                errorDisplay(it)
            }
        }
    }
}
