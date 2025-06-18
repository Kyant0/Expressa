package com.kyant.expressa.catalog.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.kyant.expressa.prelude.*
import com.kyant.expressa.ui.LocalContentColor
import com.kyant.expressa.ui.ProvideTextStyle

@Composable
fun Subtitle(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Box(
        modifier.padding(24.dp, 16.dp, 24.dp, 4.dp)
    ) {
        CompositionLocalProvider(
            LocalContentColor provides primary
        ) {
            ProvideTextStyle(
                labelLarge,
                content = content
            )
        }
    }
}
