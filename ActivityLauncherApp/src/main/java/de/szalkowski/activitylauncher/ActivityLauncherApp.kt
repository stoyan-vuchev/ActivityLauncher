package de.szalkowski.activitylauncher

import android.app.Application
import android.content.Context
import androidx.datastore.preferences.preferencesDataStore
import de.szalkowski.activitylauncher.data.preferences.AppPreferencesImpl.Companion.APP_DATA_STORE_PREFERENCES
import de.szalkowski.activitylauncher.manual_di.AppModule
import de.szalkowski.activitylauncher.manual_di.AppModuleImpl

class ActivityLauncherApp : Application() {

    companion object {

        private val Context.dataStore by preferencesDataStore(APP_DATA_STORE_PREFERENCES)

        /**
         * The application module property of the manual dependency injection.
         *
         * @see <a href="https://github.com/philipplackner/ManualDependencyInjection/blob/master/app/src/main/java/com/plcoding/manualdependencyinjection/MyApp.kt">Original code on Philipp Lackner's repo.</a>
         **/
        lateinit var appModule: AppModule

    }

    override fun onCreate() {
        super.onCreate()
        initAppModule()
    }

    private fun initAppModule() {
        appModule = AppModuleImpl(
            dataStore = this.dataStore,
            packageManager = this.packageManager
        )
    }

}