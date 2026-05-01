package com.compose.uicomponent.features.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.compose.uicomponent.core.designsystem.components.AppBaseScreen
import com.compose.uicomponent.core.designsystem.components.AppBody
import com.compose.uicomponent.core.designsystem.components.AppSubTitle
import com.compose.uicomponent.core.designsystem.components.AppSwitchItem
import com.compose.uicomponent.core.designsystem.components.AppTitle
import com.compose.uicomponent.core.designsystem.theme.AppTheme

@Composable
fun SettingsScreen(viewModel: SettingsViewModel) {
    val state by viewModel.uiState.collectAsState()

    AppBaseScreen {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(AppTheme.spacing.medium)
        ) {
            AppTitle(text = "Preferences")
            
            AppSwitchItem(
                title = "Dark Theme",
                subtitle = "Enable dark mode across the app",
                checked = state.isDarkTheme,
                onCheckedChange = { viewModel.toggleDarkTheme(it) },
                leadingIcon = Icons.Default.Settings
            )

            AppSwitchItem(
                title = "Dynamic Colors",
                subtitle = "Use system dynamic colors (Android 12+)",
                checked = state.useDynamicColors,
                onCheckedChange = { viewModel.toggleDynamicColors(it) },
                leadingIcon = Icons.Default.List
            )

            AppSwitchItem(
                title = "Animations",
                subtitle = "Enable smooth UI transitions",
                checked = state.animationsEnabled,
                onCheckedChange = { viewModel.toggleAnimations(it) },
                leadingIcon = Icons.Default.Check
            )

            Section(title = "Brand Color") {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(AppTheme.spacing.medium)
                ) {
                    viewModel.brandColors.forEach { color ->
                        val isSelected = color == state.seedColor
                        Box(
                            modifier = Modifier
                                .size(40.dp)
                                .clip(androidx.compose.foundation.shape.CircleShape)
                                .background(color)
                                .then(
                                    if (isSelected) Modifier.border(
                                        width = 3.dp,
                                        color = MaterialTheme.colorScheme.onSurface,
                                        shape = androidx.compose.foundation.shape.CircleShape
                                    ) else Modifier
                                )
                                .clickable { viewModel.updateSeedColor(color) }
                        )
                    }
                }
            }

            Section(title = "Font Scale") {
                AppBody(text = "Adjust text size: ${String.format("%.1f", state.fontScale)}x")
                Slider(
                    value = state.fontScale,
                    onValueChange = { viewModel.updateFontScale(it) },
                    valueRange = 0.8f..1.5f,
                    steps = 6
                )
            }
        }
    }
}

@Composable
private fun Section(title: String, content: @Composable () -> Unit) {
    Column(modifier = Modifier.padding(top = AppTheme.spacing.medium)) {
        AppSubTitle(text = title)
        Spacer(modifier = Modifier.height(AppTheme.spacing.small))
        content()
    }
}
