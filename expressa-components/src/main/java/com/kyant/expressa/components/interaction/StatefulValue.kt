package com.kyant.expressa.components.interaction

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.Stable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.mapNotNull

@Immutable
interface StatefulValue<T : Any, S : StateHolder> {

    fun defaultValue(stateHolder: S): T

    fun resolvedValue(stateHolder: S, interactionState: InteractionState): T?
}

@Stable
@Composable
fun <T : Any, S : StateHolder> StatefulValue<T, S>.resolvedValueAsState(
    stateHolder: S
): State<T> {
    if (this is StaticStatefulValue<*, *>) {
        return remember(stateHolder) { mutableStateOf(defaultValue(stateHolder)) }
    }

    val state = remember(stateHolder) {
        mutableStateOf(defaultValue(stateHolder))
    }

    LaunchedEffect(stateHolder) {
        interactionStateFlow(stateHolder = stateHolder)
            .mapNotNull { interactionState ->
                resolvedInteractionValueOrDefault(stateHolder, interactionState)
            }
            .distinctUntilChanged()
            .collect { resolvedValue ->
                state.value = resolvedValue
            }
    }

    return state
}

@Stable
internal fun <T : Any, S : StateHolder> StatefulValue<T, S>.resolvedInteractionValueOrDefault(
    stateHolder: S,
    interactionState: InteractionState?
): T? {
    return if (interactionState != null) {
        resolvedValue(stateHolder, interactionState)
    } else {
        defaultValue(stateHolder)
    }
}
