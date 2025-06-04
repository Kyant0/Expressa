package com.kyant.expressa.catalog.styles

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.kyant.expressa.catalog.ui.PageContainer
import com.kyant.expressa.catalog.ui.SectionContainer
import com.kyant.expressa.catalog.ui.Subtitle
import com.kyant.expressa.catalog.ui.TopBar
import com.kyant.expressa.components.button.Button
import com.kyant.expressa.m3.ProvideColorScheme
import com.kyant.expressa.m3.color.ColorSchemeProvider
import com.kyant.expressa.m3.color.DynamicSchemeVariant
import com.kyant.expressa.prelude.*
import com.kyant.expressa.ui.LocalContentColor
import com.kyant.expressa.ui.Text

@Composable
fun ColorSchemes() {
    PageContainer {
        TopBar(
            title = { Text("Color schemes") }
        )

        var variant by remember { mutableStateOf(DynamicSchemeVariant.TonalSpot) }
        var contrast by remember { mutableFloatStateOf(0f) }

        Subtitle { Text("Variant") }
        FlowRow(
            Modifier.padding(16.dp, 8.dp),
            Arrangement.spacedBy(4.dp),
            Arrangement.spacedBy(6.dp)
        ) {
            Button({ variant = DynamicSchemeVariant.TonalSpot }) {
                Text("Tonal spot")
            }
            Button({ variant = DynamicSchemeVariant.Vibrant }) {
                Text("Vibrant")
            }
            Button({ variant = DynamicSchemeVariant.Expressive }) {
                Text("Expressive")
            }
            Button({ variant = DynamicSchemeVariant.Fidelity }) {
                Text("Fidelity")
            }
            Button({ variant = DynamicSchemeVariant.Rainbow }) {
                Text("Rainbow")
            }
            Button({ variant = DynamicSchemeVariant.FruitSalad }) {
                Text("Fruit salad")
            }
            Button({ variant = DynamicSchemeVariant.Content }) {
                Text("Content")
            }
            Button({ variant = DynamicSchemeVariant.Neutral }) {
                Text("Neutral")
            }
            Button({ variant = DynamicSchemeVariant.Monochrome }) {
                Text("Monochrome")
            }
        }

        Subtitle { Text("Contrast") }
        Row(
            Modifier.padding(16.dp, 8.dp),
            Arrangement.spacedBy(4.dp),
            Alignment.CenterVertically
        ) {
            Button(
                { contrast = -1f },
                Modifier.weight(1f)
            ) {
                Text("Low")
            }
            Button(
                { contrast = 0f },
                Modifier.weight(1f)
            ) {
                Text("Default")
            }
            Button(
                { contrast = 1f },
                Modifier.weight(1f)
            ) {
                Text("High")
            }
        }

        Subtitle { Text("Light theme") }
        ProvideColorScheme(
            ColorSchemeProvider.systemDynamic(
                isLazy = false,
                variant = variant,
                isDark = false,
                contrastLevel = contrast
            )
        ) {
            ColorPalette()
        }

        Subtitle { Text("Dark theme") }
        ProvideColorScheme(
            ColorSchemeProvider.systemDynamic(
                isLazy = false,
                variant = variant,
                isDark = true,
                contrastLevel = contrast
            )
        ) {
            ColorPalette()
        }
    }
}

