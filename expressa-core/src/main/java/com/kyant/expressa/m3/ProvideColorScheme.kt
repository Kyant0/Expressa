package com.kyant.expressa.m3

import androidx.annotation.FloatRange
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import com.kyant.expressa.graphics.Hct
import com.kyant.expressa.m3.color.ColorSchemeProvider
import com.kyant.expressa.m3.color.DynamicSchemeVariant

@Composable
fun ProvideColorScheme(
    provider: ColorSchemeProvider,
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(
        localColorSchemeProvider provides provider,
        localColorScheme provides provider.toColorScheme(),
        content = content
    )
}

@Composable
fun ProvideColorScheme(
    block: MutableColorSchemeProvider.(provider: ColorSchemeProvider) -> Unit,
    content: @Composable () -> Unit
) {
    val currentProvider = LocalColorSchemeProvider.current
    val modifiedProvider = remember(currentProvider, block) {
        val provider =
            MutableColorSchemeProvider(
                isLazy = currentProvider.isLazy,
                sourceHct = currentProvider.sourceHct,
                variant = currentProvider.variant,
                isDark = currentProvider.isDark,
                contrastLevel = currentProvider.contrastLevel
            ).apply { block(currentProvider) }

        ColorSchemeProvider(
            isLazy = provider.isLazy,
            sourceHct = provider.sourceHct,
            variant = provider.variant,
            isDark = provider.isDark,
            contrastLevel = provider.contrastLevel
        )
    }

    ProvideColorScheme(
        provider = modifiedProvider,
        content = content
    )
}

class MutableColorSchemeProvider internal constructor(
    var isLazy: Boolean,
    var sourceHct: Hct,
    var variant: DynamicSchemeVariant,
    var isDark: Boolean,
    @param:FloatRange(from = -1.0, to = 1.0) var contrastLevel: Float
)
