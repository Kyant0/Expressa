package com.kyant.expressa.catalog.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.kyant.expressa.m3.typography.Typography
import com.kyant.expressa.ui.ProvideTextStyle

@Composable
fun Title(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Box(
        modifier.padding(24.dp, 48.dp, 24.dp, 8.dp)
    ) {
        ProvideTextStyle(
            Typography.titleLargeEmphasized,
            content = content
        )
    }
}
