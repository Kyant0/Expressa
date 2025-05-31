package com.kyant.expressa.components.interaction

import androidx.compose.runtime.Immutable

@Immutable
open class StaticStatefulValue<T : Any, S : StateHolder>(
    open val default: T
) : StatefulValue<T, S> {

    final override fun defaultValue(stateHolder: S): T {
        return default
    }

    final override fun resolvedValue(stateHolder: S, interactionState: InteractionState): T {
        return default
    }
}
