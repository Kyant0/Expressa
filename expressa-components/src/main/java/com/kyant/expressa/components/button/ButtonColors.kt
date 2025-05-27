package com.kyant.expressa.components.button

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.Stable
import androidx.compose.ui.graphics.Color
import com.kyant.expressa.m3.elevation.Elevation
import com.kyant.expressa.prelude.*

@Immutable
interface ButtonColors {

    @Stable
    val containerColor: ButtonColor

    @Stable
    val shadowColor: Color

    @Stable
    val elevation: ButtonElevation

    @Stable
    val labelColor: ButtonColor

    @Stable
    val iconColor: ButtonColor

    @Stable
    val outlineColor: ButtonColor
        get() = ButtonColor(
            enabled = Color.Transparent,
            disabled = Color.Transparent
        )

    @Immutable
    data class Contained(
        override val containerColor: ButtonColor,
        override val shadowColor: Color,
        override val elevation: ButtonElevation,
        override val labelColor: ButtonColor,
        override val iconColor: ButtonColor
    ) : ButtonColors

    @Immutable
    data class Outlined(
        override val outlineColor: ButtonColor,
        override val labelColor: ButtonColor,
        override val iconColor: ButtonColor
    ) : ButtonColors {

        override val containerColor: ButtonColor =
            ButtonColor(
                enabled = Color.Transparent,
                disabled = Color.Transparent
            )

        override val shadowColor: Color = Color.Transparent

        override val elevation: ButtonElevation = ButtonElevation.Zero
    }

    @Immutable
    data class Text(
        val disabledContainerColor: Color,
        override val labelColor: ButtonColor,
        override val iconColor: ButtonColor
    ) : ButtonColors {

        override val containerColor: ButtonColor =
            ButtonColor(
                enabled = Color.Transparent,
                disabled = disabledContainerColor
            )

        override val shadowColor: Color = Color.Transparent

        override val elevation: ButtonElevation = ButtonElevation.Zero
    }

    companion object {

        @Composable
        @ReadOnlyComposable
        fun elevated(
            containerColor: Color = surfaceContainerLow,
            contentColor: Color = primary
        ): Contained = Contained(
            containerColor = ButtonColor(
                enabled = containerColor,
                disabled = onSurface.copy(alpha = 0.1f)
            ),
            shadowColor = shadow,
            elevation = ButtonElevation(
                enabled = Elevation.Level1,
                disabled = Elevation.Level0,
                hovered = Elevation.Level2,
                focused = Elevation.Level1,
                pressed = Elevation.Level1
            ),
            labelColor = ButtonColor(
                enabled = contentColor,
                disabled = onSurface.copy(alpha = 0.38f)
            ),
            iconColor = ButtonColor(
                enabled = contentColor,
                disabled = onSurface.copy(alpha = 0.38f)
            )
        )

        @Composable
        @ReadOnlyComposable
        fun filled(
            containerColor: Color = primary,
            contentColor: Color = onPrimary
        ): Contained = Contained(
            containerColor = ButtonColor(
                enabled = containerColor,
                disabled = onSurface.copy(alpha = 0.1f)
            ),
            shadowColor = shadow,
            elevation = ButtonElevation(
                enabled = Elevation.Level0,
                disabled = Elevation.Level0,
                hovered = Elevation.Level1,
                focused = Elevation.Level0,
                pressed = Elevation.Level0
            ),
            labelColor = ButtonColor(
                enabled = contentColor,
                disabled = onSurface.copy(alpha = 0.38f)
            ),
            iconColor = ButtonColor(
                enabled = contentColor,
                disabled = onSurface.copy(alpha = 0.38f)
            )
        )

        @Composable
        @ReadOnlyComposable
        fun tonal(
            containerColor: Color = secondaryContainer,
            contentColor: Color = onSecondaryContainer
        ): Contained = Contained(
            containerColor = ButtonColor(
                enabled = containerColor,
                disabled = onSurface.copy(alpha = 0.1f)
            ),
            shadowColor = shadow,
            elevation = ButtonElevation(
                enabled = Elevation.Level0,
                disabled = Elevation.Level0,
                hovered = Elevation.Level1,
                focused = Elevation.Level0,
                pressed = Elevation.Level0
            ),
            labelColor = ButtonColor(
                enabled = contentColor,
                disabled = onSurface.copy(alpha = 0.38f)
            ),
            iconColor = ButtonColor(
                enabled = contentColor,
                disabled = onSurface.copy(alpha = 0.38f)
            )
        )

        @Composable
        @ReadOnlyComposable
        fun outlined(
            outlineColor: Color = outlineVariant,
            contentColor: Color = onSurfaceVariant
        ): Outlined = Outlined(
            outlineColor = ButtonColor(
                enabled = outlineColor,
                disabled = outlineVariant
            ),
            labelColor = ButtonColor(
                enabled = contentColor,
                disabled = onSurface.copy(alpha = 0.38f)
            ),
            iconColor = ButtonColor(
                enabled = contentColor,
                disabled = onSurface.copy(alpha = 0.38f)
            )
        )

        @Composable
        @ReadOnlyComposable
        fun text(
            contentColor: Color = primary
        ): Text = Text(
            disabledContainerColor = onSurface.copy(alpha = 0.1f),
            labelColor = ButtonColor(
                enabled = contentColor,
                disabled = onSurface.copy(alpha = 0.38f)
            ),
            iconColor = ButtonColor(
                enabled = contentColor,
                disabled = onSurface.copy(alpha = 0.38f)
            )
        )
    }
}
