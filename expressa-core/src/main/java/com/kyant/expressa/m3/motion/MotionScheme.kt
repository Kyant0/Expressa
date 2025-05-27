package com.kyant.expressa.m3.motion

import androidx.compose.animation.core.FiniteAnimationSpec
import androidx.compose.animation.core.SpringSpec
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable

@Immutable
interface MotionScheme {

    fun <T> fastSpatial(): FiniteAnimationSpec<T>
    fun <T> defaultSpatial(): FiniteAnimationSpec<T>
    fun <T> slowSpatial(): FiniteAnimationSpec<T>

    fun <T> fastEffects(): FiniteAnimationSpec<T>
    fun <T> defaultEffects(): FiniteAnimationSpec<T>
    fun <T> slowEffects(): FiniteAnimationSpec<T>

    companion object {

        @Stable
        val Standard: MotionScheme = StandardMotionScheme

        @Stable
        val Expressive: MotionScheme = ExpressiveMotionScheme
    }
}

@Suppress("UNCHECKED_CAST")
@Immutable
private object StandardMotionScheme : MotionScheme {

    override fun <T> fastSpatial(): FiniteAnimationSpec<T> = FastSpatial as FiniteAnimationSpec<T>
    override fun <T> defaultSpatial(): FiniteAnimationSpec<T> = DefaultSpatial as FiniteAnimationSpec<T>
    override fun <T> slowSpatial(): FiniteAnimationSpec<T> = SlowSpatial as FiniteAnimationSpec<T>

    override fun <T> fastEffects(): FiniteAnimationSpec<T> = FastEffects as FiniteAnimationSpec<T>
    override fun <T> defaultEffects(): FiniteAnimationSpec<T> = DefaultEffects as FiniteAnimationSpec<T>
    override fun <T> slowEffects(): FiniteAnimationSpec<T> = SlowEffects as FiniteAnimationSpec<T>

    private val FastSpatial = SpringSpec<Any>(0.9f, 1400f)
    private val DefaultSpatial = SpringSpec<Any>(0.9f, 700f)
    private val SlowSpatial = SpringSpec<Any>(0.9f, 300f)

    private val FastEffects = SpringSpec<Any>(1f, 3800f)
    private val DefaultEffects = SpringSpec<Any>(1f, 1600f)
    private val SlowEffects = SpringSpec<Any>(1f, 800f)
}

@Suppress("UNCHECKED_CAST")
@Immutable
private object ExpressiveMotionScheme : MotionScheme {

    override fun <T> fastSpatial(): FiniteAnimationSpec<T> = FastSpatial as FiniteAnimationSpec<T>
    override fun <T> defaultSpatial(): FiniteAnimationSpec<T> = DefaultSpatial as FiniteAnimationSpec<T>
    override fun <T> slowSpatial(): FiniteAnimationSpec<T> = SlowSpatial as FiniteAnimationSpec<T>

    override fun <T> fastEffects(): FiniteAnimationSpec<T> = FastEffects as FiniteAnimationSpec<T>
    override fun <T> defaultEffects(): FiniteAnimationSpec<T> = DefaultEffects as FiniteAnimationSpec<T>
    override fun <T> slowEffects(): FiniteAnimationSpec<T> = SlowEffects as FiniteAnimationSpec<T>

    private val FastSpatial = SpringSpec<Any>(0.6f, 800f)
    private val DefaultSpatial = SpringSpec<Any>(0.8f, 380f)
    private val SlowSpatial = SpringSpec<Any>(0.8f, 200f)

    private val FastEffects = SpringSpec<Any>(1f, 3800f)
    private val DefaultEffects = SpringSpec<Any>(1f, 1600f)
    private val SlowEffects = SpringSpec<Any>(1f, 800f)
}
