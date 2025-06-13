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
class ShapeAnimator(
    private val initialValue: InterpolableShape
) {

    private val animationState = Animatable(0f)
    private var startShape: InterpolableShape? = null
    private var targetShape: InterpolableShape? = null
    private var shapeMorph: ShapeMorph? = null
    private var currentShape: Shape by mutableStateOf(initialValue)
    private var isRunningReverse by mutableStateOf(false)

    val value: Shape
        get() = currentShape

    val targetValue: InterpolableShape
        get() = targetShape ?: initialValue

    val fraction: Float by derivedStateOf {
        if (isRunningReverse) {
            1f - animationState.value
        } else {
            animationState.value
        }
    }

    suspend fun animateTo(
        targetValue: InterpolableShape,
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
            val startShape = (currentShape as? InterpolableShape) ?: awaitInterpolableShape()
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
        targetValue: InterpolableShape,
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
            val startShape = (currentShape as? InterpolableShape) ?: awaitInterpolableShape()
            coroutineScope.launch {
                val shapeMorph = ShapeMorph(startShape, targetValue)

                this@ShapeAnimator.startShape = startShape
                targetShape = targetValue
                this@ShapeAnimator.shapeMorph = shapeMorph
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

    private suspend fun awaitInterpolableShape(): InterpolableShape {
        return snapshotFlow { currentShape }
            .filterIsInstance<InterpolableShape>()
            .first()
    }
}
