package com.kyant.expressa.graphics

import androidx.annotation.ColorInt
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import dalvik.annotation.optimization.FastNative

@Immutable
data class Hct(
    val hue: Double,
    val chroma: Double,
    val tone: Double
) {

    @Stable
    fun toArgb(): Int {
        return if (USE_NATIVE) {
            hctToInt(hue, chroma, tone)
        } else {
            com.kyant.m3color.hct.Hct.from(hue, chroma, tone).toInt()
        }
    }

    @Stable
    fun toColor(): Color {
        return Color(this.toArgb())
    }

    companion object {

        private const val USE_NATIVE = true

        init {
            System.loadLibrary("mcu_jni")
        }

        @Stable
        fun Color.toHct(): Hct {
            if (USE_NATIVE) {
                val hct = hctFromInt(this.toArgb())
                    ?: throw IllegalStateException("Failed to convert Color to HCT")

                if (hct.size != 3) {
                    throw IllegalStateException("Expected HCT array of size 3, got ${hct.size}")
                }
                return Hct(
                    hue = hct[0],
                    chroma = hct[1],
                    tone = hct[2]
                )
            } else {
                val hct = com.kyant.m3color.hct.Hct.fromInt(this.toArgb())
                return Hct(
                    hue = hct.hue,
                    chroma = hct.chroma,
                    tone = hct.tone
                )
            }
        }

        @FastNative
        private external fun hctFromInt(@ColorInt argb: Int): DoubleArray?

        @FastNative
        private external fun hctToInt(hue: Double, chroma: Double, tone: Double): Int
    }
}
