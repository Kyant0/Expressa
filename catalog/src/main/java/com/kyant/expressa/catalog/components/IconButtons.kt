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
import com.kyant.expressa.shape.toOmniShape
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
                iconButtonSizes =
                    IconButtonSizes.large().copy(
                        containerShapeRound = MaterialShapes.Sunny.toOmniShape()
                    ),
                buttonShape = ButtonShape.Round,
                iconButtonColors =
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
                IconButton({}, iconButtonSizes = IconButtonSizes.extraSmall(), content = iconContent)
                IconButton({}, iconButtonSizes = IconButtonSizes.small(), content = iconContent)
                IconButton({}, iconButtonSizes = IconButtonSizes.medium(), content = iconContent)
                IconButton({}, iconButtonSizes = IconButtonSizes.large(), content = iconContent)
                IconButton({}, iconButtonSizes = IconButtonSizes.extraLarge(), content = iconContent)
            }
        }

        Subtitle { Text("2. Shape") }
        SectionContainer {
            FlowRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                itemVerticalAlignment = Alignment.CenterVertically
            ) {
                IconButton({}, buttonShape = ButtonShape.Round, content = iconContent)
                IconButton({}, buttonShape = ButtonShape.Square, content = iconContent)
            }
        }

        Subtitle { Text("3. Color") }
        SectionContainer {
            FlowRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                itemVerticalAlignment = Alignment.CenterVertically
            ) {
                IconButton({}, iconButtonColors = IconButtonColors.filled(), content = iconContent)
                IconButton({}, iconButtonColors = IconButtonColors.tonal(), content = iconContent)
                IconButton({}, iconButtonColors = IconButtonColors.outlined(), content = iconContent)
                IconButton({}, iconButtonColors = IconButtonColors.standard(), content = iconContent)
            }
        }

        Subtitle { Text("4. Width") }
        SectionContainer {
            FlowRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                itemVerticalAlignment = Alignment.CenterVertically
            ) {
                IconButton({}, iconButtonWidth = IconButtonWidth.Narrow, content = iconContent)
                IconButton({}, iconButtonWidth = IconButtonWidth.Default, content = iconContent)
                IconButton({}, iconButtonWidth = IconButtonWidth.Wide, content = iconContent)
            }
        }

        Subtitle { Text("5. Density") }
        SectionContainer {
            FlowRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                itemVerticalAlignment = Alignment.CenterVertically
            ) {
                IconButton({}, buttonDensity = ButtonDensity.Standard, content = iconContent)
                IconButton({}, buttonDensity = ButtonDensity.Negative1, content = iconContent)
                IconButton({}, buttonDensity = ButtonDensity.Negative2, content = iconContent)
                IconButton({}, buttonDensity = ButtonDensity.Negative3, content = iconContent)
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
                    {}, enabled = { true }, iconButtonColors = IconButtonColors.filled(),
                    content = iconContent
                )
                IconButton(
                    {}, enabled = { true }, iconButtonColors = IconButtonColors.tonal(),
                    content = iconContent
                )
                IconButton(
                    {}, enabled = { true }, iconButtonColors = IconButtonColors.outlined(),
                    content = iconContent
                )
                IconButton(
                    {}, enabled = { true }, iconButtonColors = IconButtonColors.standard(),
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
                    {}, enabled = { false }, iconButtonColors = IconButtonColors.filled(),
                    content = iconContent
                )
                IconButton(
                    {}, enabled = { false }, iconButtonColors = IconButtonColors.tonal(),
                    content = iconContent
                )
                IconButton(
                    {}, enabled = { false }, iconButtonColors = IconButtonColors.outlined(),
                    content = iconContent
                )
                IconButton(
                    {}, enabled = { false }, iconButtonColors = IconButtonColors.standard(),
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
                    {}, interactionSource = source, iconButtonColors = IconButtonColors.filled(),
                    content = iconContent
                )
                IconButton(
                    {}, interactionSource = source, iconButtonColors = IconButtonColors.tonal(),
                    content = iconContent
                )
                IconButton(
                    {}, interactionSource = source, iconButtonColors = IconButtonColors.outlined(),
                    content = iconContent
                )
                IconButton(
                    {}, interactionSource = source, iconButtonColors = IconButtonColors.standard(),
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
                    {}, interactionSource = source, iconButtonColors = IconButtonColors.filled(),
                    content = iconContent
                )
                IconButton(
                    {}, interactionSource = source, iconButtonColors = IconButtonColors.tonal(),
                    content = iconContent
                )
                IconButton(
                    {}, interactionSource = source, iconButtonColors = IconButtonColors.outlined(),
                    content = iconContent
                )
                IconButton(
                    {}, interactionSource = source, iconButtonColors = IconButtonColors.standard(),
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
                    {}, interactionSource = source, iconButtonColors = IconButtonColors.filled(),
                    content = iconContent
                )
                IconButton(
                    {}, interactionSource = source, iconButtonColors = IconButtonColors.tonal(),
                    content = iconContent
                )
                IconButton(
                    {}, interactionSource = source, iconButtonColors = IconButtonColors.outlined(),
                    content = iconContent
                )
                IconButton(
                    {}, interactionSource = source, iconButtonColors = IconButtonColors.standard(),
                    content = iconContent
                )
            }
        }
    }
}
