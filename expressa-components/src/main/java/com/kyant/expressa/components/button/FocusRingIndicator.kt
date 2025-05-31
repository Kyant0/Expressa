package com.kyant.expressa.components.button

import androidx.compose.foundation.interaction.InteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.drawOutline
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp

@Composable
internal fun Modifier.focusRingIndicator(
    interactionSource: InteractionSource,
    shape: () -> Shape,
    color: Color,
    thickness: Dp,
    outlineOffset: Dp
): Modifier {
    val isFocused by interactionSource.collectIsFocusedAsState()

    return this.drawWithCache {
        val outlineOffsetPx = outlineOffset.toPx()
        val thicknessPx = thickness.toPx()
        val indicatorSize =
            Size(
                size.width + outlineOffsetPx * 2 + thicknessPx,
                size.height + outlineOffsetPx * 2 + thicknessPx
            )
        val density = Density(density)
        val indicatorStyle = Stroke(width = thicknessPx)

        if (isFocused) {
            onDrawBehind {
                translate(
                    -outlineOffsetPx - thicknessPx / 2,
                    -outlineOffsetPx - thicknessPx / 2
                ) {
                    drawOutline(
                        outline =
                            shape().createOutline(
                                size = indicatorSize,
                                layoutDirection = layoutDirection,
                                density = density
                            ),
                        color = color,
                        style = indicatorStyle
                    )
                }
            }
        } else {
            onDrawBehind {}
        }
    }
}
