package de.szalkowski.activitylauncher.domain.use_case.main_activity

import de.szalkowski.activitylauncher.domain.preferences.AppPreferences
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MainActivityDisclaimerUseCases(
    private val preferences: AppPreferences
) {

    fun observe() = preferences.observeIsDisclaimerAccepted()

    suspend fun accept() {
        withContext(Dispatchers.IO) {
            preferences.setIsDisclaimerAccepted()
        }
    }

}