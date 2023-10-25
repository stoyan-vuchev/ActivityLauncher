package de.szalkowski.activitylauncher.presentation.screens.settings

import androidx.compose.runtime.Stable
import de.szalkowski.activitylauncher.presentation.screens.settings.dialog.SettingsDialogType

@Stable
data class SettingsScreenState(
    val arePrivateActivitiesHidden: Boolean = false,
    val areRootPrivilegesEnabled: Boolean = false,
    val settingsDialogType: SettingsDialogType = SettingsDialogType.None
)