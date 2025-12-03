package com.idscodelabs.compose_form.form.fields.strings

import androidx.compose.runtime.Composable
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource

/**
 * Convert an object to a display string
 *
 * Use this in the UI, when items may not always be of the same displayable type.
 *
 * The following logic is followed
 *
 * - [String] -> [String]
 * - [StringResource] -> Apply [org.jetbrains.compose.resources.stringResource]
 * - [StringResourceWithPlaceholders] -> Apply [org.jetbrains.compose.resources.stringResource]
 * - [Pair] -> Apply [org.jetbrains.compose.resources.stringResource] if [Pair.first] is [StringResource] and [Pair.second] is [Array] of [Any]
 * - Otherwise apply [Any.toString]
 * @return The string representation of the object
 */
@Composable
fun Any.asDisplayString(): String =
    when (this) {
        is String -> {
            this
        }

        is StringResource -> {
            stringResource(this)
        }

        is StringResourceWithPlaceholders -> {
            stringResource(stringResource, *placeholders)
        }

        is Pair<*, *> if first is StringResource && second is Array<*> -> {
            stringResource(
                first as StringResource,
                *(second as Array<Any?>).filterNotNull().toTypedArray(),
            )
        }

        else -> {
            this.toString()
        }
    }
