package com.idscodelabs.compose_form.form.fields.core.file

import androidx.compose.runtime.Composable
import com.idscodelabs.compose_form.form.core.controller.FormController
import com.idscodelabs.compose_form.form.fields.core.base.FormFieldImplementation
import com.idscodelabs.compose_form.utils.updateModel
import com.idscodelabs.compose_form.utils.validator
import com.idscodelabs.compose_form.validators.core.Validator
import io.github.vinceglb.filekit.PlatformFile
import kotlin.reflect.KMutableProperty

@Composable
fun <Model> FormController<Model>.FormFileField(
    modelProperty: KMutableProperty<List<PlatformFile>>,
    initialValue: List<PlatformFile> = emptyList(),
    validator: Validator<List<PlatformFile>>? = modelProperty.validator(),
    enabled: Boolean = true,
    separator: String = "\u0000",
    implementation: FormFieldImplementation<List<PlatformFile>>,
) = FormFileField(
    modelProperty = modelProperty,
    updateModel = modelProperty.updateModel(),
    initialValue = initialValue,
    validator = validator,
    enabled = enabled,
    separator = separator,
    implementation = implementation,
)
