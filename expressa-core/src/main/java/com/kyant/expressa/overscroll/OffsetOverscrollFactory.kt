package com.kyant.expressa.overscroll

import androidx.compose.animation.core.AnimationSpec
import androidx.compose.foundation.OverscrollEffect
import androidx.compose.foundation.OverscrollFactory
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.runtime.Immutable
import kotlinx.coroutines.CoroutineScope

@Immutable
data class OffsetOverscrollFactory(
    private val orientation: Orientation,
    private val animationScope: CoroutineScope,
    private val animationSpec: AnimationSpec<Float> = OffsetOverscrollEffect.DefaultAnimationSpec,
    private val progressConverter: ProgressConverter = ProgressConverter.Default
) : OverscrollFactory {

    override fun createOverscrollEffect(): OverscrollEffect {
        return GestureEffect(
            OffsetOverscrollEffect(
                orientation = orientation,
                animationScope = animationScope,
                animationSpec = animationSpec,
                progressConverter = progressConverter
            )
        )
    }
}
