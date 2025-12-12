package com.idscodelabs.compose_form.form.fields.core.file

import io.github.vinceglb.filekit.FileKit
import io.github.vinceglb.filekit.PlatformFile
import io.github.vinceglb.filekit.path

fun setupFilePicker(appId: String) {
    FileKit.init(appId)
}

internal actual fun PlatformFile.toStorageString(): String = path

internal actual fun String.toPlatformFile(): PlatformFile? = PlatformFile(this)
