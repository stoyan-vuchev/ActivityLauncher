package de.szalkowski.activitylauncher.manual_di

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import de.szalkowski.activitylauncher.data.preferences.AppPreferencesImpl
import de.szalkowski.activitylauncher.domain.use_case.main_activity.MainActivityDisclaimerUseCases
import de.szalkowski.activitylauncher.domain.use_case.main_activity.MainActivityObserveColorSchemeUseCase
import de.szalkowski.activitylauncher.domain.use_case.main_activity.MainActivityObserveThemeModeUseCase
import de.szalkowski.activitylauncher.domain.use_case.main_activity.MainActivityUseCases
import de.szalkowski.activitylauncher.domain.use_case.settings.SettingsEnablePrivilegedModeUseCases
import de.szalkowski.activitylauncher.domain.use_case.settings.SettingsHidePrivateActivitiesUseCases
import de.szalkowski.activitylauncher.domain.use_case.settings.SettingsSetColorSchemeUseCase
import de.szalkowski.activitylauncher.domain.use_case.settings.SettingsSetThemeModeUseCase
import de.szalkowski.activitylauncher.domain.use_case.settings.SettingsUseCases

/**
 * An implementation of an [AppModule] for a manual dependency injection.
 *
 * @param dataStore the [DataStore] preferences of the app.
 *
 * @property appPreferences the application preferences instance.
 *
 * @see <a href="https://github.com/philipplackner/ManualDependencyInjection/blob/master/app/src/main/java/com/plcoding/manualdependencyinjection/di/AppModule.kt">Original code on Philipp Lackner's repo.</a>
 **/
class AppModuleImpl(
    private val dataStore: DataStore<Preferences>
) : AppModule {

    override val appPreferences by lazy {
        AppPreferencesImpl(dataStore = dataStore)
    }

    override val mainActivityUseCases by lazy {
        MainActivityUseCases(
            disclaimerUseCases = MainActivityDisclaimerUseCases(appPreferences),
            observeThemeMode = MainActivityObserveThemeModeUseCase(appPreferences),
            observeColorScheme = MainActivityObserveColorSchemeUseCase(appPreferences)
        )
    }

    override val settingsUseCases by lazy {
        SettingsUseCases(
            privateActivitiesUseCases = SettingsHidePrivateActivitiesUseCases(appPreferences),
            privilegedModeUseCases = SettingsEnablePrivilegedModeUseCases(appPreferences),
            setThemeModeUseCase = SettingsSetThemeModeUseCase(appPreferences),
            setColorSchemeUseCase = SettingsSetColorSchemeUseCase(appPreferences)
        )
    }

}