package com.idscodelabs.compose_form.form.fields.default.file

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.material3.CardDefaults.outlinedCardColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.idscodelabs.compose_form.form.fields.strings.asDisplayString
import com.idscodelabs.compose_form.form.icons.Icons
import com.idscodelabs.compose_form.form.model.FormBox
import com.idscodelabs.compose_form.form.model.remove
import com.idscodelabs.compose_form.form.model.setValue
import com.idscodelabs.compose_form.utils.StandardErrorDisplay
import io.github.vinceglb.filekit.PlatformFile
import io.github.vinceglb.filekit.dialogs.compose.PickerResultLauncher
import io.github.vinceglb.filekit.dialogs.compose.rememberFilePickerLauncher
import io.github.vinceglb.filekit.name

@Composable
fun FormBox<*, List<PlatformFile>>.DefaultFileEntry(
    hint: Any,
    editable: Boolean = true,
    filePickerLauncher: @Composable FormBox<*, List<PlatformFile>>.() -> PickerResultLauncher = {
        rememberFilePickerLauncher {
            it?.let {
                setValue(it)
            }
        }
    },
    button: @Composable FormBox<*, List<PlatformFile>>.(onClick: () -> Unit) -> Unit = {
        TextButton(onClick = it) {
            Text("Browse")
        }
    },
    fileRow: @Composable FormBox<*, List<PlatformFile>>.(file: PlatformFile) -> Unit = {
        InputChip(
            onClick = { if (editable) remove(it) },
            selected = true,
            enabled = editable,
            trailingIcon = { Icon(Icons.Close, contentDescription = "Clear selection") },
            label = { Text(it.name) },
        )
    },
    modifier: Modifier = Modifier.fillMaxWidth(),
    errorDisplay: @Composable FormBox<*, List<PlatformFile>>.(error: String) -> Unit = {
        StandardErrorDisplay(it)
    },
) {
    val filePickerLauncherInstance = filePickerLauncher()
    Column(modifier = modifier, verticalArrangement = Arrangement.spacedBy(8.dp)) {
        OutlinedCard(
            colors = outlinedCardColors().copy(containerColor = Color.Transparent),
        ) {
            Row(
                modifier = Modifier.height(IntrinsicSize.Min).padding(16.dp),
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Column(
                    Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterVertically),
                ) {
                    Text(text = hint.asDisplayString())

                    if (value.isNotEmpty()) {
                        Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                            value.forEach { fileRow(it) }
                        }
                    }
                }
                VerticalDivider()
                if (editable || value.isEmpty()) {
                    button { filePickerLauncherInstance.launch() }
                }
            }
        }
        error?.let {
            errorDisplay(it)
        }
    }
}
