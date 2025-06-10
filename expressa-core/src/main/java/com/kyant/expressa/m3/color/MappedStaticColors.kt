package com.kyant.expressa.m3.color

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.compose.ui.graphics.Color

@Stable
fun staticColorsOf(
    accent: Color,
    onAccent: Color,
    accentContainer: Color,
    onAccentContainer: Color
): StaticColors =
    MappedStaticColors(
        accent = accent,
        onAccent = onAccent,
        accentContainer = accentContainer,
        onAccentContainer = onAccentContainer
    )

@Immutable
internal class MappedStaticColors(
    override val accent: Color,
    override val onAccent: Color,
    override val accentContainer: Color,
    override val onAccentContainer: Color
) : StaticColors {

    override fun toString(): String {
        return "StaticColors(accent=$accent, onAccent=$onAccent, accentContainer=$accentContainer, " +
                "onAccentContainer=$onAccentContainer)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as MappedStaticColors

        if (accent != other.accent) return false
        if (onAccent != other.onAccent) return false
        if (accentContainer != other.accentContainer) return false
        if (onAccentContainer != other.onAccentContainer) return false

        return true
    }

    override fun hashCode(): Int {
        var result = accent.hashCode()
        result = 31 * result + onAccent.hashCode()
        result = 31 * result + accentContainer.hashCode()
        result = 31 * result + onAccentContainer.hashCode()
        return result
    }
}
