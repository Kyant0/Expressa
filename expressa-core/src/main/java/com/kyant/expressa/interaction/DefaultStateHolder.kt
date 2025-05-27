package com.kyant.expressa.interaction

import androidx.compose.foundation.interaction.InteractionSource
import androidx.compose.runtime.Stable

@Stable
data class DefaultStateHolder(
    override val interactionSource: InteractionSource
) : StateHolder
