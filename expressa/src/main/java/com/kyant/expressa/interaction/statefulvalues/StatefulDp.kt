package com.kyant.expressa.interaction.statefulvalues

import androidx.compose.animation.core.AnimationVector1D
import androidx.compose.animation.core.TwoWayConverter
import androidx.compose.animation.core.VectorConverter
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.compose.ui.unit.Dp
import com.kyant.expressa.interaction.AnimatableStatefulValue
import com.kyant.expressa.interaction.StateHolder
import com.kyant.expressa.interaction.StaticStatefulValue

@Immutable
interface StatefulDp<S : StateHolder> : AnimatableStatefulValue<Dp, AnimationVector1D, S> {

    override val typeConverter: TwoWayConverter<Dp, AnimationVector1D>
        get() = Dp.VectorConverter
}

@Immutable
data class StaticStatefulDp<S : StateHolder>(override val default: Dp) :
    StaticStatefulValue<Dp, S>(default), StatefulDp<S>

@Stable
fun <S : StateHolder> Dp.asStaticStatefulValue(): StaticStatefulDp<S> {
    return StaticStatefulDp(this)
}
