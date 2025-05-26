package com.kyant.expressa.catalog

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.ContentTransform
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.ui.NavDisplay
import com.kyant.expressa.catalog.components.Buttons
import com.kyant.expressa.catalog.components.IconButtons
import com.kyant.expressa.catalog.styles.ColorSchemes
import com.kyant.expressa.catalog.styles.Elevation
import com.kyant.expressa.catalog.styles.MotionSchemes
import com.kyant.expressa.catalog.styles.Shapes
import com.kyant.expressa.catalog.styles.Typography
import com.kyant.expressa.component.text.Text
import com.kyant.expressa.m3.motion.EaseEmphasized
import com.kyant.expressa.m3.motion.EaseEmphasizedAccelerate
import com.kyant.expressa.m3.motion.EaseEmphasizedDecelerate
import com.kyant.expressa.prelude.*

@Composable
fun NavContent() {
    val backStack = remember { mutableStateListOf<Any>(Category) }

    val transitionSpec: AnimatedContentTransitionScope<*>.() -> ContentTransform = remember {
        {
            ContentTransform(
                targetContentEnter =
                    slideInHorizontally(
                        tween(700, 0, EaseEmphasized)
                    ) { it / 2 } + fadeIn(
                        tween(500, 200, EaseEmphasizedDecelerate)
                    ),
                initialContentExit =
                    slideOutHorizontally(
                        tween(700, 50, EaseEmphasized)
                    ) { -it / 2 } + fadeOut(
                        tween(200, 0, EaseEmphasizedAccelerate)
                    )
            )
        }
    }

    val predictivePopTransitionSpec: AnimatedContentTransitionScope<*>.() -> ContentTransform = remember {
        {
            ContentTransform(
                targetContentEnter =
                    slideInHorizontally(
                        tween(700, 0, EaseEmphasized)
                    ) { -it / 2 } + fadeIn(
                        tween(500, 200, EaseEmphasizedDecelerate)
                    ),
                initialContentExit =
                    slideOutHorizontally(
                        tween(700, 0, EaseEmphasized)
                    ) { it / 2 } + fadeOut(
                        tween(200, 0, EaseEmphasizedAccelerate)
                    )
            )
        }
    }

    NavDisplay(
        backStack = backStack,
        modifier = Modifier
            .background(surfaceContainer)
            .fillMaxSize(),
        transitionSpec = transitionSpec,
        popTransitionSpec = transitionSpec,
        predictivePopTransitionSpec = predictivePopTransitionSpec
    ) { key ->
        when (key) {
            Category -> NavEntry(key) { Catalog(backStack) }

            Styles.ColorSchemes -> NavEntry(key) { ColorSchemes() }
            Styles.Elevation -> NavEntry(key) { Elevation() }
            Styles.MotionSchemes -> NavEntry(key) { MotionSchemes() }
            Styles.Shapes -> NavEntry(key) { Shapes() }
            Styles.Typography -> NavEntry(key) { Typography() }

            Components.Buttons -> NavEntry(key) { Buttons() }
            Components.IconButtons -> NavEntry(key) { IconButtons() }

            else -> NavEntry(Unit) { Text("Unknown route") }
        }
    }
}
