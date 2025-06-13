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
import com.kyant.expressa.components.button.Button
import com.kyant.expressa.components.button.ButtonColors
import com.kyant.expressa.components.button.ButtonDensity
import com.kyant.expressa.components.button.ButtonElevation
import com.kyant.expressa.components.button.ButtonShape
import com.kyant.expressa.components.button.ButtonSizes
import com.kyant.expressa.m3.elevation.Elevation
import com.kyant.expressa.prelude.*
import com.kyant.expressa.shape.RoundedRectangle
import com.kyant.expressa.ui.Icon
import com.kyant.expressa.ui.Text

@Composable
fun Buttons() {
    PageContainer {
        TopBar(
            title = { Text("Buttons") }
        )

        ComponentDisplay {
            Button(
                {},
                sizes = ButtonSizes.large.copy(shapePressed = RoundedRectangle(16.dp)),
                shape = ButtonShape.Round,
                colors =
                    ButtonColors.tonal(
                        containerColor = primaryContainer,
                        contentColor = onPrimaryContainer
                    ).copy(
                        elevation =
                            ButtonElevation(
                                pressed = Elevation.Level2
                            )
                    ),
                icon = { Icon(painterResource(R.drawable.play_arrow_24px)) }
            ) { Text("Play") }
        }

        Title { Text("Configurations") }

        Subtitle { Text("1. Size") }
        SectionContainer {
            FlowRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                itemVerticalAlignment = Alignment.CenterVertically
            ) {
                Button({}, sizes = ButtonSizes.extraSmall) { Text("Extra small") }
                Button({}, sizes = ButtonSizes.small) { Text("Small") }
                Button({}, sizes = ButtonSizes.medium) { Text("Medium") }
                Button({}, sizes = ButtonSizes.large) { Text("Large") }
                Button({}, sizes = ButtonSizes.extraLarge) { Text("Extra large") }
            }
        }

        Subtitle { Text("2. Shape") }
        SectionContainer {
            FlowRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                itemVerticalAlignment = Alignment.CenterVertically
            ) {
                Button({}, shape = ButtonShape.Round) { Text("Round") }
                Button({}, shape = ButtonShape.Square) { Text("Square") }
            }
        }

        Subtitle { Text("3. Color") }
        SectionContainer {
            FlowRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                itemVerticalAlignment = Alignment.CenterVertically
            ) {
                Button({}, colors = ButtonColors.elevated()) { Text("Elevated") }
                Button({}, colors = ButtonColors.filled()) { Text("Filled") }
                Button({}, colors = ButtonColors.tonal()) { Text("Tonal") }
                Button({}, colors = ButtonColors.outlined()) { Text("Outlined") }
                Button({}, colors = ButtonColors.text()) { Text("Text") }
            }
        }

        Subtitle { Text("4. Density") }
        SectionContainer {
            FlowRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                itemVerticalAlignment = Alignment.CenterVertically
            ) {
                Button({}, density = ButtonDensity.Standard) { Text("Density 0") }
                Button({}, density = ButtonDensity.Negative1) { Text("Density -1") }
                Button({}, density = ButtonDensity.Negative2) { Text("Density -2") }
                Button({}, density = ButtonDensity.Negative3) { Text("Density -3") }
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
                Button({}, enabled = { true }, colors = ButtonColors.elevated()) { Text("Elevated") }
                Button({}, enabled = { true }, colors = ButtonColors.filled()) { Text("Filled") }
                Button({}, enabled = { true }, colors = ButtonColors.tonal()) { Text("Tonal") }
                Button({}, enabled = { true }, colors = ButtonColors.outlined()) { Text("Outlined") }
                Button({}, enabled = { true }, colors = ButtonColors.text()) { Text("Text") }
            }
        }

        Subtitle { Text("2. Disabled") }
        SectionContainer {
            FlowRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                itemVerticalAlignment = Alignment.CenterVertically
            ) {
                Button({}, enabled = { false }, colors = ButtonColors.elevated()) { Text("Elevated") }
                Button({}, enabled = { false }, colors = ButtonColors.filled()) { Text("Filled") }
                Button({}, enabled = { false }, colors = ButtonColors.tonal()) { Text("Tonal") }
                Button({}, enabled = { false }, colors = ButtonColors.outlined()) { Text("Outlined") }
                Button({}, enabled = { false }, colors = ButtonColors.text()) { Text("Text") }
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
                Button({}, interactionSource = source, colors = ButtonColors.elevated()) {
                    Text("Elevated")
                }
                Button({}, interactionSource = source, colors = ButtonColors.filled()) {
                    Text("Filled")
                }
                Button({}, interactionSource = source, colors = ButtonColors.tonal()) {
                    Text("Tonal")
                }
                Button({}, interactionSource = source, colors = ButtonColors.outlined()) {
                    Text("Outlined")
                }
                Button({}, interactionSource = source, colors = ButtonColors.text()) {
                    Text("Text")
                }
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
                Button({}, interactionSource = source, colors = ButtonColors.elevated()) {
                    Text("Elevated")
                }
                Button({}, interactionSource = source, colors = ButtonColors.filled()) {
                    Text("Filled")
                }
                Button({}, interactionSource = source, colors = ButtonColors.tonal()) {
                    Text("Tonal")
                }
                Button({}, interactionSource = source, colors = ButtonColors.outlined()) {
                    Text("Outlined")
                }
                Button({}, interactionSource = source, colors = ButtonColors.text()) {
                    Text("Text")
                }
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
                Button({}, interactionSource = source, colors = ButtonColors.elevated()) {
                    Text("Elevated")
                }
                Button({}, interactionSource = source, colors = ButtonColors.filled()) {
                    Text("Filled")
                }
                Button({}, interactionSource = source, colors = ButtonColors.tonal()) {
                    Text("Tonal")
                }
                Button({}, interactionSource = source, colors = ButtonColors.outlined()) {
                    Text("Outlined")
                }
                Button({}, interactionSource = source, colors = ButtonColors.text()) {
                    Text("Text")
                }
            }
        }
    }
}
