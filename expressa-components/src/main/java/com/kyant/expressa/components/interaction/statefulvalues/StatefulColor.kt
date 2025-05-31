package com.kyant.expressa.components.interaction.statefulvalues

import androidx.compose.animation.VectorConverter
import androidx.compose.animation.core.AnimationVector4D
import androidx.compose.animation.core.TwoWayConverter
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.colorspace.ColorSpace
import com.kyant.expressa.components.interaction.AnimatableStatefulValue
import com.kyant.expressa.components.interaction.StateHolder
import com.kyant.expressa.components.interaction.StaticStatefulValue

@Immutable
interface StatefulColor<S : StateHolder> : AnimatableStatefulValue<Color, AnimationVector4D, S> {

    val colorSpace: ColorSpace

    override val typeConverter: TwoWayConverter<Color, AnimationVector4D>
        get() = Color.VectorConverter(colorSpace)

}

@Immutable
data class StaticStatefulColor<S : StateHolder>(override val default: Color) :
    StaticStatefulValue<Color, S>(default), StatefulColor<S> {

    override val colorSpace: ColorSpace
        get() = default.colorSpace
}

@Stable
fun <S : StateHolder> Color.asStaticStatefulValue(): StaticStatefulColor<S> {
    return StaticStatefulColor(this)
}
