package com.kyant.expressa.components.interaction.statefulvalues

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.compose.ui.graphics.Shape
import com.kyant.expressa.components.interaction.StateHolder
import com.kyant.expressa.components.interaction.StatefulValue
import com.kyant.expressa.components.interaction.StaticStatefulValue

@Immutable
interface StatefulShape<S : StateHolder> :
    StatefulValue<Shape, S>

@Immutable
data class StaticStatefulShape<S : StateHolder>(override val default: Shape) :
    StaticStatefulValue<Shape, S>(default), StatefulShape<S>

@Stable
fun <S : StateHolder> Shape.asStaticStatefulValue(): StaticStatefulShape<S> {
    return StaticStatefulShape(this)
}
