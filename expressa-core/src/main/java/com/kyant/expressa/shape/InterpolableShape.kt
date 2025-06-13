package com.kyant.expressa.shape

import androidx.compose.runtime.Immutable
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.LayoutDirection
import androidx.graphics.shapes.RoundedPolygon

@Immutable
sealed interface InterpolableShape : Shape {

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
            .toPath(path)
    }
}
