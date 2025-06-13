package com.kyant.expressa.m3.motion

import androidx.compose.animation.core.SpringSpec
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable

@Suppress("UNCHECKED_CAST")
@Immutable
object MotionScheme {

    @Stable
    fun <T> fastSpatial(): SpringSpec<T> = FastSpatial as SpringSpec<T>

    @Stable
    fun <T> fastSpatial(visibilityThreshold: T): SpringSpec<T> =
        SpringSpec(
            stiffness = FastSpatial.stiffness,
            dampingRatio = FastSpatial.dampingRatio,
            visibilityThreshold = visibilityThreshold
        )

    @Stable
    fun <T> defaultSpatial(): SpringSpec<T> = DefaultSpatial as SpringSpec<T>

    @Stable
    fun <T> defaultSpatial(visibilityThreshold: T): SpringSpec<T> =
        SpringSpec(
            stiffness = DefaultSpatial.stiffness,
            dampingRatio = DefaultSpatial.dampingRatio,
            visibilityThreshold = visibilityThreshold
        )

    @Stable
    fun <T> slowSpatial(): SpringSpec<T> = SlowSpatial as SpringSpec<T>

    @Stable
    fun <T> slowSpatial(visibilityThreshold: T): SpringSpec<T> =
        SpringSpec(
            stiffness = SlowSpatial.stiffness,
            dampingRatio = SlowSpatial.dampingRatio,
            visibilityThreshold = visibilityThreshold
        )

    @Stable
    fun <T> fastEffects(): SpringSpec<T> = FastEffects as SpringSpec<T>

    @Stable
    fun <T> fastEffects(visibilityThreshold: T): SpringSpec<T> =
        SpringSpec(
            stiffness = FastEffects.stiffness,
            dampingRatio = FastEffects.dampingRatio,
            visibilityThreshold = visibilityThreshold
        )

    @Stable
    fun <T> defaultEffects(): SpringSpec<T> = DefaultEffects as SpringSpec<T>

    @Stable
    fun <T> defaultEffects(visibilityThreshold: T): SpringSpec<T> =
        SpringSpec(
            stiffness = DefaultEffects.stiffness,
            dampingRatio = DefaultEffects.dampingRatio,
            visibilityThreshold = visibilityThreshold
        )

    @Stable
    fun <T> slowEffects(): SpringSpec<T> = SlowEffects as SpringSpec<T>

    @Stable
    fun <T> slowEffects(visibilityThreshold: T): SpringSpec<T> =
        SpringSpec(
            stiffness = SlowEffects.stiffness,
            dampingRatio = SlowEffects.dampingRatio,
            visibilityThreshold = visibilityThreshold
        )

    private val FastSpatial = SpringSpec<Any>(0.6f, 800f)
    private val DefaultSpatial = SpringSpec<Any>(0.8f, 380f)
    private val SlowSpatial = SpringSpec<Any>(0.8f, 200f)

    private val FastEffects = SpringSpec<Any>(1f, 3800f)
    private val DefaultEffects = SpringSpec<Any>(1f, 1600f)
    private val SlowEffects = SpringSpec<Any>(1f, 800f)
}
