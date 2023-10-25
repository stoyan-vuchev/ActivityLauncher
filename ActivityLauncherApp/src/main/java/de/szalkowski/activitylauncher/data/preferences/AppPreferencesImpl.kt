package de.szalkowski.activitylauncher.data.preferences

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import de.szalkowski.activitylauncher.domain.color_scheme.ColorScheme
import de.szalkowski.activitylauncher.domain.preferences.AppPreferences
import de.szalkowski.activitylauncher.domain.theme_mode.ThemeMode
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

class AppPreferencesImpl(
    private val dataStore: DataStore<Preferences>
) : AppPreferences {

    // Disclaimer

    override fun observeIsDisclaimerAccepted(): Flow<Boolean> = dataStore.data.map { prefs ->
        prefs[DISCLAIMER_KEY] ?: false
    }

    override suspend fun setIsDisclaimerAccepted() {
        dataStore.edit { it[DISCLAIMER_KEY] = true }
    }

    // Theme Mode

    override fun observeThemeMode(): Flow<ThemeMode> = dataStore.data.map { prefs ->
        prefs[THEME_MODE_KEY]?.let { ThemeMode.valueOf(it) }
            ?: ThemeMode.Default
    }

    override suspend fun setThemeMode(themeMode: ThemeMode) {
        dataStore.edit { it[THEME_MODE_KEY] = themeMode.name }
    }

    // Color Scheme

    override fun observeColorScheme(): Flow<ColorScheme> = dataStore.data.map { prefs ->
        prefs[COLOR_SCHEME_KEY]?.let { ColorScheme.valueOf(it) }
            ?: ColorScheme.Default
    }

    override suspend fun setColorScheme(colorScheme: ColorScheme) {
        dataStore.edit { it[COLOR_SCHEME_KEY] = colorScheme.name }
    }

    // Root Access

    override suspend fun getAreRootPrivilegesEnabled(): Boolean {
        return dataStore.data.first()[ROOT_ACCESS_KEY] ?: false
    }

    override suspend fun setAreRootPrivilegesEnabled(enabled: Boolean) {
        dataStore.edit { it[ROOT_ACCESS_KEY] = enabled }
    }

    // Hidden Private Activities

    override suspend fun getArePrivateActivitiesHidden(): Boolean {
        return dataStore.data.first()[HIDE_PRIVATE_ACTIVITIES_KEY] ?: false
    }

    override suspend fun setArePrivateActivitiesHidden(hidden: Boolean) {
        dataStore.edit { it[HIDE_PRIVATE_ACTIVITIES_KEY] = hidden }
    }

    companion object {

        const val APP_DATA_STORE_PREFERENCES = "app_preferences"

        private val DISCLAIMER_KEY = booleanPreferencesKey("disclaimer")
        private val THEME_MODE_KEY = stringPreferencesKey("theme_mode")
        private val COLOR_SCHEME_KEY = stringPreferencesKey("color_scheme")
        private val LANGUAGE_KEY = stringPreferencesKey("language")
        private val ROOT_ACCESS_KEY = booleanPreferencesKey("root_access")
        private val HIDE_PRIVATE_ACTIVITIES_KEY = booleanPreferencesKey("hide_private_activities")

    }

}