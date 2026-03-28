package com.idscodelabs.compose_form.utils

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp

@Composable
internal fun Modifier.dashedBorder(
    strokeWidth: Dp,
    color: Color,
    cornerRadiusDp: Dp,
) = composed(factory = {
    val density = LocalDensity.current
    val strokeWidthPx = density.run { strokeWidth.toPx() }
    val cornerRadiusPx = density.run { cornerRadiusDp.toPx() }

    this.then(
        Modifier.drawWithCache {
            onDrawBehind {
                val stroke =
                    Stroke(
                        width = strokeWidthPx,
                        pathEffect = PathEffect.dashPathEffect(floatArrayOf(20f, 10f), 0f),
                    )

                drawRoundRect(
                    color = color,
                    style = stroke,
                    cornerRadius = CornerRadius(cornerRadiusPx),
                )
            }
        },
    )
}).clip(
    RoundedCornerShape(cornerRadiusDp),
)
