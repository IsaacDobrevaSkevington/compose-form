package com.idscodelabs.compose_form.form.fields.default.dropdown

import androidx.compose.foundation.layout.requiredSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.layout.LayoutCoordinates
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInWindow
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.unit.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlin.math.max
import kotlin.math.roundToInt

class LazyDropdownScope {

    val size: MutableStateFlow<IntSize> = MutableStateFlow(IntSize(0, 0))
}

@Composable
fun Modifier.lazyDropdown(scope: LazyDropdownScope): Modifier {
    val window = LocalWindowInfo.current.containerSize

    val verticalMargin = with(LocalDensity.current) { 48.dp.roundToPx() }

    return onGloballyPositioned { layoutCoordinates ->
        scope.size.update {
            IntSize(
                width = layoutCoordinates.size.width,
                height = calculateMaxHeight(
                    windowBounds = IntRect(IntOffset.Zero, IntSize(window.width, window.height)),
                    anchorBounds = layoutCoordinates.getAnchorBounds(),
                    verticalMargin = verticalMargin,
                )
            )
        }
    }
}

@Composable
fun Modifier.lazyDropdownMenu(scope: LazyDropdownScope): Modifier {
    val statefulSize by scope.size.collectAsState()
    return with(LocalDensity.current) {
        val heightDp by derivedStateOf {
            statefulSize.height.toDp() - 64.dp
        }
        val widthDp by derivedStateOf {
            statefulSize.width.toDp()
        }
        requiredSize(width = widthDp, height = heightDp)
    }
}

@Composable
fun rememberLazyDropdownScope() = remember { LazyDropdownScope() }

private fun calculateMaxHeight(
    windowBounds: IntRect,
    anchorBounds: Rect?,
    verticalMargin: Int,
): Int {
    anchorBounds ?: return 0

    val marginedWindowTop = windowBounds.top + verticalMargin
    val marginedWindowBottom = windowBounds.bottom - verticalMargin
    val availableHeight =
        if (anchorBounds.top > windowBounds.bottom || anchorBounds.bottom < windowBounds.top) {
            marginedWindowBottom - marginedWindowTop
        } else {
            val heightAbove = anchorBounds.top - marginedWindowTop
            val heightBelow = marginedWindowBottom - anchorBounds.bottom
            max(heightAbove, heightBelow).roundToInt()
        }

    return max(availableHeight, 0)
}

private fun LayoutCoordinates?.getAnchorBounds(): Rect =
    if (this == null || !this.isAttached) Rect.Zero else Rect(positionInWindow(), size.toSize())

