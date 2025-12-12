package com.idscodelabs.compose_form.examples.fields.file

import com.idscodelabs.compose_form.examples.helpers.ExampleModel
import io.github.vinceglb.filekit.PlatformFile

data class FormFileFieldExampleModel(
    override var value: List<PlatformFile>? = null,
) : ExampleModel<List<PlatformFile>?>
