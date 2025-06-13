package com.kyant.expressa.catalog.styles

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.graphics.shapes.RoundedPolygon
import com.kyant.expressa.catalog.ui.PageContainer
import com.kyant.expressa.catalog.ui.SectionContainer
import com.kyant.expressa.catalog.ui.TopBar
import com.kyant.expressa.m3.shape.MaterialShapes
import com.kyant.expressa.m3.typography.Typography
import com.kyant.expressa.prelude.*
import com.kyant.expressa.shape.asInterpolableRoundedPolygon
import com.kyant.expressa.ui.Text

@Composable
fun Shapes() {
    PageContainer {
        TopBar(
            title = { Text("Shapes") }
        )

        SectionContainer {
            FlowRow(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterHorizontally),
                verticalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                ShapeItem(MaterialShapes.Circle, "Circle")
                ShapeItem(MaterialShapes.Square, "Square")
                ShapeItem(MaterialShapes.Slanted, "Slanted")
                ShapeItem(MaterialShapes.Arch, "Arch")
                ShapeItem(MaterialShapes.SemiCircle, "Semicircle")
                ShapeItem(MaterialShapes.Oval, "Oval")
                ShapeItem(MaterialShapes.Pill, "Pill")
                ShapeItem(MaterialShapes.Triangle, "Triangle")
                ShapeItem(MaterialShapes.Arrow, "Arrow")
                ShapeItem(MaterialShapes.Fan, "Fan")
                ShapeItem(MaterialShapes.Diamond, "Diamond")
                ShapeItem(MaterialShapes.Cookie4Sided, "4-sided cookie")
                ShapeItem(MaterialShapes.Cookie6Sided, "6-sided cookie")
                ShapeItem(MaterialShapes.Cookie7Sided, "7-sided cookie")
                ShapeItem(MaterialShapes.Cookie9Sided, "9-sided cookie")
                ShapeItem(MaterialShapes.Cookie12Sided, "12-sided cookie")
                ShapeItem(MaterialShapes.Clover4Leaf, "4-leaf clover")
                ShapeItem(MaterialShapes.Clover8Leaf, "8-leaf clover")
                ShapeItem(MaterialShapes.Burst, "Burst")
                ShapeItem(MaterialShapes.SoftBurst, "Soft burst")
                ShapeItem(MaterialShapes.Boom, "Boom")
                ShapeItem(MaterialShapes.SoftBoom, "Soft boom")
                ShapeItem(MaterialShapes.Flower, "Flower")
                ShapeItem(MaterialShapes.Puffy, "Puffy")
                ShapeItem(MaterialShapes.PuffyDiamond, "Puffy diamond")
                ShapeItem(MaterialShapes.Ghostish, "Ghost-ish")
                ShapeItem(MaterialShapes.PixelCircle, "Pixel circle")
                ShapeItem(MaterialShapes.PixelTriangle, "Pixel triangle")
                ShapeItem(MaterialShapes.Bun, "Bun")
                ShapeItem(MaterialShapes.Heart, "Heart")
            }
        }
    }
}

@Composable
private fun ShapeItem(
    shape: RoundedPolygon,
    label: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier.width(56.dp),
        Arrangement.spacedBy(8.dp),
        Alignment.CenterHorizontally
    ) {
        Box(
            modifier
                .clip(shape.asInterpolableRoundedPolygon())
                .background(primary)
                .size(56.dp)
        )
        Text(
            label,
            Typography.labelLarge.merge(textAlign = TextAlign.Center)
        )
    }
}
