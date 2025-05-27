@file:Suppress("NOTHING_TO_INLINE")

package com.kyant.expressa.prelude

import androidx.compose.animation.core.FiniteAnimationSpec
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import com.kyant.expressa.m3.LocalMotionScheme

@Composable
@ReadOnlyComposable
inline fun <T> motionSchemeFastSpatial(): FiniteAnimationSpec<T> = LocalMotionScheme.current.fastSpatial()

@Composable
@ReadOnlyComposable
inline fun <T> motionSchemeDefaultSpatial(): FiniteAnimationSpec<T> = LocalMotionScheme.current.defaultSpatial()

@Composable
@ReadOnlyComposable
inline fun <T> motionSchemeSlowSpatial(): FiniteAnimationSpec<T> = LocalMotionScheme.current.slowSpatial()

@Composable
@ReadOnlyComposable
inline fun <T> motionSchemeFastEffects(): FiniteAnimationSpec<T> = LocalMotionScheme.current.fastEffects()

@Composable
@ReadOnlyComposable
inline fun <T> motionSchemeDefaultEffects(): FiniteAnimationSpec<T> = LocalMotionScheme.current.defaultEffects()

@Composable
@ReadOnlyComposable
inline fun <T> motionSchemeSlowEffects(): FiniteAnimationSpec<T> = LocalMotionScheme.current.slowEffects()
