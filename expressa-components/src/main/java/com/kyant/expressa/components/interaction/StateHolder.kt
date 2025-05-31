package com.kyant.expressa.components.interaction

import androidx.compose.foundation.interaction.InteractionSource
import androidx.compose.runtime.Stable
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

@Stable
interface StateHolder {
    val interactionSource: InteractionSource

    val statesFlow: Flow<*>
        get() = emptyFlow<Any>()
}
