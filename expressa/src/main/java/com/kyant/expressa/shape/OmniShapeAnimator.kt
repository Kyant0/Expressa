package com.kyant.expressa.shape

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.spring
import androidx.compose.runtime.Stable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.graphics.Shape
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

@Stable
class OmniShapeAnimator(
    private val initialValue: OmniShape
) {

    private val animationState = Animatable(0f)
    private var startShape: OmniShape? = null
    private var targetShape: OmniShape? = null
    private var shapeMorph: ShapeMorph? = null
    private var currentShape: Shape by mutableStateOf(initialValue)
    private var isRunningReverse by mutableStateOf(false)

    val value: Shape
        get() = currentShape

    val targetValue: OmniShape
        get() = targetShape ?: initialValue

    val fraction: Float by derivedStateOf {
        if (isRunningReverse) {
            1f - animationState.value
        } else {
            animationState.value
        }
    }

    suspend fun animateTo(
        targetValue: OmniShape,
        animationSpec: AnimationSpec<Float> = spring(),
        block: ((Shape) -> Unit)? = null
    ) {
        if (targetValue == startShape) {
            val shapeMorph = shapeMorph!!

            targetShape = targetValue
            isRunningReverse = true

            animationState.animateTo(
                targetValue = 0f,
                animationSpec = animationSpec
            ) {
                currentShape = shapeMorph.toShape(value)
                block?.invoke(currentShape)
            }
        } else {
            val startShape = (currentShape as? OmniShape) ?: awaitCurrentOmniShape()
            val shapeMorph = ShapeMorph(startShape, targetValue)

            this.startShape = startShape
            targetShape = targetValue
            this.shapeMorph = shapeMorph
            isRunningReverse = false

            animationState.snapTo(0f)
            animationState.animateTo(
                targetValue = 1f,
                animationSpec = animationSpec
            ) {
                currentShape = shapeMorph.toShape(value)
                block?.invoke(currentShape)
            }
        }
    }

    suspend fun animateTo(
        coroutineScope: CoroutineScope,
        targetValue: OmniShape,
        animationSpec: AnimationSpec<Float> = spring(),
        block: ((Shape) -> Unit)? = null
    ) {
        if (targetValue == startShape) {
            coroutineScope.launch {
                val shapeMorph = shapeMorph!!

                targetShape = targetValue
                isRunningReverse = true

                animationState.animateTo(
                    targetValue = 0f,
                    animationSpec = animationSpec
                ) {
                    currentShape = shapeMorph.toShape(value)
                    block?.invoke(currentShape)
                }
            }
        } else {
            val startShape = (currentShape as? OmniShape) ?: awaitCurrentOmniShape()
            coroutineScope.launch {
                val shapeMorph = ShapeMorph(startShape, targetValue)

                this@OmniShapeAnimator.startShape = startShape
                targetShape = targetValue
                this@OmniShapeAnimator.shapeMorph = shapeMorph
                isRunningReverse = false

                animationState.snapTo(0f)
                animationState.animateTo(
                    targetValue = 1f,
                    animationSpec = animationSpec
                ) {
                    currentShape = shapeMorph.toShape(value)
                    block?.invoke(currentShape)
                }
            }
        }
    }

    private suspend fun awaitCurrentOmniShape(): OmniShape {
        return snapshotFlow { currentShape }
            .filterIsInstance<OmniShape>()
            .first()
    }
}
