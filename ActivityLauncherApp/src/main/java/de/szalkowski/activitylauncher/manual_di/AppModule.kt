package de.szalkowski.activitylauncher.manual_di

import de.szalkowski.activitylauncher.domain.loader.AppsLoader
import de.szalkowski.activitylauncher.domain.preferences.AppPreferences
import de.szalkowski.activitylauncher.domain.use_case.activities.ActivitiesScreenUseCases
import de.szalkowski.activitylauncher.domain.use_case.main_activity.MainActivityUseCases
import de.szalkowski.activitylauncher.domain.use_case.settings.SettingsUseCases

/**
 * An abstraction of an Application module for a manual dependency injection.
 *
 * @see <a href="https://github.com/philipplackner/ManualDependencyInjection/blob/master/app/src/main/java/com/plcoding/manualdependencyinjection/di/AppModule.kt">Original code on Philipp Lackner's repo.</a>
 **/
interface AppModule {
    val appPreferences: AppPreferences
    val appsLoader: AppsLoader
    val mainActivityUseCases: MainActivityUseCases
    val activitiesScreenUseCases: ActivitiesScreenUseCases
    val settingsScreenUseCases: SettingsUseCases
}