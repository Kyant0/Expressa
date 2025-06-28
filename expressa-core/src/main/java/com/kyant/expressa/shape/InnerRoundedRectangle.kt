package com.kyant.expressa.shape

import androidx.compose.foundation.shape.CornerSize
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp

@Stable
fun RoundedRectangle.inner(
    innerPadding: Dp
): RoundedRectangle {
    return InnerRoundedRectangle(this, innerPadding)
}

@Immutable
private data class InnerRoundedRectangle(
    val outerShape: RoundedRectangle,
    val innerPadding: Dp
) :
    RoundedRectangle(
        topStart = InnerCornerSize(outerShape.topStart, innerPadding),
        topEnd = InnerCornerSize(outerShape.topEnd, innerPadding),
        bottomEnd = InnerCornerSize(outerShape.bottomEnd, innerPadding),
        bottomStart = InnerCornerSize(outerShape.bottomStart, innerPadding),
        cornerSmoothing = outerShape.cornerSmoothing
    )

@Immutable
private data class InnerCornerSize(
    private val outerCornerSize: CornerSize,
    private val innerPadding: Dp
) : CornerSize {

    override fun toPx(shapeSize: Size, density: Density): Float {
        return outerCornerSize.toPx(shapeSize, density) + with(density) { innerPadding.toPx() }
    }
}
