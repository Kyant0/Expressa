package com.kyant.expressa.components.iconbutton

import androidx.compose.animation.core.SpringSpec
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.kyant.expressa.components.button.ButtonContainerShape
import com.kyant.expressa.components.button.ButtonDensity
import com.kyant.expressa.components.button.ButtonShape
import com.kyant.expressa.m3.motion.MotionScheme
import com.kyant.expressa.m3.shape.CornerShape

@Immutable
data class IconButtonSizes(
    val containerHeight: Dp,
    val iconSize: Dp,
    val narrowLeadingSpace: Dp,
    val narrowTrailingSpace: Dp,
    val defaultLeadingSpace: Dp,
    val defaultTrailingSpace: Dp,
    val wideLeadingSpace: Dp,
    val wideTrailingSpace: Dp,
    val containerShapeRound: Shape,
    val containerShapeSquare: Shape,
    val outlineWidth: Dp,
    val shapePressed: Shape,
    val shapeSpringSpec: SpringSpec<Float>
) {

    @Stable
    fun resolvedContainerHeight(buttonDensity: ButtonDensity): Dp {
        return when (buttonDensity) {
            ButtonDensity.Standard -> containerHeight
            ButtonDensity.Negative1 -> containerHeight - 4.dp
            ButtonDensity.Negative2 -> containerHeight - 8.dp
            ButtonDensity.Negative3 -> containerHeight - 12.dp
        }
    }

    @Stable
    fun resolvedLeadingSpace(iconButtonWidth: IconButtonWidth): Dp {
        return when (iconButtonWidth) {
            IconButtonWidth.Narrow -> narrowLeadingSpace
            IconButtonWidth.Default -> defaultLeadingSpace
            IconButtonWidth.Wide -> wideLeadingSpace
        }
    }

    @Stable
    fun resolvedTrailingSpace(iconButtonWidth: IconButtonWidth): Dp {
        return when (iconButtonWidth) {
            IconButtonWidth.Narrow -> narrowTrailingSpace
            IconButtonWidth.Default -> defaultTrailingSpace
            IconButtonWidth.Wide -> wideTrailingSpace
        }
    }

    @Stable
    fun resolvedShapes(buttonShape: ButtonShape): ButtonContainerShape {
        return when (buttonShape) {
            ButtonShape.Round -> ButtonContainerShape(
                default = containerShapeRound,
                pressed = shapePressed
            )

            ButtonShape.Square -> ButtonContainerShape(
                default = containerShapeSquare,
                pressed = shapePressed
            )
        }
    }

    companion object {

        @Stable
        val extraSmall: IconButtonSizes = IconButtonSizes(
            containerHeight = 32.dp,
            iconSize = 20.dp,
            narrowLeadingSpace = 4.dp,
            narrowTrailingSpace = 4.dp,
            defaultLeadingSpace = 6.dp,
            defaultTrailingSpace = 6.dp,
            wideLeadingSpace = 10.dp,
            wideTrailingSpace = 10.dp,
            containerShapeRound = CornerShape.full,
            containerShapeSquare = CornerShape.medium,
            outlineWidth = 1.dp,
            shapePressed = CornerShape.small,
            shapeSpringSpec = MotionScheme.fastSpatial()
        )

        @Stable
        val small: IconButtonSizes = IconButtonSizes(
            containerHeight = 40.dp,
            iconSize = 24.dp,
            narrowLeadingSpace = 4.dp,
            narrowTrailingSpace = 4.dp,
            defaultLeadingSpace = 8.dp,
            defaultTrailingSpace = 8.dp,
            wideLeadingSpace = 14.dp,
            wideTrailingSpace = 14.dp,
            containerShapeRound = CornerShape.full,
            containerShapeSquare = CornerShape.medium,
            outlineWidth = 1.dp,
            shapePressed = CornerShape.small,
            shapeSpringSpec = MotionScheme.fastSpatial()
        )

        @Stable
        val medium: IconButtonSizes = IconButtonSizes(
            containerHeight = 56.dp,
            iconSize = 24.dp,
            narrowLeadingSpace = 12.dp,
            narrowTrailingSpace = 12.dp,
            defaultLeadingSpace = 16.dp,
            defaultTrailingSpace = 16.dp,
            wideLeadingSpace = 24.dp,
            wideTrailingSpace = 24.dp,
            containerShapeRound = CornerShape.full,
            containerShapeSquare = CornerShape.large,
            outlineWidth = 1.dp,
            shapePressed = CornerShape.medium,
            shapeSpringSpec = MotionScheme.fastSpatial()
        )

        @Stable
        val large: IconButtonSizes = IconButtonSizes(
            containerHeight = 96.dp,
            iconSize = 32.dp,
            narrowLeadingSpace = 16.dp,
            narrowTrailingSpace = 16.dp,
            defaultLeadingSpace = 32.dp,
            defaultTrailingSpace = 32.dp,
            wideLeadingSpace = 48.dp,
            wideTrailingSpace = 48.dp,
            containerShapeRound = CornerShape.full,
            containerShapeSquare = CornerShape.extraLarge,
            outlineWidth = 2.dp,
            shapePressed = CornerShape.large,
            shapeSpringSpec = MotionScheme.fastSpatial()
        )

        @Stable
        val extraLarge: IconButtonSizes = IconButtonSizes(
            containerHeight = 136.dp,
            iconSize = 40.dp,
            narrowLeadingSpace = 32.dp,
            narrowTrailingSpace = 32.dp,
            defaultLeadingSpace = 48.dp,
            defaultTrailingSpace = 48.dp,
            wideLeadingSpace = 72.dp,
            wideTrailingSpace = 72.dp,
            containerShapeRound = CornerShape.full,
            containerShapeSquare = CornerShape.extraLarge,
            outlineWidth = 3.dp,
            shapePressed = CornerShape.large,
            shapeSpringSpec = MotionScheme.fastSpatial()
        )
    }
}
