package com.kyant.expressa.components.button

import androidx.compose.animation.core.FiniteAnimationSpec
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.Stable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.kyant.expressa.prelude.*
import com.kyant.expressa.shape.OmniShape

@Immutable
data class ButtonSizes(
    val containerHeight: Dp,
    val outlineWidth: Dp,
    val labelTextStyle: TextStyle,
    val iconSize: Dp,
    val shapeRound: OmniShape,
    val shapeSquare: OmniShape,
    val leadingSpace: Dp,
    val betweenIconLabelSpace: Dp,
    val trailingSpace: Dp,
    val shapePressed: OmniShape,
    val shapeAnimationSpec: FiniteAnimationSpec<Float>
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

        @Composable
        @ReadOnlyComposable
        fun extraSmall(): ButtonSizes = ButtonSizes(
            containerHeight = 32.dp,
            outlineWidth = 1.dp,
            labelTextStyle = titleSmall,
            iconSize = 16.dp,
            shapeRound = cornerShapeFull,
            shapeSquare = cornerShapeMedium,
            leadingSpace = 12.dp,
            betweenIconLabelSpace = 8.dp,
            trailingSpace = 12.dp,
            shapePressed = cornerShapeSmall,
            shapeAnimationSpec = motionSchemeFastSpatial()
        )

        @Composable
        @ReadOnlyComposable
        fun small(): ButtonSizes = ButtonSizes(
            containerHeight = 40.dp,
            outlineWidth = 1.dp,
            labelTextStyle = titleSmall,
            iconSize = 20.dp,
            shapeRound = cornerShapeFull,
            shapeSquare = cornerShapeMedium,
            leadingSpace = 12.dp,
            betweenIconLabelSpace = 8.dp,
            trailingSpace = 12.dp,
            shapePressed = cornerShapeSmall,
            shapeAnimationSpec = motionSchemeFastSpatial()
        )

        @Composable
        @ReadOnlyComposable
        fun medium(): ButtonSizes = ButtonSizes(
            containerHeight = 56.dp,
            outlineWidth = 1.dp,
            labelTextStyle = titleMedium,
            iconSize = 24.dp,
            shapeRound = cornerShapeFull,
            shapeSquare = cornerShapeLarge,
            leadingSpace = 24.dp,
            betweenIconLabelSpace = 8.dp,
            trailingSpace = 24.dp,
            shapePressed = cornerShapeMedium,
            shapeAnimationSpec = motionSchemeFastSpatial()
        )

        @Composable
        @ReadOnlyComposable
        fun large(): ButtonSizes = ButtonSizes(
            containerHeight = 96.dp,
            outlineWidth = 2.dp,
            labelTextStyle = headlineSmall,
            iconSize = 32.dp,
            shapeRound = cornerShapeFull,
            shapeSquare = cornerShapeExtraLarge,
            leadingSpace = 48.dp,
            betweenIconLabelSpace = 12.dp,
            trailingSpace = 48.dp,
            shapePressed = cornerShapeLarge,
            shapeAnimationSpec = motionSchemeFastSpatial()
        )

        @Composable
        @ReadOnlyComposable
        fun extraLarge(): ButtonSizes = ButtonSizes(
            containerHeight = 136.dp,
            outlineWidth = 3.dp,
            labelTextStyle = headlineLarge,
            iconSize = 40.dp,
            shapeRound = cornerShapeFull,
            shapeSquare = cornerShapeExtraLarge,
            leadingSpace = 64.dp,
            betweenIconLabelSpace = 16.dp,
            trailingSpace = 64.dp,
            shapePressed = cornerShapeLarge,
            shapeAnimationSpec = motionSchemeFastSpatial()
        )
    }
}
