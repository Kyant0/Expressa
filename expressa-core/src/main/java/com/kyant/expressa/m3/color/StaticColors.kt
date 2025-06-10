package com.kyant.expressa.m3.color

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.compose.ui.graphics.Color

@Immutable
sealed interface StaticColors {

    @Stable
    val accent: Color

    @Stable
    val onAccent: Color

    @Stable
    val accentContainer: Color

    @Stable
    val onAccentContainer: Color
}
