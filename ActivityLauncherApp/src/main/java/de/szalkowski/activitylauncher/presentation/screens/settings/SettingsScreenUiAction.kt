package de.szalkowski.activitylauncher.presentation.screens.settings

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Immutable
import de.szalkowski.activitylauncher.domain.preferences.color_scheme.ColorScheme
import de.szalkowski.activitylauncher.domain.preferences.theme_mode.ThemeMode
import de.szalkowski.activitylauncher.presentation.screens.settings.dialog.SettingsDialogType

@Immutable
sealed interface SettingsScreenUiAction {

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    data object ChangeLanguageFromSystemSettings : SettingsScreenUiAction
    data object NavigateUp : SettingsScreenUiAction

    data class SetHidePrivateActivities(val hidden: Boolean) : SettingsScreenUiAction
    data class SetPrivilegedMode(val enabled: Boolean) : SettingsScreenUiAction
    data class SetDialogType(val dialogType: SettingsDialogType) : SettingsScreenUiAction
    data class SetThemeMode(val themeMode: ThemeMode) : SettingsScreenUiAction
    data class SetColorScheme(val colorScheme: ColorScheme) : SettingsScreenUiAction
    data class OpenUrl(val url: String) : SettingsScreenUiAction

}