package com.kyant.expressa.mcu

import androidx.compose.runtime.Immutable
import com.kyant.expressa.graphics.Hct

@Immutable
data class TonalPalette(
    val hue: Double,
    val chroma: Double,
    val keyColor: Hct
)
