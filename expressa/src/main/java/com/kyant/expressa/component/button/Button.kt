package com.kyant.expressa.component.button

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
import com.kyant.expressa.component.icon.LocalIconSize
import com.kyant.expressa.component.text.ProvideTextStyle
import com.kyant.expressa.interaction.animatedValueAsState
import com.kyant.expressa.interaction.statefulvalues.animatedValueAsState
import com.kyant.expressa.prelude.*
import com.kyant.expressa.ui.LocalContentColor
import com.kyant.expressa.ui.focusRingIndicator

@Composable
fun Button(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: () -> Boolean = { true },
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    buttonSizes: ButtonSizes = ButtonSizes.small(),
    buttonShape: ButtonShape = ButtonShape.Round,
    buttonColors: ButtonColors = ButtonColors.filled(),
    buttonDensity: ButtonDensity = ButtonDensity.Standard,
    icon: @Composable (() -> Unit)? = null,
    label: @Composable () -> Unit
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

    val containerColor = { buttonColors.containerColor.resolvedValue(stateHolder) }
    val containerElevation by buttonColors.elevation
        .animatedValueAsState(stateHolder, motionSchemeFastEffects())
    val containerShape by buttonSizes.resolvedShapes(buttonShape)
        .animatedValueAsState(stateHolder, buttonSizes.shapeAnimationSpec)
    val labelColor = buttonColors.labelColor.resolvedValue(stateHolder)
    val iconColor = buttonColors.iconColor.resolvedValue(stateHolder)
    val outlineColor = { buttonColors.outlineColor.resolvedValue(stateHolder) }

    val focusRingIndicatorColor = secondary
    val focusRingIndicatorThickness = 3.dp
    val focusRingOutlineOffset = 2.dp

    CompositionLocalProvider(
        LocalContentColor provides labelColor
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
                    shadowElevation = containerElevation.toPx()
                    ambientShadowColor = buttonColors.shadowColor
                    spotShadowColor = buttonColors.shadowColor
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
                        style = Stroke(width = buttonSizes.outlineWidth.toPx())
                    )
                }
                .clickable(
                    enabled = enabled(),
                    role = Role.Button,
                    interactionSource = interactionSource,
                    onClick = onClick
                )
                .padding(
                    start = buttonSizes.leadingSpace,
                    end = buttonSizes.trailingSpace
                )
                .height(buttonSizes.resolvedContainerHeight(buttonDensity)),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (icon != null) {
                CompositionLocalProvider(
                    LocalContentColor provides iconColor,
                    LocalIconSize provides buttonSizes.iconSize,
                    content = icon
                )
                Spacer(Modifier.width(buttonSizes.betweenIconLabelSpace))
            }

            ProvideTextStyle(
                buttonSizes.labelTextStyle,
                content = label
            )
        }
    }
}
