package com.kyant.expressa.interaction

import androidx.compose.runtime.Immutable

@Immutable
enum class InteractionState {
    Pressed,
    Focused,
    Hovered,
    Dragged
}
