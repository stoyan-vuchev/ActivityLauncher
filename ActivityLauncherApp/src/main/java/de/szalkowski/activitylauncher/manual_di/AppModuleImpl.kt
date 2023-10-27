package de.szalkowski.activitylauncher.manual_di

import android.content.pm.PackageManager
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import de.szalkowski.activitylauncher.data.preferences.AppPreferencesImpl
import de.szalkowski.activitylauncher.domain.loader.AppsLoader
import de.szalkowski.activitylauncher.domain.use_case.activities.ActivitiesScreenPrivateActivitiesUseCase
import de.szalkowski.activitylauncher.domain.use_case.activities.ActivitiesScreenUseCases
import de.szalkowski.activitylauncher.domain.use_case.main_activity.MainActivityDisclaimerUseCases
import de.szalkowski.activitylauncher.domain.use_case.main_activity.MainActivityObserveColorSchemeUseCase
import de.szalkowski.activitylauncher.domain.use_case.main_activity.MainActivityObserveThemeModeUseCase
import de.szalkowski.activitylauncher.domain.use_case.main_activity.MainActivityUseCases
import de.szalkowski.activitylauncher.domain.use_case.settings.SettingsEnablePrivilegedModeUseCases
import de.szalkowski.activitylauncher.domain.use_case.settings.SettingsHidePrivateActivitiesUseCases
import de.szalkowski.activitylauncher.domain.use_case.settings.SettingsSetColorSchemeUseCase
import de.szalkowski.activitylauncher.domain.use_case.settings.SettingsSetThemeModeUseCase
import de.szalkowski.activitylauncher.domain.use_case.settings.SettingsUseCases
import de.szalkowski.activitylauncher.presentation.loader.AppsLoaderImpl

/**
 * An implementation of an [AppModule] for a manual dependency injection.
 *
 * @param dataStore the [DataStore] preferences of the app.
 * @param packageManager the [PackageManager] instance for the [AppsLoader].
 *
 * @see <a href="https://github.com/philipplackner/ManualDependencyInjection/blob/master/app/src/main/java/com/plcoding/manualdependencyinjection/di/AppModule.kt">Original code on Philipp Lackner's repo.</a>
 **/
class AppModuleImpl(
    private val dataStore: DataStore<Preferences>,
    private val packageManager: PackageManager
) : AppModule {

    override val appPreferences by lazy {
        AppPreferencesImpl(dataStore = dataStore)
    }

    override val appsLoader by lazy {
        AppsLoaderImpl(packageManager = packageManager)
    }

    override val mainActivityUseCases by lazy {
        MainActivityUseCases(
            disclaimerUseCases = MainActivityDisclaimerUseCases(appPreferences),
            observeThemeMode = MainActivityObserveThemeModeUseCase(appPreferences),
            observeColorScheme = MainActivityObserveColorSchemeUseCase(appPreferences)
        )
    }

    override val activitiesScreenUseCases by lazy {
        ActivitiesScreenUseCases(
            observeArePrivateActivitiesHidden = ActivitiesScreenPrivateActivitiesUseCase(
                appPreferences
            )
        )
    }

    override val settingsScreenUseCases by lazy {
        SettingsUseCases(
            privateActivitiesUseCases = SettingsHidePrivateActivitiesUseCases(appPreferences),
            privilegedModeUseCases = SettingsEnablePrivilegedModeUseCases(appPreferences),
            setThemeModeUseCase = SettingsSetThemeModeUseCase(appPreferences),
            setColorSchemeUseCase = SettingsSetColorSchemeUseCase(appPreferences)
        )
    }

}