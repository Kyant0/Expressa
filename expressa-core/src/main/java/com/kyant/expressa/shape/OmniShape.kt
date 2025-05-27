package com.kyant.expressa.shape

import androidx.compose.runtime.Immutable
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.geometry.center
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.unit.LayoutDirection
import androidx.graphics.shapes.RoundedPolygon

@Immutable
sealed interface OmniShape : Shape {

    val startAngle: Int

    // TODO: remove transformOrigin?
    val transformOrigin: TransformOrigin

    fun toRoundedPolygon(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Float
    ): RoundedPolygon

    fun toPath(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Float,
        path: Path = Path()
    ): Path {
        return this
            .toRoundedPolygon(size, layoutDirection, density)
            .toPath(
                path = path,
                startAngle = startAngle,
                rotationPivotX = size.center.x,
                rotationPivotY = size.center.y
            )
    }
}
