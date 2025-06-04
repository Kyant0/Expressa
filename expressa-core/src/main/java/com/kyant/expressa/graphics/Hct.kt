package com.kyant.expressa.graphics

import androidx.annotation.ColorInt
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb

@Stable
fun Hct(@ColorInt argb: Int): Hct {
    val hct = com.kyant.m3color.hct.Hct.fromInt(argb)
    return Hct(
        hue = hct.hue,
        chroma = hct.chroma,
        tone = hct.tone
    )
}

@Immutable
data class Hct(
    val hue: Double,
    val chroma: Double,
    val tone: Double
) {

    @Stable
    fun toColor(): Color {
        val argb = com.kyant.m3color.hct.Hct.from(hue, chroma, tone).toInt()
        return Color(argb)
    }

    companion object {

        @Stable
        fun Color.toHct(): Hct {
            val hct = com.kyant.m3color.hct.Hct.fromInt(this.toArgb())
            return Hct(
                hue = hct.hue,
                chroma = hct.chroma,
                tone = hct.tone
            )
        }
    }
}
