package com.idscodelabs.compose_form.utils

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun StandardErrorDisplay(error: String) =
    Text(
        modifier = Modifier.fillMaxWidth(),
        text = error,
        color = MaterialTheme.colorScheme.error,
    )
