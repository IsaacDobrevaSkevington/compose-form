package com.idscodelabs.compose_form.form.fields.core.file

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.InputChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.idscodelabs.compose_form.form.core.controller.FormController
import com.idscodelabs.compose_form.form.fields.default.file.DefaultFileEntryHeroIcon
import com.idscodelabs.compose_form.form.icons.Icons
import com.idscodelabs.compose_form.form.model.FormBox
import com.idscodelabs.compose_form.form.model.LocalFormBox
import com.idscodelabs.compose_form.form.model.remove
import com.idscodelabs.compose_form.form.model.setValue
import com.idscodelabs.compose_form.utils.StandardErrorDisplay
import com.idscodelabs.compose_form.utils.hint
import com.idscodelabs.compose_form.utils.updateModel
import com.idscodelabs.compose_form.utils.validator
import com.idscodelabs.compose_form.validators.core.Validator
import io.github.vinceglb.filekit.PlatformFile
import io.github.vinceglb.filekit.dialogs.compose.PickerResultLauncher
import io.github.vinceglb.filekit.dialogs.compose.rememberFilePickerLauncher
import io.github.vinceglb.filekit.name
import kotlin.reflect.KMutableProperty

@Composable
fun <Model> FormController<Model>.FormFileField(
    modelProperty: KMutableProperty<List<PlatformFile>>,
    hint: Any? = modelProperty.hint(),
    instruction: Any? = null,
    initialValue: List<PlatformFile> = emptyList(),
    validator: Validator<List<PlatformFile>>? = modelProperty.validator(),
    enabled: Boolean = true,
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
        TextButton(onClick = it, enabled = LocalFormBox.current.enabled) {
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
    separator: String = "\u0000",
) = FormFileField(
    modelProperty = modelProperty,
    updateModel = modelProperty.updateModel(),
    instruction = instruction,
    multiple = multiple,
    heroIcon = heroIcon,
    initialValue = initialValue,
    validator = validator,
    enabled = enabled,
    separator = separator,
    hint = hint,
    editable = editable,
    filePickerLauncher = filePickerLauncher,
    button = button,
    fileView = fileView,
    modifier = modifier,
    errorDisplay = errorDisplay,
)
