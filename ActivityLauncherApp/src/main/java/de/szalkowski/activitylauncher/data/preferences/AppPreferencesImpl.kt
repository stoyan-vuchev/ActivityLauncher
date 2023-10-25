package de.szalkowski.activitylauncher.data.preferences

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import de.szalkowski.activitylauncher.domain.color_scheme.ColorScheme
import de.szalkowski.activitylauncher.domain.preferences.AppPreferences
import de.szalkowski.activitylauncher.domain.theme_mode.ThemeMode
import kotlinx.coroutines.flow.first

class AppPreferencesImpl(
    private val dataStore: DataStore<Preferences>
) : AppPreferences {

    // Disclaimer

    override suspend fun getIsDisclaimerAccepted(): Boolean {
        return dataStore.data.first()[DISCLAIMER_KEY] ?: false
    }

    override suspend fun setIsDisclaimerAccepted() {
        dataStore.edit { it[DISCLAIMER_KEY] = true }
    }

    // Theme Mode

    override suspend fun getThemeMode(): ThemeMode {
        return dataStore.data.first()[THEME_MODE_KEY]?.let { themeMode ->
            ThemeMode.valueOf(themeMode)
        } ?: ThemeMode.Default
    }

    override suspend fun setThemeMode(themeMode: ThemeMode) {
        dataStore.edit { it[THEME_MODE_KEY] = themeMode.name }
    }

    // Color Scheme

    override suspend fun getColorScheme(): ColorScheme {
        return dataStore.data.first()[COLOR_SCHEME_KEY]?.let { colorScheme ->
            ColorScheme.valueOf(colorScheme)
        } ?: ColorScheme.Default
    }

    override suspend fun setColorScheme(colorScheme: ColorScheme) {
        dataStore.edit { it[COLOR_SCHEME_KEY] = colorScheme.name }
    }

    // Root Access

    override suspend fun getIsRootAccessAllowed(rootAccessAllowed: Boolean): Boolean {
        return dataStore.data.first()[ROOT_ACCESS_KEY] ?: false
    }

    override suspend fun setIsRootAccessAllowed(rootAccessAllowed: Boolean) {
        dataStore.edit { it[ROOT_ACCESS_KEY] = rootAccessAllowed }
    }

    // Hidden Private Activities

    override suspend fun getArePrivateActivitiesHidden(): Boolean {
        return dataStore.data.first()[HIDE_PRIVATE_ACTIVITIES_KEY] ?: false
    }

    override suspend fun setArePrivateActivitiesHidden(arePrivateActivitiesHidden: Boolean) {
        dataStore.edit { it[HIDE_PRIVATE_ACTIVITIES_KEY] = arePrivateActivitiesHidden }
    }

    companion object {

        const val APP_DATA_STORE_PREFERENCES = "app_preferences"

        val DISCLAIMER_KEY = booleanPreferencesKey("disclaimer")
        val THEME_MODE_KEY = stringPreferencesKey("theme_mode")
        val COLOR_SCHEME_KEY = stringPreferencesKey("color_scheme")
        val ROOT_ACCESS_KEY = booleanPreferencesKey("root_access")
        val HIDE_PRIVATE_ACTIVITIES_KEY = booleanPreferencesKey("hide_private_activities")

    }

}