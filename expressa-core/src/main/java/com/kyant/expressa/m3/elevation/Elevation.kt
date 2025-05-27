package com.kyant.expressa.m3.elevation

import androidx.compose.animation.core.AnimationVector
import androidx.compose.animation.core.AnimationVector1D
import androidx.compose.animation.core.TwoWayConverter
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Immutable
@JvmInline
value class Elevation(val value: Dp) {

    @Stable
    context(density: Density)
    fun toPx(): Float {
        return with(density) { value.toPx() }
    }

    @Stable
    context(density: Density)
    fun Density.roundToPx(): Int {
        return with(density) { value.roundToPx() }
    }

    companion object {

        @Stable
        val Level0: Elevation = Elevation(0.dp)

        @Stable
        val Level1: Elevation = Elevation(1.dp)

        @Stable
        val Level2: Elevation = Elevation(3.dp)

        @Stable
        val Level3: Elevation = Elevation(6.dp)

        @Stable
        val Level4: Elevation = Elevation(8.dp)

        @Stable
        val Level5: Elevation = Elevation(12.dp)

        @Stable
        val VectorConverter: TwoWayConverter<Elevation, AnimationVector1D> =
            TwoWayConverter(
                convertToVector = { elevation ->
                    AnimationVector(elevation.value.value)
                },
                convertFromVector = { vector ->
                    Elevation(Dp(vector.value))
                }
            )
    }
}
