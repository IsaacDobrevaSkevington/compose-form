package com.idscodelabs.compose_form.form.fields.core.file

import io.github.vinceglb.filekit.PlatformFile
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

internal object PlatformFileStore {
    val storageMap = HashMap<String, PlatformFile>()
}

@OptIn(ExperimentalUuidApi::class)
internal actual fun PlatformFile.toStorageString(): String {
    val uuid = Uuid.random().toString()
    PlatformFileStore.storageMap[uuid] = this
    return uuid
}

internal actual fun String.toPlatformFile(): PlatformFile? = PlatformFileStore.storageMap[this]
