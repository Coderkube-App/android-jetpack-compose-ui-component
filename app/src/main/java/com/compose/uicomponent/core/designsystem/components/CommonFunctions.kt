package com.compose.uicomponent.core.designsystem.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.compose.uicomponent.core.designsystem.theme.AppTheme

/**
 * A common wrapper for all screens to ensure consistent padding and layout.
 */
@Composable
fun AppBaseScreen(
    modifier: Modifier = Modifier,
    usePadding: Boolean = true,
    content: @Composable BoxScope.() -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .then(
                if (usePadding) Modifier.padding(AppTheme.spacing.medium)
                else Modifier
            )
    ) {
        content()
    }
}

/**
 * Common state handler function for screens.
 */
@Composable
fun <T> AppStateHandler(
    loading: Boolean,
    error: String? = null,
    data: T? = null,
    onRetry: () -> Unit = {},
    emptyMessage: String = "No data available",
    content: @Composable (T) -> Unit
) {
    when {
        loading -> AppLoadingState()
        error != null -> AppErrorState(message = error, onRetry = onRetry)
        data == null || (data is List<*> && data.isEmpty()) -> AppEmptyState(message = emptyMessage)
        else -> content(data)
    }
}
