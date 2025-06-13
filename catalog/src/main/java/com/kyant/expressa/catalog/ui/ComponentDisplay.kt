package com.kyant.expressa.catalog.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.kyant.expressa.m3.shape.CornerShape
import com.kyant.expressa.prelude.*

@Composable
fun ComponentDisplay(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Box(
        modifier
            .padding(16.dp, 8.dp)
            .clip(CornerShape.extraLarge)
            .background(surfaceBright)
            .heightIn(min = 160.dp)
            .fillMaxWidth()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        content()
    }
}
