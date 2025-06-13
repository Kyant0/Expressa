package com.kyant.expressa.shape

import androidx.compose.runtime.Immutable
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.geometry.toRect
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import androidx.graphics.shapes.RoundedPolygon
import androidx.graphics.shapes.rectangle

@Immutable
data object InterpolableRectangle : InterpolableShape {

    override fun toRoundedPolygon(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Float
    ): RoundedPolygon {
        return RoundedPolygon.rectangle(
            width = size.width,
            height = size.height,
            centerX = size.width / 2,
            centerY = size.height / 2
        )
    }

    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        return Outline.Rectangle(size.toRect())
    }
}
