package com.kyant.expressa.ui

import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.NonRestartableComposable
import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.Measurable
import androidx.compose.ui.layout.MeasurePolicy
import androidx.compose.ui.layout.MeasureResult
import androidx.compose.ui.layout.MeasureScope
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

val LocalIconSize: ProvidableCompositionLocal<Dp> =
    staticCompositionLocalOf { 24.dp }

@Composable
@NonRestartableComposable
fun Icon(
    painter: Painter,
    modifier: Modifier = Modifier
) {
    val size = LocalIconSize.current
    val color = LocalContentColor.current

    Layout(
        modifier = modifier
            .size(size)
            .clipToBounds()
            .paint(
                painter = painter,
                colorFilter = ColorFilter.tint(color),
                contentScale = ContentScale.Fit
            ),
        measurePolicy = IconMeasurePolicy
    )
}

@Composable
@NonRestartableComposable
fun Icon(
    painter: Painter,
    contentDescription: String,
    modifier: Modifier = Modifier
) {
    val size = LocalIconSize.current
    val color = LocalContentColor.current

    Layout(
        modifier = modifier
            .size(size)
            .semantics {
                this.contentDescription = contentDescription
                this.role = Role.Image
            }
            .clipToBounds()
            .paint(
                painter = painter,
                colorFilter = ColorFilter.tint(color),
                contentScale = ContentScale.Fit
            ),
        measurePolicy = IconMeasurePolicy
    )
}

@Composable
@NonRestartableComposable
fun Icon(
    painter: Painter,
    tint: Color,
    modifier: Modifier = Modifier
) {
    val size = LocalIconSize.current

    Layout(
        modifier = modifier
            .size(size)
            .clipToBounds()
            .paint(
                painter = painter,
                colorFilter = ColorFilter.tint(tint),
                contentScale = ContentScale.Fit
            ),
        measurePolicy = IconMeasurePolicy
    )
}

@Composable
@NonRestartableComposable
fun Icon(
    painter: Painter,
    contentDescription: String,
    tint: Color,
    modifier: Modifier = Modifier
) {
    val size = LocalIconSize.current

    Layout(
        modifier = modifier
            .size(size)
            .semantics {
                this.contentDescription = contentDescription
                this.role = Role.Image
            }
            .clipToBounds()
            .paint(
                painter = painter,
                colorFilter = ColorFilter.tint(tint),
                contentScale = ContentScale.Fit
            ),
        measurePolicy = IconMeasurePolicy
    )
}

private object IconMeasurePolicy : MeasurePolicy {

    override fun MeasureScope.measure(
        measurables: List<Measurable>,
        constraints: Constraints
    ): MeasureResult {
        return layout(constraints.minWidth, constraints.minHeight) {}
    }
}
