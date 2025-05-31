package com.kyant.expressa.components.interaction

import androidx.compose.runtime.Immutable

@Immutable
enum class InteractionState {
    Pressed,
    Focused,
    Hovered,
    Dragged
}