@Composable
private fun ColorPalette(modifier: Modifier = Modifier) {
    SectionContainer(modifier) {
        Column(
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                ColorCell(primary, onPrimary, "Primary")
                ColorCell(onPrimary, primary, "On primary")
                ColorCell(primaryContainer, onPrimaryContainer, "Primary container")
                ColorCell(onPrimaryContainer, primaryContainer, "On primary container")
            }
            Row(
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                ColorCell(secondary, onSecondary, "Secondary")
                ColorCell(onSecondary, secondary, "On secondary")
                ColorCell(secondaryContainer, onSecondaryContainer, "Secondary container")
                ColorCell(onSecondaryContainer, secondaryContainer, "On secondary container")
            }
            Row(
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                ColorCell(tertiary, onTertiary, "Tertiary")
                ColorCell(onTertiary, tertiary, "On tertiary")
                ColorCell(tertiaryContainer, onTertiaryContainer, "Tertiary container")
                ColorCell(onTertiaryContainer, tertiaryContainer, "On tertiary container")
            }
            Row(
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                ColorCell(error, onError, "Error")
                ColorCell(onError, error, "On error")
                ColorCell(errorContainer, onErrorContainer, "Error container")
                ColorCell(onErrorContainer, errorContainer, "On error container")
            }
            Spacer(Modifier.height(4.dp))

            Row(
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                ColorCell(primaryFixed, onPrimaryFixed, "Primary fixed")
                ColorCell(onPrimaryFixed, primaryFixed, "On primary fixed")
                ColorCell(primaryFixedDim, onPrimaryFixed, "Primary fixed dim")
                ColorCell(onPrimaryFixedVariant, primaryFixedDim, "On primary fixed variant")
            }
            Row(
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                ColorCell(secondaryFixed, onSecondaryFixed, "Secondary fixed")
                ColorCell(onSecondaryFixed, secondaryFixed, "On secondary fixed")
                ColorCell(secondaryFixedDim, onSecondaryFixed, "Secondary fixed dim")
                ColorCell(onSecondaryFixedVariant, secondaryFixedDim, "On secondary fixed variant")
            }
            Row(
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                ColorCell(tertiaryFixed, onTertiaryFixed, "Tertiary fixed")
                ColorCell(onTertiaryFixed, tertiaryFixed, "On tertiary fixed")
                ColorCell(tertiaryFixedDim, onTertiaryFixed, "Tertiary fixed dim")
                ColorCell(onTertiaryFixedVariant, tertiaryFixedDim, "On tertiary fixed variant")
            }
            Spacer(Modifier.height(4.dp))

            Row(
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                ColorCell(surface, onSurface, "Surface")
                ColorCell(onSurface, surface, "On surface")
                ColorCell(surfaceVariant, onSurfaceVariant, "Surface variant")
                ColorCell(onSurfaceVariant, surfaceVariant, "On surface variant")
            }
            Row(
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                ColorCell(surfaceContainerLowest, onSurface, "SC lowest")
                ColorCell(surfaceContainerLow, onSurface, "SC low")
                ColorCell(surfaceContainer, onSurface, "Surface container")
                ColorCell(surfaceContainerHigh, onSurface, "SC high")
                ColorCell(surfaceContainerHighest, onSurface, "SC highest")
            }
            Row(
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                ColorCell(background, onBackground, "Background")
                ColorCell(onBackground, background, "On background")
                ColorCell(surfaceBright, onSurface, "Surface bright")
                ColorCell(surfaceDim, onSurface, "Surface dim")
            }
            Row(
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                ColorCell(inverseSurface, inverseOnSurface, "Inverse surface")
                ColorCell(inverseOnSurface, inverseSurface, "Inverse on surface")
                ColorCell(inversePrimary, primary, "Inverse primary")
            }
            Row(
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                ColorCell(outline, Color.White, "Outline")
                ColorCell(outlineVariant, Color.White, "Outline variant")
                ColorCell(scrim, Color.White, "Scrim")
                ColorCell(shadow, Color.White, "Shadow")
            }
        }
    }
}

@Composable
private fun RowScope.ColorCell(
    color: Color,
    labelColor: Color,
    label: String,
    modifier: Modifier = Modifier
) {
    CompositionLocalProvider(
        LocalContentColor provides labelColor
    ) {
        Box(
            modifier
                .border(
                    width = 1.dp,
                    color = labelColor,
                    shape = cornerShapeSmall
                )
                .clip(cornerShapeSmall)
                .clickable {}
                .background(color)
                .height(56.dp)
                .weight(1f)
                .padding(4.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                label,
                labelSmall.merge(textAlign = TextAlign.Center),
                maxLines = 3
            )
        }
    }
}
