package com.kyant.expressa.interaction

import androidx.compose.animation.core.AnimationVector
import androidx.compose.animation.core.TwoWayConverter
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable

@Immutable
interface AnimatableStatefulValue<T : Any, V : AnimationVector, S : StateHolder> : StatefulValue<T, S> {

    @Stable
    val typeConverter: TwoWayConverter<T, V>
}
