package de.szalkowski.activitylauncher.presentation.screens.settings.dialog

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import de.szalkowski.activitylauncher.presentation.ext.LocalColorScheme
import de.szalkowski.activitylauncher.presentation.ext.LocalThemeMode
import de.szalkowski.activitylauncher.presentation.screens.settings.SettingsScreenUiAction
import de.szalkowski.activitylauncher.presentation.screens.settings.SettingsScreenUiAction.SetColorScheme
import de.szalkowski.activitylauncher.presentation.screens.settings.SettingsScreenUiAction.SetDialogType
import de.szalkowski.activitylauncher.presentation.screens.settings.SettingsScreenUiAction.SetThemeMode

@Composable
fun SettingsDialog(
    dialogType: SettingsDialogType,
    onUiAction: (SettingsScreenUiAction) -> Unit
) {

    val onDismissRequest = remember { { onUiAction(SetDialogType(SettingsDialogType.None)) } }

    when (dialogType) {

        is SettingsDialogType.None -> Unit // Don't display anything.

        is SettingsDialogType.ThemeMode -> ThemeModeDialog(
            initialThemeMode = LocalThemeMode.current,
            onDismissRequest = onDismissRequest,
            onSetThemeMode = remember { { onUiAction(SetThemeMode(it)) } }
        )

        is SettingsDialogType.ColorScheme -> ColorSchemeDialog(
            initialColorScheme = LocalColorScheme.current,
            onDismissRequest = onDismissRequest,
            onSetColorScheme = remember { { onUiAction(SetColorScheme(it)) } }
        )

    }

}