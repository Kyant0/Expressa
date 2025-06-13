package com.kyant.expressa.shape

import androidx.compose.foundation.shape.CornerSize
import androidx.compose.runtime.Immutable
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp

@Immutable
internal data class InnerRoundedRectangle(
    val outerShape: RoundedRectangle,
    val innerPadding: Dp
) :
    RoundedRectangle(
        topStart = InnerCornerSize(outerShape.topStart, innerPadding),
        topEnd = InnerCornerSize(outerShape.topEnd, innerPadding),
        bottomEnd = InnerCornerSize(outerShape.bottomEnd, innerPadding),
        bottomStart = InnerCornerSize(outerShape.bottomStart, innerPadding),
        topStartSmoothing = outerShape.topStartSmoothing,
        topEndSmoothing = outerShape.topEndSmoothing,
        bottomEndSmoothing = outerShape.bottomEndSmoothing,
        bottomStartSmoothing = outerShape.bottomStartSmoothing
    ), InterpolableShape

@Immutable
private data class InnerCornerSize(
    private val outerCornerSize: CornerSize,
    private val innerPadding: Dp
) : CornerSize {

    override fun toPx(shapeSize: Size, density: Density): Float {
        return outerCornerSize.toPx(shapeSize, density) + with(density) { innerPadding.toPx() }
    }
}
