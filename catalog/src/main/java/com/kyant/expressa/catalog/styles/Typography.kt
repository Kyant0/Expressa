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
import com.kyant.expressa.component.text.Text
import com.kyant.expressa.prelude.*

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
                TypographyItem(displayLarge, "Display", "L")
                TypographyItem(displayMedium, "Display", "M")
                TypographyItem(displaySmall, "Display", "S")
                TypographyItem(headlineLarge, "Headline", "L")
                TypographyItem(headlineMedium, "Headline", "M")
                TypographyItem(headlineSmall, "Headline", "S")
                TypographyItem(titleLarge, "Title", "L")
                TypographyItem(titleMedium, "Title", "M")
                TypographyItem(titleSmall, "Title", "S")
                TypographyItem(bodyLarge, "Body", "L")
                TypographyItem(bodyMedium, "Body", "M")
                TypographyItem(bodySmall, "Body", "S")
                TypographyItem(labelLarge, "Label", "L")
                TypographyItem(labelMedium, "Label", "M")
                TypographyItem(labelSmall, "Label", "S")
            }
        }

        Subtitle { Text("Emphasized") }
        SectionContainer {
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                TypographyItem(displayLargeEmphasized, "Display", "L")
                TypographyItem(displayMediumEmphasized, "Display", "M")
                TypographyItem(displaySmallEmphasized, "Display", "S")
                TypographyItem(headlineLargeEmphasized, "Headline", "L")
                TypographyItem(headlineMediumEmphasized, "Headline", "M")
                TypographyItem(headlineSmallEmphasized, "Headline", "S")
                TypographyItem(titleLargeEmphasized, "Title", "L")
                TypographyItem(titleMediumEmphasized, "Title", "M")
                TypographyItem(titleSmallEmphasized, "Title", "S")
                TypographyItem(bodyLargeEmphasized, "Body", "L")
                TypographyItem(bodyMediumEmphasized, "Body", "M")
                TypographyItem(bodySmallEmphasized, "Body", "S")
                TypographyItem(labelLargeEmphasized, "Label", "L")
                TypographyItem(labelMediumEmphasized, "Label", "M")
                TypographyItem(labelSmallEmphasized, "Label", "S")
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
            .clip(cornerShapeSmall)
            .clickable {}
            .border(
                width = 2.dp,
                color = primaryFixedDim,
                shape = cornerShapeSmall
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
                .clip(cornerShapeExtraSmall)
                .background(primaryContainer)
                .size(20.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                scale,
                labelLarge
            )
        }
    }
}
