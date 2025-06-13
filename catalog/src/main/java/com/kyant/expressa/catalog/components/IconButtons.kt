package com.kyant.expressa.catalog.components

import androidx.compose.foundation.gestures.awaitEachGesture
import androidx.compose.foundation.interaction.FocusInteraction
import androidx.compose.foundation.interaction.HoverInteraction
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.PointerEventPass
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.kyant.expressa.catalog.R
import com.kyant.expressa.catalog.ui.ComponentDisplay
import com.kyant.expressa.catalog.ui.PageContainer
import com.kyant.expressa.catalog.ui.SectionContainer
import com.kyant.expressa.catalog.ui.Subtitle
import com.kyant.expressa.catalog.ui.Title
import com.kyant.expressa.catalog.ui.TopBar
import com.kyant.expressa.components.button.ButtonDensity
import com.kyant.expressa.components.button.ButtonShape
import com.kyant.expressa.components.iconbutton.IconButton
import com.kyant.expressa.components.iconbutton.IconButtonColors
import com.kyant.expressa.components.iconbutton.IconButtonSizes
import com.kyant.expressa.components.iconbutton.IconButtonWidth
import com.kyant.expressa.m3.shape.MaterialShapes
import com.kyant.expressa.prelude.*
import com.kyant.expressa.shape.asInterpolableRoundedPolygon
import com.kyant.expressa.ui.Icon
import com.kyant.expressa.ui.Text

