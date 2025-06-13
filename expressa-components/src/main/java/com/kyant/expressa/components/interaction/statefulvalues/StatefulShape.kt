package com.kyant.expressa.components.interaction.statefulvalues

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import com.kyant.expressa.components.interaction.StateHolder
import com.kyant.expressa.components.interaction.StatefulValue
import com.kyant.expressa.components.interaction.StaticStatefulValue
import com.kyant.expressa.shape.InterpolableShape

@Immutable
interface StatefulShape<S : StateHolder> :
    StatefulValue<InterpolableShape, S>

@Immutable
data class StaticStatefulShape<S : StateHolder>(override val default: InterpolableShape) :
    StaticStatefulValue<InterpolableShape, S>(default), StatefulShape<S>

@Stable
fun <S : StateHolder> InterpolableShape.asStaticStatefulValue(): StaticStatefulShape<S> {
    return StaticStatefulShape(this)
}
