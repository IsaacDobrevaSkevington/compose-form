package com.idscodelabs.compose_form.form.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType.Companion.NonZero
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap.Companion.Butt
import androidx.compose.ui.graphics.StrokeJoin.Companion.Miter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

internal val CloseIcon: ImageVector by lazy {

    ImageVector
        .Builder(
            name = "materialSymbolsOutlinedClose",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 960f,
            viewportHeight = 960f,
        ).apply {
            path(
                fill = SolidColor(Color(0xFF000000)),
                stroke = null,
                strokeLineWidth = 0.0f,
                strokeLineCap = Butt,
                strokeLineJoin = Miter,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero,
            ) {
                moveToRelative(256.0f, 760.0f)
                lineToRelative(-56.0f, -56.0f)
                lineToRelative(224.0f, -224.0f)
                lineToRelative(-224.0f, -224.0f)
                lineToRelative(56.0f, -56.0f)
                lineToRelative(224.0f, 224.0f)
                lineToRelative(224.0f, -224.0f)
                lineToRelative(56.0f, 56.0f)
                lineToRelative(-224.0f, 224.0f)
                lineToRelative(224.0f, 224.0f)
                lineToRelative(-56.0f, 56.0f)
                lineToRelative(-224.0f, -224.0f)
                lineToRelative(-224.0f, 224.0f)
                close()
            }
        }.build()
}
