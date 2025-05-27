package com.kyant.expressa.ui

import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.graphics.Color

val LocalContentColor: ProvidableCompositionLocal<Color> =
    compositionLocalOf { Color.Unspecified }
