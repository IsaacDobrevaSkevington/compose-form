package com.idscodelabs.compose_form.form.fields.core.file

import androidx.compose.runtime.Composable
import com.idscodelabs.compose_form.form.core.controller.FormController
import com.idscodelabs.compose_form.form.fields.core.base.FormFieldImplementation
import com.idscodelabs.compose_form.form.fields.core.base.FormFieldWrapper
import com.idscodelabs.compose_form.validators.core.Validator
import io.github.vinceglb.filekit.PlatformFile
import kotlin.reflect.KProperty

@Composable
fun <Model> FormController<Model>.FormFileField(
    modelProperty: KProperty<*>,
    updateModel: Model.(List<PlatformFile>) -> Unit,
    initialValue: List<PlatformFile> = emptyList(),
    validator: Validator<List<PlatformFile>>? = null,
    enabled: Boolean = true,
    separator: String = "\u0000",
    implementation: FormFieldImplementation<List<PlatformFile>>,
) {
    FormFieldWrapper(
        modelProperty = modelProperty,
        initialValue = initialValue,
        enabled = enabled,
        validator = validator,
        updateModel = { updateModel(it ?: emptyList()) },
        implementation = implementation,
        valueToString = {
            it?.joinToString(separator, transform = PlatformFile::toStorageString)
        },
        stringToValue = { it?.ifBlank { null }?.split(separator)?.mapNotNull(String::toPlatformFile) ?: emptyList() },
        { this },
    )
}

internal expect fun PlatformFile.toStorageString(): String

internal expect fun String.toPlatformFile(): PlatformFile?
