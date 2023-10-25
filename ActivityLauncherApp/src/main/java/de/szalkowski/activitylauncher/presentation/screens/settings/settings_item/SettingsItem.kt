package de.szalkowski.activitylauncher.presentation.screens.settings.settings_item

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import de.szalkowski.activitylauncher.presentation.screens.settings.SettingsCategory

@Stable
data class SettingsItem(
    val category: SettingsCategory,
    val content: @Composable () -> Unit
)