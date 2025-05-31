package com.kyant.expressa.components.interaction

import androidx.compose.foundation.interaction.DragInteraction
import androidx.compose.foundation.interaction.FocusInteraction
import androidx.compose.foundation.interaction.HoverInteraction
import androidx.compose.foundation.interaction.Interaction
import androidx.compose.foundation.interaction.PressInteraction
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.merge
import kotlinx.coroutines.flow.onEach

fun interactionStateFlow(
    stateHolder: StateHolder,
    delayPressRelease: (suspend () -> Unit)? = null
): Flow<InteractionState?> {
    var wasPressing = false
    val interactions = mutableListOf<Interaction>()

    return merge(
        stateHolder.interactionSource.interactions,
        stateHolder.statesFlow
    )
        .onEach { state ->
            if (state is Interaction) {
                val interaction = state
                when (interaction) {
                    is PressInteraction.Press -> interactions.add(interaction)
                    is PressInteraction.Release -> interactions.remove(interaction.press)
                    is PressInteraction.Cancel -> interactions.remove(interaction.press)

                    is FocusInteraction.Focus -> interactions.add(interaction)
                    is FocusInteraction.Unfocus -> interactions.remove(interaction.focus)

                    is HoverInteraction.Enter -> interactions.add(interaction)
                    is HoverInteraction.Exit -> interactions.remove(interaction.enter)

                    is DragInteraction.Start -> interactions.add(interaction)
                    is DragInteraction.Stop -> interactions.remove(interaction.start)
                    is DragInteraction.Cancel -> interactions.remove(interaction.start)
                }
            }
        }
        .map { state ->
            if (state is Interaction) {
                val interaction = interactions.lastOrNull()
                if (interaction != null) {
                    wasPressing = interaction is PressInteraction.Press
                    when (interaction) {
                        is PressInteraction.Press -> InteractionState.Pressed
                        is FocusInteraction.Focus -> InteractionState.Focused
                        is HoverInteraction.Enter -> InteractionState.Hovered
                        is DragInteraction.Start -> InteractionState.Dragged
                        else -> null
                    }
                } else {
                    if (delayPressRelease != null && wasPressing) {
                        wasPressing = false
                        delayPressRelease()
                    }
                    null
                }
            } else {
                null
            }
        }
}
