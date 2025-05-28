package com.kyant.expressa.catalog.ui

import androidx.compose.foundation.LocalOverscrollFactory
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.ripple.RippleAlpha
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import com.kyant.expressa.m3.LocalColorScheme
import com.kyant.expressa.m3.color.ColorScheme
import com.kyant.expressa.overscroll.rememberOffsetOverscrollFactory
import com.kyant.expressa.ripple.ProvideRipples
import com.kyant.expressa.ui.LocalContentColor

@Composable
fun ExpressaCatalogTheme(
    content: @Composable () -> Unit
) {
    val isDark = isSystemInDarkTheme()
    val rippleAlpha = remember {
        RippleAlpha(
            hoveredAlpha = 2f * 0.08f,
            focusedAlpha = 2f * 0.10f,
            pressedAlpha = 2f * 0.10f,
            draggedAlpha = 2f * 0.16f
        )
    }

    val overscrollFactory = rememberOffsetOverscrollFactory()

    ProvideRipples(rippleAlpha = rippleAlpha) {
        CompositionLocalProvider(
            LocalColorScheme provides ColorScheme.systemDynamic(),
            LocalContentColor provides if (isDark) Color.White else Color.Black,
            LocalOverscrollFactory provides overscrollFactory,
            content = content
        )
    }
}
