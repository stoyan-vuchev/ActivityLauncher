package de.szalkowski.activitylauncher.presentation.screens.settings.dialog

import androidx.compose.runtime.Immutable

@Immutable
sealed interface SettingsDialogType {
    data object None : SettingsDialogType
    data object ThemeMode : SettingsDialogType
    data object ColorScheme : SettingsDialogType
}