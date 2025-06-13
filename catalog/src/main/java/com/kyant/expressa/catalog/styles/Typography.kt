package com.kyant.expressa.catalog.styles

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.kyant.expressa.catalog.ui.PageContainer
import com.kyant.expressa.catalog.ui.SectionContainer
import com.kyant.expressa.catalog.ui.Subtitle
import com.kyant.expressa.catalog.ui.TopBar
import com.kyant.expressa.m3.shape.CornerShape
import com.kyant.expressa.m3.typography.Typography
import com.kyant.expressa.prelude.*
import com.kyant.expressa.ui.Text

@Composable
fun Typography() {
    PageContainer {
        TopBar(
            title = { Text("Typography") }
        )

        Subtitle { Text("Default") }
        SectionContainer {
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                TypographyItem(Typography.displayLarge, "Display", "L")
                TypographyItem(Typography.displayMedium, "Display", "M")
                TypographyItem(Typography.displaySmall, "Display", "S")
                TypographyItem(Typography.headlineLarge, "Headline", "L")
                TypographyItem(Typography.headlineMedium, "Headline", "M")
                TypographyItem(Typography.headlineSmall, "Headline", "S")
                TypographyItem(Typography.titleLarge, "Title", "L")
                TypographyItem(Typography.titleMedium, "Title", "M")
                TypographyItem(Typography.titleSmall, "Title", "S")
                TypographyItem(Typography.bodyLarge, "Body", "L")
                TypographyItem(Typography.bodyMedium, "Body", "M")
                TypographyItem(Typography.bodySmall, "Body", "S")
                TypographyItem(Typography.labelLarge, "Label", "L")
                TypographyItem(Typography.labelMedium, "Label", "M")
                TypographyItem(Typography.labelSmall, "Label", "S")
            }
        }

        Subtitle { Text("Emphasized") }
        SectionContainer {
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                TypographyItem(Typography.displayLargeEmphasized, "Display", "L")
                TypographyItem(Typography.displayMediumEmphasized, "Display", "M")
                TypographyItem(Typography.displaySmallEmphasized, "Display", "S")
                TypographyItem(Typography.headlineLargeEmphasized, "Headline", "L")
                TypographyItem(Typography.headlineMediumEmphasized, "Headline", "M")
                TypographyItem(Typography.headlineSmallEmphasized, "Headline", "S")
                TypographyItem(Typography.titleLargeEmphasized, "Title", "L")
                TypographyItem(Typography.titleMediumEmphasized, "Title", "M")
                TypographyItem(Typography.titleSmallEmphasized, "Title", "S")
                TypographyItem(Typography.bodyLargeEmphasized, "Body", "L")
                TypographyItem(Typography.bodyMediumEmphasized, "Body", "M")
                TypographyItem(Typography.bodySmallEmphasized, "Body", "S")
                TypographyItem(Typography.labelLargeEmphasized, "Label", "L")
                TypographyItem(Typography.labelMediumEmphasized, "Label", "M")
                TypographyItem(Typography.labelSmallEmphasized, "Label", "S")
            }
        }
    }
}

@Composable
private fun TypographyItem(
    style: TextStyle,
    label: String,
    scale: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier
            .clip(CornerShape.small)
            .clickable {}
            .border(
                width = 2.dp,
                color = primaryFixedDim,
                shape = CornerShape.small
            )
            .fillMaxWidth()
            .padding(16.dp),
        Arrangement.spacedBy(16.dp),
        Alignment.CenterVertically
    ) {
        Text(
            label,
            style,
            maxLines = 1
        )
        Box(
            Modifier
                .clip(CornerShape.extraSmall)
                .background(primaryContainer)
                .size(20.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                scale,
                Typography.labelLarge
            )
        }
    }
}
