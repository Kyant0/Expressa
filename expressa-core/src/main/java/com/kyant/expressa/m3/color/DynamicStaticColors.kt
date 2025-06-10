package com.kyant.expressa.m3.color

import androidx.annotation.FloatRange
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.takeOrElse
import com.kyant.expressa.graphics.Hct
import com.kyant.expressa.mcu.DynamicScheme
import com.kyant.expressa.mcu.DynamicSchemeVariant
import com.kyant.expressa.mcu.Leakable
import com.kyant.expressa.mcu.LeakableObject

@OptIn(LeakableObject::class)
@Immutable
internal class DynamicStaticColors
@LeakableObject constructor(
    val sourceHct: Hct,
    val variant: DynamicSchemeVariant,
    val isDark: Boolean,
    @param:FloatRange(from = -1.0, to = 1.0) val contrastLevel: Float
) : StaticColors, Leakable {

    private val dynamicScheme =
        DynamicScheme(
            sourceHct = sourceHct,
            variant = variant,
            isDark = isDark,
            contrastLevel = contrastLevel.toDouble()
        )

    override fun isFreed(): Boolean {
        return dynamicScheme.isFreed()
    }

    override fun free() {
        dynamicScheme.free()
    }

    private var _accent = Color.Companion.Unspecified
    private var _onAccent = Color.Companion.Unspecified
    private var _accentContainer = Color.Companion.Unspecified
    private var _onAccentContainer = Color.Companion.Unspecified

    @Stable
    override val accent: Color
        get() = _accent.takeOrElse {
            Color(dynamicScheme.primary).also { _accent = it }
        }

    @Stable
    override val onAccent: Color
        get() = _onAccent.takeOrElse {
            Color(dynamicScheme.onPrimary).also { _onAccent = it }
        }

    @Stable
    override val accentContainer: Color
        get() = _accentContainer.takeOrElse {
            Color(dynamicScheme.primaryContainer).also { _accentContainer = it }
        }

    @Stable
    override val onAccentContainer: Color
        get() = _onAccentContainer.takeOrElse {
            Color(dynamicScheme.onPrimaryContainer).also { _onAccentContainer = it }
        }

    override fun toString(): String {
        return "StaticColors(sourceHct=$sourceHct, variant=$variant, isDark=$isDark, contrastLevel=$contrastLevel)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as DynamicStaticColors

        if (sourceHct != other.sourceHct) return false
        if (variant != other.variant) return false
        if (isDark != other.isDark) return false
        if (contrastLevel != other.contrastLevel) return false

        return true
    }

    override fun hashCode(): Int {
        var result = sourceHct.hashCode()
        result = 31 * result + variant.hashCode()
        result = 31 * result + isDark.hashCode()
        result = 31 * result + contrastLevel.hashCode()
        return result
    }
}
