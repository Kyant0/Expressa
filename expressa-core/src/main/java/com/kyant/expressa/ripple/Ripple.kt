package com.kyant.expressa.ripple

import androidx.compose.foundation.IndicationNodeFactory
import androidx.compose.foundation.interaction.InteractionSource
import androidx.compose.material.ripple.RippleAlpha
import androidx.compose.material.ripple.createRippleModifierNode
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorProducer
import androidx.compose.ui.graphics.takeOrElse
import androidx.compose.ui.node.CompositionLocalConsumerModifierNode
import androidx.compose.ui.node.DelegatableNode
import androidx.compose.ui.node.DelegatingNode
import androidx.compose.ui.node.currentValueOf
import androidx.compose.ui.unit.Dp
import com.kyant.expressa.ui.LocalContentColor

@Stable
fun ripple(
    bounded: Boolean = true,
    radius: Dp = Dp.Unspecified,
    color: Color = Color.Unspecified,
    rippleAlpha: RippleAlpha = DefaultRippleAlpha
): IndicationNodeFactory {
    return if (radius == Dp.Unspecified && color == Color.Unspecified && rippleAlpha == DefaultRippleAlpha) {
        if (bounded) DefaultBoundedRipple else DefaultUnboundedRipple
    } else {
        RippleNodeFactory(
            bounded = bounded,
            radius = radius,
            color = color,
            rippleAlpha = { rippleAlpha }
        )
    }
}

@Stable
fun ripple(
    color: ColorProducer,
    bounded: Boolean = true,
    radius: Dp = Dp.Unspecified,
    rippleAlpha: RippleAlpha = DefaultRippleAlpha
): IndicationNodeFactory {
    return RippleNodeFactory(
        bounded = bounded,
        radius = radius,
        colorProducer = color,
        rippleAlpha = { rippleAlpha }
    )
}

@ConsistentCopyVisibility
@Immutable
private data class RippleNodeFactory private constructor(
    private val bounded: Boolean,
    private val radius: Dp,
    private val colorProducer: ColorProducer?,
    private val color: Color,
    private val rippleAlpha: () -> RippleAlpha
) : IndicationNodeFactory {

    constructor(
        bounded: Boolean,
        radius: Dp,
        colorProducer: ColorProducer,
        rippleAlpha: () -> RippleAlpha
    ) : this(bounded, radius, colorProducer, Color.Unspecified, rippleAlpha)

    constructor(
        bounded: Boolean,
        radius: Dp,
        color: Color,
        rippleAlpha: () -> RippleAlpha
    ) : this(bounded, radius, null, color, rippleAlpha)

    override fun create(interactionSource: InteractionSource): DelegatableNode {
        val colorProducer = colorProducer ?: ColorProducer { color }

        return DelegatingThemeAwareRippleNode(
            interactionSource = interactionSource,
            bounded = bounded,
            radius = radius,
            colorProducer = colorProducer,
            rippleAlpha = rippleAlpha
        )
    }
}

private class DelegatingThemeAwareRippleNode(
    interactionSource: InteractionSource,
    bounded: Boolean,
    radius: Dp,
    colorProducer: ColorProducer,
    rippleAlpha: () -> RippleAlpha
) : DelegatingNode(), CompositionLocalConsumerModifierNode {

    init {
        delegate(
            createRippleModifierNode(
                interactionSource = interactionSource,
                bounded = bounded,
                radius = radius,
                color = { colorProducer().takeOrElse { currentValueOf(LocalContentColor) } },
                rippleAlpha = rippleAlpha
            )
        )
    }
}

val DefaultRippleAlpha: RippleAlpha =
    RippleAlpha(
        hoveredAlpha = 0.08f,
        focusedAlpha = 0.10f,
        pressedAlpha = 0.10f,
        draggedAlpha = 0.16f
    )

private val DefaultBoundedRipple =
    RippleNodeFactory(
        bounded = true,
        radius = Dp.Unspecified,
        color = Color.Unspecified,
        rippleAlpha = { DefaultRippleAlpha }
    )

private val DefaultUnboundedRipple =
    RippleNodeFactory(
        bounded = false,
        radius = Dp.Unspecified,
        color = Color.Unspecified,
        rippleAlpha = { DefaultRippleAlpha }
    )
