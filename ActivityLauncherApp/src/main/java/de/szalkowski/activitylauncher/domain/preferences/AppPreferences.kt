package de.szalkowski.activitylauncher.domain.preferences

import de.szalkowski.activitylauncher.domain.color_scheme.ColorScheme
import de.szalkowski.activitylauncher.domain.theme_mode.ThemeMode

interface AppPreferences {

    // Disclaimer

    suspend fun getIsDisclaimerAccepted(): Boolean
    suspend fun setIsDisclaimerAccepted()

    // Theme Mode

    suspend fun getThemeMode(): ThemeMode
    suspend fun setThemeMode(themeMode: ThemeMode)

    // Color Scheme

    suspend fun getColorScheme(): ColorScheme
    suspend fun setColorScheme(colorScheme: ColorScheme)

    // Root Access

    suspend fun getIsRootAccessAllowed(rootAccessAllowed: Boolean): Boolean
    suspend fun setIsRootAccessAllowed(rootAccessAllowed: Boolean)

    // Hidden Private Activities

    suspend fun getArePrivateActivitiesHidden(): Boolean
    suspend fun setArePrivateActivitiesHidden(arePrivateActivitiesHidden: Boolean)

}