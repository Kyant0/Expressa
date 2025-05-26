package com.kyant.expressa.interaction.statefulvalues

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import com.kyant.expressa.interaction.StateHolder
import com.kyant.expressa.interaction.StatefulValue
import com.kyant.expressa.interaction.StaticStatefulValue
import com.kyant.expressa.shape.OmniShape

@Immutable
interface StatefulShape<S : StateHolder> :
    StatefulValue<OmniShape, S>

@Immutable
data class StaticStatefulShape<S : StateHolder>(override val default: OmniShape) :
    StaticStatefulValue<OmniShape, S>(default), StatefulShape<S>

@Stable
fun <S : StateHolder> OmniShape.asStaticStatefulValue(): StaticStatefulShape<S> {
    return StaticStatefulShape(this)
}
