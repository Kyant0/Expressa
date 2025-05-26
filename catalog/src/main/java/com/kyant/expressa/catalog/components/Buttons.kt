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
import com.kyant.expressa.component.button.Button
import com.kyant.expressa.component.button.ButtonColors
import com.kyant.expressa.component.button.ButtonDensity
import com.kyant.expressa.component.button.ButtonElevation
import com.kyant.expressa.component.button.ButtonShape
import com.kyant.expressa.component.button.ButtonSizes
import com.kyant.expressa.component.icon.Icon
import com.kyant.expressa.component.text.Text
import com.kyant.expressa.m3.elevation.Elevation
import com.kyant.expressa.prelude.*
import com.kyant.expressa.shape.RoundedCornerOmniShape

@Composable
fun Buttons() {
    PageContainer {
        TopBar(
            title = { Text("Buttons") }
        )

        ComponentDisplay {
            Button(
                {},
                buttonSizes =
                    ButtonSizes.large().copy(
                        shapePressed = RoundedCornerOmniShape(16.dp, 0.48f)
                    ),
                buttonShape = ButtonShape.Round,
                buttonColors =
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
                Button({}, buttonSizes = ButtonSizes.extraSmall()) { Text("Extra small") }
                Button({}, buttonSizes = ButtonSizes.small()) { Text("Small") }
                Button({}, buttonSizes = ButtonSizes.medium()) { Text("Medium") }
                Button({}, buttonSizes = ButtonSizes.large()) { Text("Large") }
                Button({}, buttonSizes = ButtonSizes.extraLarge()) { Text("Extra large") }
            }
        }

        Subtitle { Text("2. Shape") }
        SectionContainer {
            FlowRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                itemVerticalAlignment = Alignment.CenterVertically
            ) {
                Button({}, buttonShape = ButtonShape.Round) { Text("Round") }
                Button({}, buttonShape = ButtonShape.Square) { Text("Square") }
            }
        }

        Subtitle { Text("3. Color") }
        SectionContainer {
            FlowRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                itemVerticalAlignment = Alignment.CenterVertically
            ) {
                Button({}, buttonColors = ButtonColors.elevated()) { Text("Elevated") }
                Button({}, buttonColors = ButtonColors.filled()) { Text("Filled") }
                Button({}, buttonColors = ButtonColors.tonal()) { Text("Tonal") }
                Button({}, buttonColors = ButtonColors.outlined()) { Text("Outlined") }
                Button({}, buttonColors = ButtonColors.text()) { Text("Text") }
            }
        }

        Subtitle { Text("4. Density") }
        SectionContainer {
            FlowRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                itemVerticalAlignment = Alignment.CenterVertically
            ) {
                Button({}, buttonDensity = ButtonDensity.Standard) { Text("Density 0") }
                Button({}, buttonDensity = ButtonDensity.Negative1) { Text("Density -1") }
                Button({}, buttonDensity = ButtonDensity.Negative2) { Text("Density -2") }
                Button({}, buttonDensity = ButtonDensity.Negative3) { Text("Density -3") }
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
                Button({}, enabled = { true }, buttonColors = ButtonColors.elevated()) { Text("Elevated") }
                Button({}, enabled = { true }, buttonColors = ButtonColors.filled()) { Text("Filled") }
                Button({}, enabled = { true }, buttonColors = ButtonColors.tonal()) { Text("Tonal") }
                Button({}, enabled = { true }, buttonColors = ButtonColors.outlined()) { Text("Outlined") }
                Button({}, enabled = { true }, buttonColors = ButtonColors.text()) { Text("Text") }
            }
        }

        Subtitle { Text("2. Disabled") }
        SectionContainer {
            FlowRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                itemVerticalAlignment = Alignment.CenterVertically
            ) {
                Button({}, enabled = { false }, buttonColors = ButtonColors.elevated()) { Text("Elevated") }
                Button({}, enabled = { false }, buttonColors = ButtonColors.filled()) { Text("Filled") }
                Button({}, enabled = { false }, buttonColors = ButtonColors.tonal()) { Text("Tonal") }
                Button({}, enabled = { false }, buttonColors = ButtonColors.outlined()) { Text("Outlined") }
                Button({}, enabled = { false }, buttonColors = ButtonColors.text()) { Text("Text") }
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
                Button({}, interactionSource = source, buttonColors = ButtonColors.elevated()) {
                    Text("Elevated")
                }
                Button({}, interactionSource = source, buttonColors = ButtonColors.filled()) {
                    Text("Filled")
                }
                Button({}, interactionSource = source, buttonColors = ButtonColors.tonal()) {
                    Text("Tonal")
                }
                Button({}, interactionSource = source, buttonColors = ButtonColors.outlined()) {
                    Text("Outlined")
                }
                Button({}, interactionSource = source, buttonColors = ButtonColors.text()) {
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
                Button({}, interactionSource = source, buttonColors = ButtonColors.elevated()) {
                    Text("Elevated")
                }
                Button({}, interactionSource = source, buttonColors = ButtonColors.filled()) {
                    Text("Filled")
                }
                Button({}, interactionSource = source, buttonColors = ButtonColors.tonal()) {
                    Text("Tonal")
                }
                Button({}, interactionSource = source, buttonColors = ButtonColors.outlined()) {
                    Text("Outlined")
                }
                Button({}, interactionSource = source, buttonColors = ButtonColors.text()) {
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
                Button({}, interactionSource = source, buttonColors = ButtonColors.elevated()) {
                    Text("Elevated")
                }
                Button({}, interactionSource = source, buttonColors = ButtonColors.filled()) {
                    Text("Filled")
                }
                Button({}, interactionSource = source, buttonColors = ButtonColors.tonal()) {
                    Text("Tonal")
                }
                Button({}, interactionSource = source, buttonColors = ButtonColors.outlined()) {
                    Text("Outlined")
                }
                Button({}, interactionSource = source, buttonColors = ButtonColors.text()) {
                    Text("Text")
                }
            }
        }
    }
}
