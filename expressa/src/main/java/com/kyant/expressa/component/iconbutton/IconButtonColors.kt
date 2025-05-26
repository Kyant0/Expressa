package com.kyant.expressa.component.iconbutton

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.Stable
import androidx.compose.ui.graphics.Color
import com.kyant.expressa.component.button.ButtonColor
import com.kyant.expressa.prelude.*

@Immutable
interface IconButtonColors {

    @Stable
    val containerColor: ButtonColor

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
        override val iconColor: ButtonColor
    ) : IconButtonColors

    @Immutable
    data class Outlined(
        override val outlineColor: ButtonColor,
        override val iconColor: ButtonColor
    ) : IconButtonColors {

        override val containerColor: ButtonColor =
            ButtonColor(
                enabled = Color.Transparent,
                disabled = Color.Transparent
            )
    }

    @Immutable
    data class Standard(
        override val iconColor: ButtonColor
    ) : IconButtonColors {

        override val containerColor: ButtonColor =
            ButtonColor(
                enabled = Color.Transparent,
                disabled = Color.Transparent
            )
    }

    companion object {

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
            iconColor = ButtonColor(
                enabled = contentColor,
                disabled = onSurface.copy(alpha = 0.38f)
            )
        )

        @Composable
        @ReadOnlyComposable
        fun standard(
            contentColor: Color = onSurfaceVariant
        ): Standard = Standard(
            iconColor = ButtonColor(
                enabled = contentColor,
                disabled = onSurface.copy(alpha = 0.38f)
            )
        )
    }
}
