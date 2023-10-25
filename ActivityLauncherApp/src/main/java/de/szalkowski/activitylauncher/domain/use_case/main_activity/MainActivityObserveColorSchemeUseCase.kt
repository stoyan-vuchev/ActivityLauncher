package de.szalkowski.activitylauncher.domain.use_case.main_activity

import de.szalkowski.activitylauncher.domain.preferences.AppPreferences

class MainActivityObserveColorSchemeUseCase(
    private val preferences: AppPreferences
) {
    operator fun invoke() = preferences.observeColorScheme()
}