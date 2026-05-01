package com.compose.uicomponent.features.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.compose.uicomponent.core.designsystem.components.AppBanner
import com.compose.uicomponent.core.designsystem.components.AppBaseScreen
import com.compose.uicomponent.core.designsystem.components.AppListItem
import com.compose.uicomponent.core.designsystem.components.AppStatsCard
import com.compose.uicomponent.core.designsystem.components.AppSubTitle
import com.compose.uicomponent.core.designsystem.layouts.AdaptiveGrid
import com.compose.uicomponent.core.designsystem.layouts.WindowSizeClass
import com.compose.uicomponent.core.designsystem.layouts.rememberWindowSize
import com.compose.uicomponent.core.designsystem.theme.AppTheme

@Composable
fun HomeScreen() {
    val windowSize = rememberWindowSize()
    
    AppBaseScreen {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(AppTheme.spacing.large)
        ) {
            item {
                AppBanner(
                    title = "Hello, John!",
                    subtitle = "Here is what's happening with your projects today."
                )
            }

            item {
                AppSubTitle(text = "Quick Statistics")
                Spacer(modifier = Modifier.height(AppTheme.spacing.small))
                AdaptiveGrid(
                    items = sampleStats,
                    windowSize = windowSize,
                    modifier = Modifier.height(if (windowSize.width == WindowSizeClass.Compact) 200.dp else 100.dp)
                ) { stat ->
                    AppStatsCard(
                        label = stat.label,
                        value = stat.value,
                        icon = stat.icon
                    )
                }
            }

            item {
                AppSubTitle(text = "Recent Activity")
            }

            items(sampleActivities) { activity ->
                AppListItem(
                    title = activity.title,
                    subtitle = activity.time,
                    leadingIcon = activity.icon
                )
            }
        }
    }
}

data class StatItem(val label: String, val value: String, val icon: ImageVector)
val sampleStats = listOf(
    StatItem("Projects", "12", Icons.Default.List),
    StatItem("Teams", "4", Icons.Default.Person),
    StatItem("Tasks", "24", Icons.Default.Check)
)

data class ActivityItem(val title: String, val time: String, val icon: ImageVector)
val sampleActivities = listOf(
    ActivityItem("Design System Updated", "2 hours ago", Icons.Default.Settings),
    ActivityItem("New Component Added", "5 hours ago", Icons.Default.List),
    ActivityItem("Team Meeting", "Yesterday", Icons.Default.Person)
)
