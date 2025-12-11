package com.idscodelabs.compose_form.form.core

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.vector.ImageVector

/**
 * Icon parameters for forms
 *
 * @property icon The icon vector
 * @property rotation The rotation of the icon
 * @property onClick Action to perform when the icon is clicked
 * @constructor Create empty Icon params
 */
class IconParams(
    val icon: ImageVector,
    val rotation: Float = 0f,
    val contentDescription: String? = null,
    val onClick: () -> Unit,
)

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

@Composable
fun IconButton(iconParams: IconParams) =
    with(iconParams) {
        IconButton(
            icon = icon,
            contentDescription = contentDescription,
            onClick = onClick,
            iconModifier = Modifier.rotate(rotation),
        )
    }

@Composable
fun IconButtonRow(iconParams: List<IconParams>) {
    Row {
        iconParams.forEach {
            IconButton(it)
        }
    }
}
