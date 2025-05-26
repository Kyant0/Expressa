package com.kyant.expressa.interaction.statefulvalues

import androidx.compose.animation.core.AnimationVector1D
import androidx.compose.animation.core.TwoWayConverter
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import com.kyant.expressa.interaction.AnimatableStatefulValue
import com.kyant.expressa.interaction.StateHolder
import com.kyant.expressa.interaction.StaticStatefulValue
import com.kyant.expressa.m3.elevation.Elevation

@Immutable
interface StatefulElevation<S : StateHolder> : AnimatableStatefulValue<Elevation, AnimationVector1D, S> {

    override val typeConverter: TwoWayConverter<Elevation, AnimationVector1D>
        get() = Elevation.VectorConverter
}

@Immutable
data class StaticStatefulElevation<S : StateHolder>(override val default: Elevation) :
    StaticStatefulValue<Elevation, S>(default), StatefulElevation<S>

@Stable
fun <S : StateHolder> Elevation.asStaticStatefulValue(): StaticStatefulElevation<S> {
    return StaticStatefulElevation(this)
}
