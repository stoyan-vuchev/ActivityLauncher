package de.szalkowski.activitylauncher.domain.use_case.settings

import de.szalkowski.activitylauncher.domain.preferences.AppPreferences
import de.szalkowski.activitylauncher.domain.theme_mode.ThemeMode
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SettingsSetThemeModeUseCase(
    private val preferences: AppPreferences
) {

    suspend operator fun invoke(themeMode: ThemeMode) {
        withContext(Dispatchers.IO) {
            preferences.setThemeMode(themeMode)
        }
    }

}