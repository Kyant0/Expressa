package com.kyant.expressa.catalog.styles

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.SpringSpec
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layout
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import com.kyant.expressa.catalog.ui.PageContainer
import com.kyant.expressa.catalog.ui.SectionContainer
import com.kyant.expressa.catalog.ui.Subtitle
import com.kyant.expressa.catalog.ui.TopBar
import com.kyant.expressa.component.text.Text
import com.kyant.expressa.m3.LocalMotionScheme
import com.kyant.expressa.m3.motion.MotionScheme
import com.kyant.expressa.prelude.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.withContext

@Composable
fun MotionSchemes() {
    PageContainer {
        TopBar(
            title = { Text("Motion schemes") }
        )

        Subtitle { Text("Standard") }
        CompositionLocalProvider(
            LocalMotionScheme provides MotionScheme.Standard
        ) {
            MotionScheme()
        }

        Subtitle { Text("Expressive") }
        CompositionLocalProvider(
            LocalMotionScheme provides MotionScheme.Expressive
        ) {
            MotionScheme()
        }
    }
}

@Composable
private fun MotionScheme(modifier: Modifier = Modifier) {
    SectionContainer(modifier) {
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            SpatialMotionItem(motionSchemeFastSpatial<Float>() as SpringSpec<Float>, "Fast spatial")
            SpatialMotionItem(motionSchemeDefaultSpatial<Float>() as SpringSpec<Float>, "Default spatial")
            SpatialMotionItem(motionSchemeSlowSpatial<Float>() as SpringSpec<Float>, "Slow spatial")
            EffectsMotionItem(motionSchemeFastEffects<Color>() as SpringSpec<Color>, "Fast effects")
            EffectsMotionItem(motionSchemeDefaultEffects<Color>() as SpringSpec<Color>, "Default effects")
            EffectsMotionItem(motionSchemeSlowEffects<Color>() as SpringSpec<Color>, "Slow effects")
        }
    }
}

@Composable
private fun SpatialMotionItem(
    animationSpec: SpringSpec<Float>,
    label: String,
    modifier: Modifier = Modifier
) {
    val animation = remember { Animatable(0f) }
    LaunchedEffect(animation) {
        withContext(Dispatchers.Default) {
            while (isActive) {
                val startTime = System.currentTimeMillis()
                animation.animateTo(
                    targetValue = if (animation.targetValue == 0f) 1f else 0f,
                    animationSpec = SpringSpec(
                        stiffness = animationSpec.stiffness,
                        dampingRatio = animationSpec.dampingRatio,
                        visibilityThreshold = 0.001f
                    )
                )
                val elapsedTime = System.currentTimeMillis() - startTime
                delay(1000L - elapsedTime)
            }
        }
    }

    Column(
        modifier
            .clip(cornerShapeSmall)
            .clickable {}
            .border(
                width = 2.dp,
                color = primaryFixedDim,
                shape = cornerShapeSmall
            )
            .fillMaxWidth()
            .padding(16.dp),
        Arrangement.spacedBy(16.dp)
    ) {
        Box(
            Modifier
                .layout { measurable, constraints ->
                    val placeable = measurable.measure(constraints)
                    val width = placeable.width
                    val height = placeable.height

                    layout(width, height) {
                        placeable.placeRelative(
                            lerp(
                                0,
                                constraints.maxWidth - width,
                                animation.value
                            ),
                            0
                        )
                    }
                }
                .border(
                    width = 2.dp,
                    color = primary,
                    shape = CircleShape
                )
                .clip(CircleShape)
                .background(primaryContainer)
                .size(48.dp),
            contentAlignment = Alignment.Center
        ) {
        }
        Text(
            label,
            labelLarge
        )
    }
}

@Composable
private fun EffectsMotionItem(
    animationSpec: SpringSpec<Color>,
    label: String,
    modifier: Modifier = Modifier
) {
    val startColor = primaryContainer
    val endColor = primary
    val colorAnimation = remember { androidx.compose.animation.Animatable(startColor) }
    LaunchedEffect(colorAnimation) {
        withContext(Dispatchers.Default) {
            while (isActive) {
                val startTime = System.currentTimeMillis()
                colorAnimation.animateTo(
                    targetValue = if (colorAnimation.targetValue == startColor) endColor else startColor,
                    animationSpec = animationSpec
                )
                val elapsedTime = System.currentTimeMillis() - startTime
                delay(1000L - elapsedTime)
            }
        }
    }

    Column(
        modifier
            .clip(cornerShapeSmall)
            .clickable {}
            .border(
                width = 2.dp,
                color = primaryFixedDim,
                shape = cornerShapeSmall
            )
            .fillMaxWidth()
            .padding(16.dp),
        Arrangement.spacedBy(16.dp)
    ) {
        Box(
            Modifier
                .clip(CircleShape)
                .drawBehind {
                    drawRect(color = colorAnimation.value)
                }
                .size(48.dp),
            contentAlignment = Alignment.Center
        ) {
        }
        Text(
            label,
            labelLarge
        )
    }
}
