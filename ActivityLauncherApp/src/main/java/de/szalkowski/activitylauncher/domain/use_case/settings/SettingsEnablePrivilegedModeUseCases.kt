package de.szalkowski.activitylauncher.domain.use_case.settings

import de.szalkowski.activitylauncher.domain.preferences.AppPreferences
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SettingsEnablePrivilegedModeUseCases(
    private val preferences: AppPreferences
) {

    suspend fun getIsPrivilegedModeEnabled(): Boolean {
        return withContext(Dispatchers.IO) {
            preferences.getAreRootPrivilegesEnabled()
        }
    }

    suspend fun setPrivilegedMode(enabled: Boolean) {
        withContext(Dispatchers.IO) {
            preferences.setAreRootPrivilegesEnabled(enabled)
        }
    }

}