package de.szalkowski.activitylauncher.domain.use_case.settings

import de.szalkowski.activitylauncher.domain.color_scheme.ColorScheme
import de.szalkowski.activitylauncher.domain.preferences.AppPreferences
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SettingsSetColorSchemeUseCase(
    private val preferences: AppPreferences
) {

    suspend operator fun invoke(colorScheme: ColorScheme) {
        withContext(Dispatchers.IO) {
            preferences.setColorScheme(colorScheme)
        }
    }

}