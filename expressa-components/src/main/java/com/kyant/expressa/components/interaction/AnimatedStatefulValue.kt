package com.kyant.expressa.components.interaction

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.AnimationState
import androidx.compose.animation.core.AnimationVector
import androidx.compose.animation.core.animateTo
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.Stable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshotFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.mapNotNull
import kotlinx.coroutines.launch

@Suppress("UNCHECKED_CAST")
@Stable
@Composable
fun <T : Any, V : AnimationVector, S : StateHolder> AnimatableStatefulValue<T, V, S>.animatedValueAsState(
    stateHolder: S,
    animationSpec: AnimationSpec<Any>
): State<T> {
    if (this is StaticStatefulValue<*, *>) {
        return resolvedValueAsState(stateHolder)
    }

    val state = remember(stateHolder) {
        mutableStateOf(defaultValue(stateHolder))
    }

    LaunchedEffect(stateHolder) {
        val valueAnimation =
            AnimationState(
                typeConverter = typeConverter,
                initialValue = state.value
            )
        val pressedFractionAnimation = Animatable(0f)

        interactionStateFlow(
            stateHolder = stateHolder,
            delayPressRelease = {
                if (pressedFractionAnimation.value <= 0.75f) {
                    snapshotFlow { pressedFractionAnimation.value }
                        .first { it > 0.75f }
                }
            }
        )
            .mapNotNull { interactionState ->
                Pair(
                    interactionState,
                    resolvedInteractionValueOrDefault(stateHolder, interactionState) ?: return@mapNotNull null
                )
            }
            .collectLatest { (interactionState, value) ->
                launch {
                    pressedFractionAnimation.animateTo(
                        targetValue = if (interactionState == InteractionState.Pressed) 1f else 0f,
                        animationSpec = animationSpec as AnimationSpec<Float>
                    )
                }

                valueAnimation.animateTo(
                    targetValue = value,
                    animationSpec = animationSpec as AnimationSpec<T>
                ) {
                    state.value = valueAnimation.value
                }
            }
    }

    return state
}
