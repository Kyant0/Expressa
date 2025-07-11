package com.kyant.expressa.overscroll

import com.kyant.expressa.overscroll.ProgressConverter.Companion.linear
import kotlin.math.tanh

/** This converter lets you change a linear progress into a function of your choice. */
fun interface ProgressConverter {

    fun convert(progress: Float): Float

    companion object {

        /** Starts linearly with some resistance and slowly approaches to 1f */
        val Default: ProgressConverter = tanh(maxProgress = 1f, tilt = 2f)

        /**
         * The scroll stays linear, with [factor] you can control how much resistance there is.
         *
         * @param factor If you choose a value between 0f and 1f, the progress will grow more
         *   slowly, like there's resistance. A value of 1f means there's no resistance.
         */
        fun linear(factor: Float = 1f): ProgressConverter = ProgressConverter { it * factor }

        /**
         * This function starts linear and slowly approaches [maxProgress].
         *
         * See a [visual representation](https://www.desmos.com/calculator/usgvvf0z1u) of this
         * function.
         *
         * @param maxProgress is the maximum progress value.
         * @param tilt behaves similarly to the factor in the [linear] function, and allows you to
         *   control how quickly you get to the [maxProgress].
         */
        fun tanh(maxProgress: Float, tilt: Float = 1f): ProgressConverter = ProgressConverter {
            maxProgress * tanh(x = it / (maxProgress * tilt))
        }
    }
}
