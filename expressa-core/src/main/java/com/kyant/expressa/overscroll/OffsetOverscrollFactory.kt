package com.kyant.expressa.overscroll

import androidx.compose.foundation.OverscrollEffect
import androidx.compose.foundation.OverscrollFactory
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import kotlinx.coroutines.CoroutineScope

@Composable
fun rememberOffsetOverscrollFactory(): OffsetOverscrollFactory {
    val scope = rememberCoroutineScope()
    return remember(scope) {
        OffsetOverscrollFactory(scope)
    }
}

@Immutable
class OffsetOverscrollFactory internal constructor(
    private val scope: CoroutineScope
) : OverscrollFactory {

    override fun createOverscrollEffect(): OverscrollEffect {
        return OffsetOverscrollEffect(scope)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as OffsetOverscrollFactory

        return scope == other.scope
    }

    override fun hashCode(): Int {
        return scope.hashCode()
    }
}
