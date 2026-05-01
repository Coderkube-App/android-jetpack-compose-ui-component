package com.compose.uicomponent.core.designsystem.layouts

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun <T> AdaptiveGrid(
    items: List<T>,
    windowSize: WindowSize,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp),
    itemContent: @Composable (T) -> Unit
) {
    val columns = when (windowSize.width) {
        WindowSizeClass.Compact -> 1
        WindowSizeClass.Medium -> 2
        WindowSizeClass.Expanded -> 3
    }

    LazyVerticalGrid(
        columns = GridCells.Fixed(columns),
        modifier = modifier.fillMaxSize(),
        contentPadding = contentPadding,
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(items) { item ->
            itemContent(item)
        }
    }
}

@Composable
fun MasterDetailLayout(
    windowSize: WindowSize,
    masterContent: @Composable () -> Unit,
    detailContent: @Composable () -> Unit,
    modifier: Modifier = Modifier
) {
    val isExpanded = windowSize.width == WindowSizeClass.Expanded

    if (isExpanded) {
        Row(modifier = modifier.fillMaxSize()) {
            Box(modifier = Modifier.weight(1f)) {
                masterContent()
            }
            Box(modifier = Modifier.weight(1.5f)) {
                detailContent()
            }
        }
    } else {
        Box(modifier = modifier.fillMaxSize()) {
            masterContent()
            // In a real app, you'd navigate to detail, but for this layout component,
            // we just show master when not expanded.
        }
    }
}
