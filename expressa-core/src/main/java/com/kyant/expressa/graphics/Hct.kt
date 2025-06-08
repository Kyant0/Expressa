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
        return hctToInt(hue, chroma, tone)
    }

    @Stable
    fun toColor(): Color {
        return Color(this.toArgb())
    }

    companion object {

        init {
            System.loadLibrary("mcu_jni")
        }

        @Stable
        fun Color.toHct(): Hct {
            val hct = hctFromInt(this.toArgb())

            if (hct == null || hct.size != 3) {
                throw IllegalStateException("Failed to convert Color to HCT")
            }

            return Hct(
                hue = hct[0],
                chroma = hct[1],
                tone = hct[2]
            )
        }

        @FastNative
        private external fun hctFromInt(@ColorInt argb: Int): DoubleArray?

        @FastNative
        private external fun hctToInt(hue: Double, chroma: Double, tone: Double): Int
    }
}
