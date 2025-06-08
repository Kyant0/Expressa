package com.kyant.expressa.catalog.ui

import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.LocalOverscrollFactory
import androidx.compose.material.ripple.RippleAlpha
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import com.kyant.expressa.m3.LocalColorScheme
import com.kyant.expressa.m3.color.ColorScheme
import com.kyant.expressa.overscroll.rememberOffsetOverscrollFactory
import com.kyant.expressa.prelude.*
import com.kyant.expressa.ripple.LocalRippleConfiguration
import com.kyant.expressa.ripple.RippleConfiguration
import com.kyant.expressa.ripple.ripple
import com.kyant.expressa.ui.LocalContentColor

@Composable
fun ExpressaCatalogTheme(
    content: @Composable () -> Unit
) {
    val rippleConfiguration = remember {
        RippleConfiguration(
            rippleAlpha = RippleAlpha(
                hoveredAlpha = 2f * 0.08f,
                focusedAlpha = 2f * 0.10f,
                pressedAlpha = 2f * 0.10f,
                draggedAlpha = 2f * 0.16f
            )
        )
    }
    val overscrollFactory = rememberOffsetOverscrollFactory()

    CompositionLocalProvider(
        LocalColorScheme provides ColorScheme.systemDynamic()
    ) {
        CompositionLocalProvider(
            LocalContentColor provides onSurface,
            LocalRippleConfiguration provides rippleConfiguration,
            LocalIndication provides ripple(),
            LocalOverscrollFactory provides overscrollFactory,
            content = content
        )
    }
}
