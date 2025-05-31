package com.kyant.expressa.ripple

import androidx.compose.material.ripple.RippleAlpha
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.graphics.Color

val LocalRippleConfiguration: ProvidableCompositionLocal<RippleConfiguration?> =
    compositionLocalOf { RippleConfiguration() }

@Immutable
data class RippleConfiguration(
    val color: Color = Color.Unspecified,
    val rippleAlpha: RippleAlpha? = null
)
