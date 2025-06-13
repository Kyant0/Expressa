package com.kyant.expressa.components.button

import androidx.compose.animation.core.SpringSpec
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.kyant.expressa.m3.motion.MotionScheme
import com.kyant.expressa.m3.shape.CornerShape
import com.kyant.expressa.m3.typography.Typography
import com.kyant.expressa.shape.InterpolableShape

@Immutable
data class ButtonSizes(
    val containerHeight: Dp,
    val outlineWidth: Dp,
    val labelTextStyle: TextStyle,
    val iconSize: Dp,
    val shapeRound: InterpolableShape,
    val shapeSquare: InterpolableShape,
    val leadingSpace: Dp,
    val betweenIconLabelSpace: Dp,
    val trailingSpace: Dp,
    val shapePressed: InterpolableShape,
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
    fun resolvedShapes(buttonShape: ButtonShape): ButtonContainerShape {
        return when (buttonShape) {
            ButtonShape.Round -> ButtonContainerShape(
                default = shapeRound,
                pressed = shapePressed
            )

            ButtonShape.Square -> ButtonContainerShape(
                default = shapeSquare,
                pressed = shapePressed
            )
        }
    }

    companion object {

        @Stable
        val extraSmall: ButtonSizes = ButtonSizes(
            containerHeight = 32.dp,
            outlineWidth = 1.dp,
            labelTextStyle = Typography.titleSmall,
            iconSize = 16.dp,
            shapeRound = CornerShape.full,
            shapeSquare = CornerShape.medium,
            leadingSpace = 12.dp,
            betweenIconLabelSpace = 8.dp,
            trailingSpace = 12.dp,
            shapePressed = CornerShape.small,
            shapeSpringSpec = MotionScheme.fastSpatial()
        )

        @Stable
        val small: ButtonSizes = ButtonSizes(
            containerHeight = 40.dp,
            outlineWidth = 1.dp,
            labelTextStyle = Typography.titleSmall,
            iconSize = 20.dp,
            shapeRound = CornerShape.full,
            shapeSquare = CornerShape.medium,
            leadingSpace = 12.dp,
            betweenIconLabelSpace = 8.dp,
            trailingSpace = 12.dp,
            shapePressed = CornerShape.small,
            shapeSpringSpec = MotionScheme.fastSpatial()
        )

        @Stable
        val medium: ButtonSizes = ButtonSizes(
            containerHeight = 56.dp,
            outlineWidth = 1.dp,
            labelTextStyle = Typography.titleMedium,
            iconSize = 24.dp,
            shapeRound = CornerShape.full,
            shapeSquare = CornerShape.large,
            leadingSpace = 24.dp,
            betweenIconLabelSpace = 8.dp,
            trailingSpace = 24.dp,
            shapePressed = CornerShape.medium,
            shapeSpringSpec = MotionScheme.fastSpatial()
        )

        @Stable
        val large: ButtonSizes = ButtonSizes(
            containerHeight = 96.dp,
            outlineWidth = 2.dp,
            labelTextStyle = Typography.headlineSmall,
            iconSize = 32.dp,
            shapeRound = CornerShape.full,
            shapeSquare = CornerShape.extraLarge,
            leadingSpace = 48.dp,
            betweenIconLabelSpace = 12.dp,
            trailingSpace = 48.dp,
            shapePressed = CornerShape.large,
            shapeSpringSpec = MotionScheme.fastSpatial()
        )

        @Stable
        val extraLarge: ButtonSizes = ButtonSizes(
            containerHeight = 136.dp,
            outlineWidth = 3.dp,
            labelTextStyle = Typography.headlineLarge,
            iconSize = 40.dp,
            shapeRound = CornerShape.full,
            shapeSquare = CornerShape.extraLarge,
            leadingSpace = 64.dp,
            betweenIconLabelSpace = 16.dp,
            trailingSpace = 64.dp,
            shapePressed = CornerShape.large,
            shapeSpringSpec = MotionScheme.fastSpatial()
        )
    }
}
