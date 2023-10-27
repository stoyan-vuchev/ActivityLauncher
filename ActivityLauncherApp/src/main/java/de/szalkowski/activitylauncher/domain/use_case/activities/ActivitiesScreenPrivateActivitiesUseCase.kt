package de.szalkowski.activitylauncher.domain.use_case.activities

import de.szalkowski.activitylauncher.domain.preferences.AppPreferences
import kotlinx.coroutines.flow.Flow

class ActivitiesScreenPrivateActivitiesUseCase(
    private val preferences: AppPreferences
) {
    operator fun invoke(): Flow<Boolean> {
        return preferences.observeArePrivateActivitiesHidden()
    }
}