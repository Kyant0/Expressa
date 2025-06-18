package com.kyant.expressa.catalog.styles

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.kyant.expressa.catalog.ui.PageContainer
import com.kyant.expressa.catalog.ui.SectionContainer
import com.kyant.expressa.catalog.ui.TopBar
import com.kyant.expressa.m3.elevation.Elevation
import com.kyant.expressa.m3.shape.CornerShape
import com.kyant.expressa.prelude.*
import com.kyant.expressa.ui.Text

@Composable
fun Elevation() {
    PageContainer {
        TopBar(
            title = { Text("Elevation") }
        )

        SectionContainer {
            FlowRow(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterHorizontally),
                verticalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                ElevationItem(Elevation.Level0, "0")
                ElevationItem(Elevation.Level1, "+1")
                ElevationItem(Elevation.Level2, "+2")
                ElevationItem(Elevation.Level3, "+3")
                ElevationItem(Elevation.Level4, "+4")
                ElevationItem(Elevation.Level5, "+5")
            }
        }
    }
}

@Composable
private fun ElevationItem(
    elevation: Elevation,
    label: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier.width(IntrinsicSize.Min),
        Arrangement.spacedBy(8.dp),
        Alignment.CenterHorizontally
    ) {
        Box(
            Modifier
                .shadow(
                    elevation = elevation.value,
                    shape = CornerShape.small,
                    ambientColor = shadow,
                    spotColor = shadow
                )
                .background(surface)
                .size(64.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                "${elevation.value.value.toInt()}dp",
                labelLarge
            )
        }
        Text(
            label,
            labelLarge.merge(textAlign = TextAlign.Center)
        )
    }
}
