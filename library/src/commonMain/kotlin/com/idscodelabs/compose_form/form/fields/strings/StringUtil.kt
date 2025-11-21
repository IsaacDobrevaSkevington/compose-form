package com.idscodelabs.compose_form.form.fields.strings

import androidx.compose.runtime.Composable
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource

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
            stringResource(stringResource, *placeholders.toTypedArray())
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
