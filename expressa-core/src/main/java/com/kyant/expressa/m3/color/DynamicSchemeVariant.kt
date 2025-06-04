package com.kyant.expressa.m3.color

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import com.kyant.m3color.dynamiccolor.DynamicScheme
import com.kyant.m3color.hct.Hct
import com.kyant.m3color.scheme.SchemeContent
import com.kyant.m3color.scheme.SchemeExpressive
import com.kyant.m3color.scheme.SchemeFidelity
import com.kyant.m3color.scheme.SchemeFruitSalad
import com.kyant.m3color.scheme.SchemeMonochrome
import com.kyant.m3color.scheme.SchemeNeutral
import com.kyant.m3color.scheme.SchemeRainbow
import com.kyant.m3color.scheme.SchemeTonalSpot
import com.kyant.m3color.scheme.SchemeVibrant

@Immutable
fun interface DynamicSchemeVariant {

    @Stable
    fun toDynamicScheme(provider: ColorSchemeProvider): DynamicScheme

    companion object {

        @Stable
        val TonalSpot: DynamicSchemeVariant = { provider ->
            val hct = with(provider.sourceHct) { Hct.from(hue, chroma, tone) }
            val contrast = provider.contrastLevel.toDouble()
            SchemeTonalSpot(hct, provider.isDark, contrast, provider.spec, provider.platform)
        }

        @Stable
        val Neutral: DynamicSchemeVariant = { provider ->
            val hct = with(provider.sourceHct) { Hct.from(hue, chroma, tone) }
            val contrast = provider.contrastLevel.toDouble()
            SchemeNeutral(hct, provider.isDark, contrast, provider.spec, provider.platform)
        }

        @Stable
        val Vibrant: DynamicSchemeVariant = { provider ->
            val hct = with(provider.sourceHct) { Hct.from(hue, chroma, tone) }
            val contrast = provider.contrastLevel.toDouble()
            SchemeVibrant(hct, provider.isDark, contrast, provider.spec, provider.platform)
        }

        @Stable
        val Expressive: DynamicSchemeVariant = { provider ->
            val hct = with(provider.sourceHct) { Hct.from(hue, chroma, tone) }
            val contrast = provider.contrastLevel.toDouble()
            SchemeExpressive(hct, provider.isDark, contrast, provider.spec, provider.platform)
        }

        @Stable
        val Rainbow: DynamicSchemeVariant = { provider ->
            val hct = with(provider.sourceHct) { Hct.from(hue, chroma, tone) }
            val contrast = provider.contrastLevel.toDouble()
            SchemeRainbow(hct, provider.isDark, contrast, provider.spec, provider.platform)
        }

        @Stable
        val FruitSalad: DynamicSchemeVariant = { provider ->
            val hct = with(provider.sourceHct) { Hct.from(hue, chroma, tone) }
            val contrast = provider.contrastLevel.toDouble()
            SchemeFruitSalad(hct, provider.isDark, contrast, provider.spec, provider.platform)
        }

        @Stable
        val Monochrome: DynamicSchemeVariant = { provider ->
            val hct = with(provider.sourceHct) { Hct.from(hue, chroma, tone) }
            val contrast = provider.contrastLevel.toDouble()
            SchemeMonochrome(hct, provider.isDark, contrast, provider.spec, provider.platform)
        }

        @Stable
        val Fidelity: DynamicSchemeVariant = { provider ->
            val hct = with(provider.sourceHct) { Hct.from(hue, chroma, tone) }
            val contrast = provider.contrastLevel.toDouble()
            SchemeFidelity(hct, provider.isDark, contrast, provider.spec, provider.platform)
        }

        @Stable
        val Content: DynamicSchemeVariant = { provider ->
            val hct = with(provider.sourceHct) { Hct.from(hue, chroma, tone) }
            val contrast = provider.contrastLevel.toDouble()
            SchemeContent(hct, provider.isDark, contrast, provider.spec, provider.platform)
        }
    }
}
