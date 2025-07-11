package com.kyant.expressa.overscroll

import androidx.compose.foundation.gestures.Orientation
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.Velocity

interface SpaceVectorConverter {
    fun Offset.toFloat(): Float

    fun Velocity.toFloat(): Float

    fun IntOffset.toInt(): Int

    fun Float.toOffset(): Offset

    fun Float.toVelocity(): Velocity

    fun Int.toIntOffset(): IntOffset
}

fun SpaceVectorConverter(orientation: Orientation): SpaceVectorConverter =
    when (orientation) {
        Orientation.Horizontal -> HorizontalConverter
        Orientation.Vertical -> VerticalConverter
    }

private data object HorizontalConverter : SpaceVectorConverter {
    override fun Offset.toFloat() = x

    override fun Velocity.toFloat() = x

    override fun IntOffset.toInt() = x

    override fun Float.toOffset() = Offset(this, 0f)

    override fun Float.toVelocity() = Velocity(this, 0f)

    override fun Int.toIntOffset() = IntOffset(this, 0)
}

private data object VerticalConverter : SpaceVectorConverter {
    override fun Offset.toFloat() = y

    override fun Velocity.toFloat() = y

    override fun IntOffset.toInt() = y

    override fun Float.toOffset() = Offset(0f, this)

    override fun Float.toVelocity() = Velocity(0f, this)

    override fun Int.toIntOffset() = IntOffset(0, this)
}
