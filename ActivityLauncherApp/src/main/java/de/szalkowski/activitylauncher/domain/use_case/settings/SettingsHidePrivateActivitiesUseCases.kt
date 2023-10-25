package de.szalkowski.activitylauncher.domain.use_case.settings

import de.szalkowski.activitylauncher.domain.preferences.AppPreferences
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SettingsHidePrivateActivitiesUseCases(
    private val preferences: AppPreferences
) {

    suspend fun getArePrivateActivitiesHidden(): Boolean {
        return withContext(Dispatchers.IO) {
            preferences.getArePrivateActivitiesHidden()
        }
    }

    suspend fun setArePrivateActivitiesHidden(hidden: Boolean) {
        withContext(Dispatchers.IO) {
            preferences.setArePrivateActivitiesHidden(hidden)
        }
    }

}