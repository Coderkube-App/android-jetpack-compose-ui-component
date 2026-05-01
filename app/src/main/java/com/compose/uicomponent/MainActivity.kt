package com.compose.uicomponent

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.compose.uicomponent.core.designsystem.theme.AppTheme
import com.compose.uicomponent.core.navigation.MainAppContent
import com.compose.uicomponent.features.settings.SettingsViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val settingsViewModel: SettingsViewModel = viewModel()
            val settingsState by settingsViewModel.uiState.collectAsState()
            
            AppTheme(
                darkTheme = settingsState.isDarkTheme,
                dynamicColor = settingsState.useDynamicColors,
                seedColor = settingsState.seedColor
            ) {
                MainAppContent(settingsViewModel)
            }
        }
    }
}