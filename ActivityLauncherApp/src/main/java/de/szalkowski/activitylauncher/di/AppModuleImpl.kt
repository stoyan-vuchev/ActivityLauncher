package de.szalkowski.activitylauncher.di

import android.content.Context
import androidx.datastore.preferences.preferencesDataStore
import de.szalkowski.activitylauncher.data.preferences.AppPreferencesImpl
import de.szalkowski.activitylauncher.data.preferences.AppPreferencesImpl.Companion.APP_DATA_STORE_PREFERENCES

/**
 * An implementation of an [AppModule] for a manual dependency injection.
 *
 * @param appContext the application context.
 *
 * @property appPreferences the application preferences instance.
 *
 * @see <a href="https://github.com/philipplackner/ManualDependencyInjection/blob/master/app/src/main/java/com/plcoding/manualdependencyinjection/di/AppModule.kt">Original code on Philipp Lackner's repo.</a>
 **/
class AppModuleImpl(private val appContext: Context) : AppModule {

    // Creating an instance of the application preferences.
    private val Context.appDataStorePreferences by preferencesDataStore(APP_DATA_STORE_PREFERENCES)

    override val appPreferences by lazy {
        AppPreferencesImpl(dataStore = appContext.appDataStorePreferences)
    }

}