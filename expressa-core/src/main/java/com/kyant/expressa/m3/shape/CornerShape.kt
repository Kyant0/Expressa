package com.kyant.expressa.m3.shape

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.compose.ui.unit.dp
import com.kyant.expressa.shape.CapsuleShape
import com.kyant.expressa.shape.InterpolableRectangle
import com.kyant.expressa.shape.RoundedRectangle

@Immutable
object CornerShape {

    @Stable
    val none: InterpolableRectangle = InterpolableRectangle

    @Stable
    val extraSmall: RoundedRectangle = RoundedRectangle(4.dp)

    @Stable
    val small: RoundedRectangle = RoundedRectangle(8.dp)

    @Stable
    val medium: RoundedRectangle = RoundedRectangle(12.dp)

    @Stable
    val large: RoundedRectangle = RoundedRectangle(16.dp)

    @Stable
    val largeIncreased: RoundedRectangle = RoundedRectangle(20.dp)

    @Stable
    val extraLarge: RoundedRectangle = RoundedRectangle(28.dp)

    @Stable
    val extraLargeIncreased: RoundedRectangle = RoundedRectangle(32.dp)

    @Stable
    val extraExtraLarge: RoundedRectangle = RoundedRectangle(48.dp)

    @Stable
    val full: RoundedRectangle = CapsuleShape
}
