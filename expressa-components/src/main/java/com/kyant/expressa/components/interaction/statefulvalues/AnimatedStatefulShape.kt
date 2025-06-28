package com.kyant.expressa.components.interaction.statefulvalues

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationSpec
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.Stable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.graphics.Shape
import com.kyant.expressa.components.interaction.InteractionState
import com.kyant.expressa.components.interaction.StateHolder
import com.kyant.expressa.components.interaction.StaticStatefulValue
import com.kyant.expressa.components.interaction.interactionStateFlow
import com.kyant.expressa.components.interaction.resolvedInteractionValueOrDefault
import com.kyant.expressa.components.interaction.resolvedValueAsState
import com.kyant.expressa.shape.RoundedRectangle
import com.kyant.expressa.shape.lerp
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.mapNotNull
import kotlinx.coroutines.launch

@Stable
@Composable
fun <S : StateHolder> StatefulShape<S>.animatedValueAsState(
    stateHolder: S,
    animationSpec: AnimationSpec<Float>
): State<Shape> {
    if (this is StaticStatefulValue<*, *>) {
        return resolvedValueAsState(stateHolder)
    }

    val state = remember(stateHolder) {
        mutableStateOf(defaultValue(stateHolder))
    }

    LaunchedEffect(stateHolder) {
        val valueAnimation = Animatable(0f)
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
                        animationSpec = animationSpec
                    )
                }

                val currentValue = state.value
                if (currentValue is RoundedRectangle && value is RoundedRectangle) {
                    valueAnimation.snapTo(0f)
                    valueAnimation.animateTo(
                        targetValue = 1f,
                        animationSpec = animationSpec
                    ) {
                        state.value = lerp(currentValue, value, valueAnimation.value)
                    }
                }
            }
    }

    return state
}
