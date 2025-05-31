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
import com.kyant.expressa.shape.OmniShapeAnimator
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
        mutableStateOf<Shape>(defaultValue(stateHolder))
    }

    LaunchedEffect(stateHolder) {
        val valueAnimation = OmniShapeAnimator(defaultValue(stateHolder))
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
            .collect { (interactionState, value) ->
                launch {
                    pressedFractionAnimation.animateTo(
                        targetValue = if (interactionState == InteractionState.Pressed) 1f else 0f,
                        animationSpec = animationSpec
                    )
                }

                valueAnimation.animateTo(
                    coroutineScope = this,
                    targetValue = value,
                    animationSpec = animationSpec
                ) { value ->
                    state.value = value
                }
            }
    }

    return state
}
