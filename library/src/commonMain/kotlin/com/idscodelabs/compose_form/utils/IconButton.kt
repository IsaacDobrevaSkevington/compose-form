package com.idscodelabs.compose_form.utils

import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector

@Composable
fun IconButton(
    icon: ImageVector,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    iconModifier: Modifier = Modifier,
    onClick: () -> Unit,
) = IconButton(modifier = modifier, onClick = onClick) {
    Icon(icon, contentDescription, modifier = iconModifier)
}