@Composable
fun IconButtons() {
    PageContainer {
        TopBar(
            title = { Text("Icon buttons") }
        )

        ComponentDisplay {
            IconButton(
                {},
                sizes =
                    IconButtonSizes.large.copy(
                        containerShapeRound = MaterialShapes.Sunny.asInterpolableRoundedPolygon()
                    ),
                shape = ButtonShape.Round,
                colors =
                    IconButtonColors.tonal(
                        containerColor = primaryContainer,
                        contentColor = onPrimaryContainer
                    )
            ) {
                Icon(painterResource(R.drawable.play_arrow_24px), "Play")
            }
        }

        val iconContent = @Composable {
            Icon(painterResource(R.drawable.play_arrow_24px), "Play")
        }

        Title { Text("Configurations") }

        Subtitle { Text("1. Size") }
        SectionContainer {
            FlowRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                itemVerticalAlignment = Alignment.CenterVertically
            ) {
                IconButton({}, sizes = IconButtonSizes.extraSmall, content = iconContent)
                IconButton({}, sizes = IconButtonSizes.small, content = iconContent)
                IconButton({}, sizes = IconButtonSizes.medium, content = iconContent)
                IconButton({}, sizes = IconButtonSizes.large, content = iconContent)
                IconButton({}, sizes = IconButtonSizes.extraLarge, content = iconContent)
            }
        }

        Subtitle { Text("2. Shape") }
        SectionContainer {
            FlowRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                itemVerticalAlignment = Alignment.CenterVertically
            ) {
                IconButton({}, shape = ButtonShape.Round, content = iconContent)
                IconButton({}, shape = ButtonShape.Square, content = iconContent)
            }
        }

        Subtitle { Text("3. Color") }
        SectionContainer {
            FlowRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                itemVerticalAlignment = Alignment.CenterVertically
            ) {
                IconButton({}, colors = IconButtonColors.filled(), content = iconContent)
                IconButton({}, colors = IconButtonColors.tonal(), content = iconContent)
                IconButton({}, colors = IconButtonColors.outlined(), content = iconContent)
                IconButton({}, colors = IconButtonColors.standard(), content = iconContent)
            }
        }

        Subtitle { Text("4. Width") }
        SectionContainer {
            FlowRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                itemVerticalAlignment = Alignment.CenterVertically
            ) {
                IconButton({}, width = IconButtonWidth.Narrow, content = iconContent)
                IconButton({}, width = IconButtonWidth.Default, content = iconContent)
                IconButton({}, width = IconButtonWidth.Wide, content = iconContent)
            }
        }

        Subtitle { Text("5. Density") }
        SectionContainer {
            FlowRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                itemVerticalAlignment = Alignment.CenterVertically
            ) {
                IconButton({}, density = ButtonDensity.Standard, content = iconContent)
                IconButton({}, density = ButtonDensity.Negative1, content = iconContent)
                IconButton({}, density = ButtonDensity.Negative2, content = iconContent)
                IconButton({}, density = ButtonDensity.Negative3, content = iconContent)
            }
        }

        Title { Text("States") }

        Subtitle { Text("1. Enabled") }
        SectionContainer {
            FlowRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                itemVerticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    {}, enabled = { true }, colors = IconButtonColors.filled(),
                    content = iconContent
                )
                IconButton(
                    {}, enabled = { true }, colors = IconButtonColors.tonal(),
                    content = iconContent
                )
                IconButton(
                    {}, enabled = { true }, colors = IconButtonColors.outlined(),
                    content = iconContent
                )
                IconButton(
                    {}, enabled = { true }, colors = IconButtonColors.standard(),
                    content = iconContent
                )
            }
        }

        Subtitle { Text("2. Disabled") }
        SectionContainer {
            FlowRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                itemVerticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    {}, enabled = { false }, colors = IconButtonColors.filled(),
                    content = iconContent
                )
                IconButton(
                    {}, enabled = { false }, colors = IconButtonColors.tonal(),
                    content = iconContent
                )
                IconButton(
                    {}, enabled = { false }, colors = IconButtonColors.outlined(),
                    content = iconContent
                )
                IconButton(
                    {}, enabled = { false }, colors = IconButtonColors.standard(),
                    content = iconContent
                )
            }
        }

        Subtitle { Text("3. Hovered") }
        SectionContainer {
            val source = remember { MutableInteractionSource() }
            FlowRow(
                Modifier.pointerInput(Unit) {
                    awaitEachGesture {
                        val event = awaitPointerEvent(PointerEventPass.Initial)
                        event.changes.forEach { it.consume() }
                    }
                },
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                itemVerticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    {}, interactionSource = source, colors = IconButtonColors.filled(),
                    content = iconContent
                )
                IconButton(
                    {}, interactionSource = source, colors = IconButtonColors.tonal(),
                    content = iconContent
                )
                IconButton(
                    {}, interactionSource = source, colors = IconButtonColors.outlined(),
                    content = iconContent
                )
                IconButton(
                    {}, interactionSource = source, colors = IconButtonColors.standard(),
                    content = iconContent
                )
            }
            LaunchedEffect(source) {
                source.emit(HoverInteraction.Enter())
            }
        }

        Subtitle { Text("4. Focused") }
        SectionContainer {
            val source = remember { MutableInteractionSource() }
            FlowRow(
                Modifier.pointerInput(Unit) {
                    awaitEachGesture {
                        val event = awaitPointerEvent(PointerEventPass.Initial)
                        event.changes.forEach { it.consume() }
                    }
                },
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                itemVerticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    {}, interactionSource = source, colors = IconButtonColors.filled(),
                    content = iconContent
                )
                IconButton(
                    {}, interactionSource = source, colors = IconButtonColors.tonal(),
                    content = iconContent
                )
                IconButton(
                    {}, interactionSource = source, colors = IconButtonColors.outlined(),
                    content = iconContent
                )
                IconButton(
                    {}, interactionSource = source, colors = IconButtonColors.standard(),
                    content = iconContent
                )
            }
            LaunchedEffect(source) {
                source.emit(FocusInteraction.Focus())
            }
        }

        Subtitle { Text("5. Pressed") }
        SectionContainer {
            val source = remember { MutableInteractionSource() }
            FlowRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                itemVerticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    {}, interactionSource = source, colors = IconButtonColors.filled(),
                    content = iconContent
                )
                IconButton(
                    {}, interactionSource = source, colors = IconButtonColors.tonal(),
                    content = iconContent
                )
                IconButton(
                    {}, interactionSource = source, colors = IconButtonColors.outlined(),
                    content = iconContent
                )
                IconButton(
                    {}, interactionSource = source, colors = IconButtonColors.standard(),
                    content = iconContent
                )
            }
        }
    }
}
