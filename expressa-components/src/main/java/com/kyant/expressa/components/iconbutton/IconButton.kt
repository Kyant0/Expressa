package com.kyant.expressa.components.iconbutton

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.drawOutline
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.dp
import com.kyant.expressa.components.button.ButtonDensity
import com.kyant.expressa.components.button.ButtonShape
import com.kyant.expressa.components.button.ButtonStateHolder
import com.kyant.expressa.interaction.statefulvalues.animatedValueAsState
import com.kyant.expressa.prelude.*
import com.kyant.expressa.ui.LocalContentColor
import com.kyant.expressa.ui.LocalIconSize
import com.kyant.expressa.ui.focusRingIndicator

@Composable
fun IconButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: () -> Boolean = { true },
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    iconButtonSizes: IconButtonSizes = IconButtonSizes.small(),
    buttonShape: ButtonShape = ButtonShape.Round,
    iconButtonColors: IconButtonColors = IconButtonColors.filled(),
    iconButtonWidth: IconButtonWidth = IconButtonWidth.Default,
    buttonDensity: ButtonDensity = ButtonDensity.Standard,
    content: @Composable () -> Unit
) {
    val disabledState = remember(enabled) {
        derivedStateOf {
            !enabled()
        }
    }
    val stateHolder = remember(interactionSource, disabledState) {
        ButtonStateHolder(
            interactionSource = interactionSource,
            disabled = disabledState
        )
    }

    val containerColor = { iconButtonColors.containerColor.resolvedValue(stateHolder) }
    val containerShape by iconButtonSizes.resolvedShapes(buttonShape)
        .animatedValueAsState(stateHolder, iconButtonSizes.shapeAnimationSpec)
    val iconColor = iconButtonColors.iconColor.resolvedValue(stateHolder)
    val outlineColor = { iconButtonColors.outlineColor.resolvedValue(stateHolder) }

    val focusRingIndicatorColor = secondary
    val focusRingIndicatorThickness = 3.dp
    val focusRingOutlineOffset = 2.dp

    CompositionLocalProvider(
        LocalContentColor provides iconColor,
        LocalIconSize provides iconButtonSizes.iconSize
    ) {
        Row(
            modifier
                .focusRingIndicator(
                    interactionSource = interactionSource,
                    shape = { containerShape },
                    color = focusRingIndicatorColor,
                    thickness = focusRingIndicatorThickness,
                    outlineOffset = focusRingOutlineOffset
                )
                .graphicsLayer {
                    clip = true
                    shape = containerShape
                }
                .drawBehind {
                    drawRect(color = containerColor())
                    drawOutline(
                        outline = containerShape.createOutline(
                            size = size,
                            layoutDirection = layoutDirection,
                            density = Density(density)
                        ),
                        color = outlineColor(),
                        style = Stroke(width = iconButtonSizes.outlineWidth.toPx())
                    )
                }
                .clickable(
                    enabled = enabled(),
                    role = Role.Button,
                    interactionSource = interactionSource,
                    onClick = onClick
                )
                .padding(
                    start = iconButtonSizes.resolvedLeadingSpace(iconButtonWidth),
                    end = iconButtonSizes.resolvedTrailingSpace(iconButtonWidth)
                )
                .height(iconButtonSizes.resolvedContainerHeight(buttonDensity)),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            content()
        }
    }
}
