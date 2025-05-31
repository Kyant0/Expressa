package com.kyant.expressa.components.button

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
import com.kyant.expressa.components.interaction.animatedValueAsState
import com.kyant.expressa.components.interaction.statefulvalues.animatedValueAsState
import com.kyant.expressa.prelude.*
import com.kyant.expressa.ui.LocalContentColor
import com.kyant.expressa.ui.LocalIconSize
import com.kyant.expressa.ui.ProvideTextStyle

@Composable
fun Button(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: () -> Boolean = { true },
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    sizes: ButtonSizes = ButtonSizes.small(),
    shape: ButtonShape = ButtonShape.Round,
    colors: ButtonColors = ButtonColors.filled(),
    density: ButtonDensity = ButtonDensity.Standard,
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

    val containerColor = { colors.containerColor.resolvedValue(stateHolder) }
    val containerElevation by colors.elevation
        .animatedValueAsState(stateHolder, motionSchemeFastEffects())
    val containerShape by sizes.resolvedShapes(shape)
        .animatedValueAsState(stateHolder, sizes.shapeAnimationSpec)
    val labelColor = colors.labelColor.resolvedValue(stateHolder)
    val iconColor = colors.iconColor.resolvedValue(stateHolder)
    val outlineColor = { colors.outlineColor.resolvedValue(stateHolder) }

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
                    this.shape = containerShape
                    shadowElevation = containerElevation.toPx()
                    ambientShadowColor = colors.shadowColor
                    spotShadowColor = colors.shadowColor
                }
                .drawBehind {
                    drawRect(color = containerColor())
                    drawOutline(
                        outline = containerShape.createOutline(
                            size = size,
                            layoutDirection = layoutDirection,
                            density = Density(this.density)
                        ),
                        color = outlineColor(),
                        style = Stroke(width = sizes.outlineWidth.toPx())
                    )
                }
                .clickable(
                    enabled = enabled(),
                    role = Role.Button,
                    interactionSource = interactionSource,
                    onClick = onClick
                )
                .padding(
                    start = sizes.leadingSpace,
                    end = sizes.trailingSpace
                )
                .height(sizes.resolvedContainerHeight(density)),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (icon != null) {
                CompositionLocalProvider(
                    LocalContentColor provides iconColor,
                    LocalIconSize provides sizes.iconSize,
                    content = icon
                )
                Spacer(Modifier.width(sizes.betweenIconLabelSpace))
            }

            ProvideTextStyle(
                sizes.labelTextStyle,
                content = label
            )
        }
    }
}
