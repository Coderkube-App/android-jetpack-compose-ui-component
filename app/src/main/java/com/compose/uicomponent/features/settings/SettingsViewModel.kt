package com.compose.uicomponent.features.settings

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class SettingsState(
    val isDarkTheme: Boolean = false,
    val useDynamicColors: Boolean = false,
    val animationsEnabled: Boolean = true,
    val fontScale: Float = 1.0f,
    val seedColor: androidx.compose.ui.graphics.Color = androidx.compose.ui.graphics.Color(0xFF6750A4)
)

class SettingsViewModel : ViewModel() {
    val brandColors = listOf(
        androidx.compose.ui.graphics.Color(0xFF6750A4), // Purple
        androidx.compose.ui.graphics.Color(0xFF006494), // Blue
        androidx.compose.ui.graphics.Color(0xFF006B5B), // Green
        androidx.compose.ui.graphics.Color(0xFF984061), // Pink
        androidx.compose.ui.graphics.Color(0xFFB3261E), // Red
        androidx.compose.ui.graphics.Color(0xFF625B71)  // Gray
    )

    private val _uiState = MutableStateFlow(SettingsState())
    val uiState: StateFlow<SettingsState> = _uiState.asStateFlow()

    fun toggleDarkTheme(enabled: Boolean) {
        _uiState.update { it.copy(isDarkTheme = enabled) }
    }

    fun toggleDynamicColors(enabled: Boolean) {
        _uiState.update { it.copy(useDynamicColors = enabled) }
    }

    fun toggleAnimations(enabled: Boolean) {
        _uiState.update { it.copy(animationsEnabled = enabled) }
    }

    fun updateFontScale(scale: Float) {
        _uiState.update { it.copy(fontScale = scale) }
    }

    fun updateSeedColor(color: androidx.compose.ui.graphics.Color) {
        _uiState.update { it.copy(seedColor = color, useDynamicColors = false) }
    }
}
