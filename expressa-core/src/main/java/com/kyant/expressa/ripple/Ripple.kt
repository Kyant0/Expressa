package com.kyant.expressa.ripple

import androidx.compose.foundation.IndicationNodeFactory
import androidx.compose.foundation.interaction.InteractionSource
import androidx.compose.material.ripple.RippleAlpha
import androidx.compose.material.ripple.createRippleModifierNode
import androidx.compose.runtime.Stable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorProducer
import androidx.compose.ui.graphics.isSpecified
import androidx.compose.ui.graphics.isUnspecified
import androidx.compose.ui.node.CompositionLocalConsumerModifierNode
import androidx.compose.ui.node.DelegatableNode
import androidx.compose.ui.node.DelegatingNode
import androidx.compose.ui.node.ObserverModifierNode
import androidx.compose.ui.node.currentValueOf
import androidx.compose.ui.node.observeReads
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.isUnspecified
import com.kyant.expressa.ui.LocalContentColor

@Stable
fun ripple(
    bounded: Boolean = true,
    radius: Dp = Dp.Unspecified,
    color: Color = Color.Unspecified
): IndicationNodeFactory {
    return if (radius.isUnspecified && color.isUnspecified) {
        if (bounded) DefaultBoundedRipple else DefaultUnboundedRipple
    } else {
        RippleNodeFactory(bounded, radius, color)
    }
}

@Stable
fun ripple(
    color: ColorProducer,
    bounded: Boolean = true,
    radius: Dp = Dp.Unspecified
): IndicationNodeFactory {
    return RippleNodeFactory(bounded, radius, color)
}

object RippleDefaults {

    val RippleAlpha: RippleAlpha =
        RippleAlpha(
            hoveredAlpha = 0.08f,
            focusedAlpha = 0.10f,
            pressedAlpha = 0.10f,
            draggedAlpha = 0.16f
        )
}

@ConsistentCopyVisibility
@Stable
private data class RippleNodeFactory private constructor(
    private val bounded: Boolean,
    private val radius: Dp,
    private val colorProducer: ColorProducer?,
    private val color: Color
) : IndicationNodeFactory {

    constructor(
        bounded: Boolean,
        radius: Dp,
        colorProducer: ColorProducer
    ) : this(bounded, radius, colorProducer, Color.Unspecified)

    constructor(
        bounded: Boolean,
        radius: Dp,
        color: Color
    ) : this(bounded, radius, null, color)

    override fun create(interactionSource: InteractionSource): DelegatableNode {
        val colorProducer = colorProducer ?: ColorProducer { color }
        return DelegatingThemeAwareRippleNode(
            interactionSource = interactionSource,
            bounded = bounded,
            radius = radius,
            color = colorProducer
        )
    }
}

private class DelegatingThemeAwareRippleNode(
    private val interactionSource: InteractionSource,
    private val bounded: Boolean,
    private val radius: Dp,
    private val color: ColorProducer,
) : DelegatingNode(), CompositionLocalConsumerModifierNode, ObserverModifierNode {

    private var rippleNode: DelegatableNode? = null

    override fun onAttach() {
        updateConfiguration()
    }

    override fun onObservedReadsChanged() {
        updateConfiguration()
    }

    private fun updateConfiguration() {
        observeReads {
            val configuration = currentValueOf(LocalRippleConfiguration)
            if (configuration == null) {
                removeRipple()
            } else {
                if (rippleNode == null) {
                    attachNewRipple()
                }
            }
        }
    }

    private fun attachNewRipple() {
        val calculateColor = ColorProducer {
            val userDefinedColor = color()
            if (userDefinedColor.isSpecified) {
                userDefinedColor
            } else {
                val configuration = currentValueOf(LocalRippleConfiguration)
                if (configuration?.color?.isSpecified == true) {
                    configuration.color
                } else {
                    currentValueOf(LocalContentColor)
                }
            }
        }

        val calculateRippleAlpha = {
            val configuration = currentValueOf(LocalRippleConfiguration)
            configuration?.rippleAlpha ?: RippleDefaults.RippleAlpha
        }

        rippleNode =
            delegate(
                createRippleModifierNode(
                    interactionSource = interactionSource,
                    bounded = bounded,
                    radius = radius,
                    color = calculateColor,
                    rippleAlpha = calculateRippleAlpha
                )
            )
    }

    private fun removeRipple() {
        rippleNode?.let { undelegate(it) }
        rippleNode = null
    }
}

private val DefaultBoundedRipple =
    RippleNodeFactory(
        bounded = true,
        radius = Dp.Unspecified,
        color = Color.Unspecified
    )

private val DefaultUnboundedRipple =
    RippleNodeFactory(
        bounded = false,
        radius = Dp.Unspecified,
        color = Color.Unspecified
    )
