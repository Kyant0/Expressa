package com.kyant.expressa.overscroll

import kotlin.math.abs
import kotlin.math.sign

internal class RubberBand {

    private var limit = 0f
    private var limitSquared = 0f

    fun updateLimit(limit: Float) {
        this.limit = limit
        limitSquared = limit * limit
    }

    fun valueOf(x: Float): Float {
        return sign(x) * (limit - limitSquared / (abs(x) + limit))
    }
}
