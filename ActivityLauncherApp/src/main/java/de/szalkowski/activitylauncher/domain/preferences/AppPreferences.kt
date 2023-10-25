package de.szalkowski.activitylauncher.domain.preferences

import de.szalkowski.activitylauncher.domain.color_scheme.ColorScheme
import de.szalkowski.activitylauncher.domain.theme_mode.ThemeMode
import kotlinx.coroutines.flow.Flow

interface AppPreferences {

    // Disclaimer

    fun observeIsDisclaimerAccepted(): Flow<Boolean?>
    suspend fun setIsDisclaimerAccepted()

    // Theme Mode

    fun observeThemeMode(): Flow<ThemeMode>
    suspend fun setThemeMode(themeMode: ThemeMode)

    // Color Scheme

    fun observeColorScheme(): Flow<ColorScheme>
    suspend fun setColorScheme(colorScheme: ColorScheme)

    // Root Access

    suspend fun getAreRootPrivilegesEnabled(): Boolean
    suspend fun setAreRootPrivilegesEnabled(enabled: Boolean)

    // Hidden Private Activities

    suspend fun getArePrivateActivitiesHidden(): Boolean
    suspend fun setArePrivateActivitiesHidden(hidden: Boolean)

}