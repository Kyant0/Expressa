package com.kyant.expressa.graphics

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import com.kyant.m3color.hct.Hct.from
import com.kyant.m3color.hct.Hct.fromInt

@Immutable
data class Hct(
    val hue: Double,
    val chroma: Double,
    val tone: Double
) {

    @Stable
    fun toColor(): Color {
        val argb = from(hue, chroma, tone).toInt()
        return Color(argb)
    }

    companion object {

        @Stable
        fun Color.toHct(): Hct {
            val hct = fromInt(this.toArgb())
            return Hct(
                hue = hct.hue,
                chroma = hct.chroma,
                tone = hct.tone
            )
        }

        @Stable
        fun Int.toHct(): Hct {
            val hct = fromInt(this)
            return Hct(
                hue = hct.hue,
                chroma = hct.chroma,
                tone = hct.tone
            )
        }
    }
}
