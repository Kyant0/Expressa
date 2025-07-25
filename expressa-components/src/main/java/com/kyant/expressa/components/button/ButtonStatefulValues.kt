package com.kyant.expressa.components.button

import androidx.compose.foundation.interaction.InteractionSource
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.State
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import com.kyant.expressa.components.interaction.InteractionState
import com.kyant.expressa.components.interaction.StateHolder
import com.kyant.expressa.components.interaction.statefulvalues.StatefulElevation
import com.kyant.expressa.components.interaction.statefulvalues.StatefulShape
import com.kyant.expressa.m3.elevation.Elevation
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.merge

@Stable
@ConsistentCopyVisibility
data class ButtonStateHolder internal constructor(
    override val interactionSource: InteractionSource,
    val disabled: State<Boolean>
) : StateHolder {

    override val statesFlow: Flow<*> =
        merge(
            snapshotFlow { disabled.value }
        )
}

@Immutable
data class ButtonColor(
    val enabled: Color,
    val disabled: Color
) {

    fun resolvedValue(stateHolder: ButtonStateHolder): Color {
        return when {
            stateHolder.disabled.value -> disabled
            else -> enabled
        }
    }
}

@Immutable
data class ButtonElevation(
    val enabled: Elevation = Elevation.Level0,
    val disabled: Elevation = Elevation.Level0,
    val pressed: Elevation = Elevation.Level0,
    val focused: Elevation = Elevation.Level0,
    val hovered: Elevation = Elevation.Level0
) : StatefulElevation<ButtonStateHolder> {

    override fun defaultValue(stateHolder: ButtonStateHolder): Elevation {
        return when {
            stateHolder.disabled.value -> disabled
            else -> enabled
        }
    }

    override fun resolvedValue(stateHolder: ButtonStateHolder, interactionState: InteractionState): Elevation? {
        return when {
            stateHolder.disabled.value -> disabled
            else -> when (interactionState) {
                InteractionState.Pressed -> pressed
                InteractionState.Focused -> focused
                InteractionState.Hovered -> hovered
                InteractionState.Dragged -> null
            }
        }
    }

    companion object {

        @Stable
        val Zero: ButtonElevation = ButtonElevation()
    }
}

@Immutable
data class ButtonContainerShape(
    val default: Shape,
    val pressed: Shape
) : StatefulShape<ButtonStateHolder> {

    override fun defaultValue(stateHolder: ButtonStateHolder): Shape {
        return default
    }

    override fun resolvedValue(stateHolder: ButtonStateHolder, interactionState: InteractionState): Shape? {
        return when {
            else -> when (interactionState) {
                InteractionState.Pressed -> pressed
                else -> null
            }
        }
    }
}
