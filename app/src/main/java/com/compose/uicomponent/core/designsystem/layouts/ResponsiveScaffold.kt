package com.compose.uicomponent.core.designsystem.layouts

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.compose.uicomponent.core.designsystem.components.AppBottomNavigation
import com.compose.uicomponent.core.designsystem.components.NavigationItem

@Composable
fun ResponsiveScaffold(
    windowSize: WindowSize,
    topBar: @Composable () -> Unit = {},
    bottomBarItems: List<NavigationItem> = emptyList(),
    currentRoute: String? = null,
    onNavigate: (NavigationItem) -> Unit = {},
    content: @Composable (PaddingValues) -> Unit
) {
    val isTablet = windowSize.width != WindowSizeClass.Compact

    Scaffold(
        topBar = topBar,
        bottomBar = {
            if (!isTablet && bottomBarItems.isNotEmpty()) {
                AppBottomNavigation(
                    items = bottomBarItems,
                    currentRoute = currentRoute,
                    onItemClick = onNavigate
                )
            }
        }
    ) { innerPadding ->
        Row(modifier = Modifier.fillMaxSize()) {
            if (isTablet && bottomBarItems.isNotEmpty()) {
                NavigationRail(
                    modifier = Modifier.fillMaxHeight(),
                    containerColor = androidx.compose.material3.MaterialTheme.colorScheme.surface,
                    contentColor = androidx.compose.material3.MaterialTheme.colorScheme.onSurface
                ) {
                    bottomBarItems.forEach { item ->
                        NavigationRailItem(
                            selected = currentRoute == item.route,
                            onClick = { onNavigate(item) },
                            icon = { androidx.compose.material3.Icon(item.icon, contentDescription = item.title) },
                            label = { Text(item.title) }
                        )
                    }
                }
            }
            Box(modifier = Modifier.weight(1f)) {
                content(innerPadding)
            }
        }
    }
}
