package com.kyant.expressa.catalog

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.kyant.expressa.catalog.ui.PageContainer
import com.kyant.expressa.catalog.ui.Subtitle
import com.kyant.expressa.catalog.ui.TopBar
import com.kyant.expressa.m3.shape.CornerShape
import com.kyant.expressa.prelude.*
import com.kyant.expressa.ui.ProvideTextStyle
import com.kyant.expressa.ui.Text

@Composable
fun Catalog(backStack: MutableList<Any>) {
    PageContainer {
        TopBar(
            title = { Text("Expressa") }
        )

        Subtitle { Text("Styles") }
        Category {
            CategoryItem({ backStack.add(Styles.ColorSchemes) }) { Text("Color schemes") }
            CategoryItem({ backStack.add(Styles.Elevation) }) { Text("Elevation") }
            CategoryItem({ backStack.add(Styles.MotionSchemes) }) { Text("Motion schemes") }
            CategoryItem({ backStack.add(Styles.Typography) }) { Text("Typography") }
        }

        Subtitle { Text("Components") }
        Category {
            CategoryItem({ backStack.add(Components.Buttons) }) { Text("Buttons") }
            CategoryItem({ backStack.add(Components.IconButtons) }) { Text("Icon buttons") }
        }
    }
}

@Composable
private fun Category(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Column(
        modifier
            .padding(16.dp, 8.dp)
            .clip(CornerShape.extraLarge)
            .fillMaxWidth(),
        Arrangement.spacedBy(2.dp)
    ) {
        content()
    }
}

@Composable
private fun CategoryItem(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Row(
        modifier
            .clickable(onClick = onClick)
            .clip(CornerShape.extraSmall)
            .background(surfaceBright)
            .height(56.dp)
            .fillMaxWidth()
            .padding(horizontal = 20.dp),
        Arrangement.spacedBy(12.dp),
        Alignment.CenterVertically
    ) {
        ProvideTextStyle(
            bodyLarge,
            content = content
        )
    }
}
