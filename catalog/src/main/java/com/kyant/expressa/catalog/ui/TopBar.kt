package com.kyant.expressa.catalog.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.kyant.expressa.prelude.*
import com.kyant.expressa.ui.ProvideTextStyle

@Composable
fun TopBar(
    title: @Composable () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier
            .paddingFromBaseline(top = 120.dp)
            .padding(24.dp, 56.dp, 24.dp, 16.dp)
    ) {
        ProvideTextStyle(
            displaySmall,
            content = title
        )
    }
}
