package com.idscodelabs.compose_form.app

import androidx.compose.runtime.Composable

interface Example {
    val displayName: String
    val contents: @Composable () -> Unit
}
