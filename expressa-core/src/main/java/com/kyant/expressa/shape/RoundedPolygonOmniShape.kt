package com.kyant.expressa.shape

import androidx.compose.runtime.Immutable
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import androidx.graphics.shapes.RoundedPolygon
import androidx.graphics.shapes.TransformResult

@Immutable
data class RoundedPolygonOmniShape(
    private val roundedPolygon: RoundedPolygon
) : OmniShape {

    private val path = Path()

    internal val normalizedPolygon = roundedPolygon.normalized()

    override fun toRoundedPolygon(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Float
    ): RoundedPolygon {
        return normalizedPolygon.transformed { x, y ->
            TransformResult(x * size.width, y * size.height)
        }
    }

    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        return Outline.Generic(
            this.toPath(
                size = size,
                layoutDirection = layoutDirection,
                density = density.density,
                path = path
            )
        )
    }
}
