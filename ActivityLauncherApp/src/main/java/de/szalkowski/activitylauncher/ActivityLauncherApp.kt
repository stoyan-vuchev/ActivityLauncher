package de.szalkowski.activitylauncher

import android.app.Application
import de.szalkowski.activitylauncher.di.AppModule
import de.szalkowski.activitylauncher.di.AppModuleImpl

class ActivityLauncherApp : Application() {

    companion object {

        /**
         * The application module property of the manual dependency injection.
         *
         * @see <a href="https://github.com/philipplackner/ManualDependencyInjection/blob/master/app/src/main/java/com/plcoding/manualdependencyinjection/MyApp.kt">Original code on Philipp Lackner's repo.</a>
         **/
        lateinit var appModule: AppModule

    }

    override fun onCreate() {
        super.onCreate()
        appModule = AppModuleImpl(this)
    }

}