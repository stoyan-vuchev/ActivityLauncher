package de.szalkowski.activitylauncher.presentation.loader

import android.content.ComponentName
import android.content.pm.ActivityInfo
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import de.szalkowski.activitylauncher.BuildConfig
import de.szalkowski.activitylauncher.R
import de.szalkowski.activitylauncher.domain.loader.AppsLoader
import de.szalkowski.activitylauncher.domain.loader.AppsLoaderStateHolder
import de.szalkowski.activitylauncher.domain.model.ActivityModel
import de.szalkowski.activitylauncher.domain.model.AppModel
import de.szalkowski.activitylauncher.utils.DrawableUtils.drawableToBitmap
import de.szalkowski.activitylauncher.utils.UiString
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.withContext

class AppsLoaderImpl(
    private val packageManager: PackageManager
) : AppsLoader {

    private var installedPackages: MutableList<PackageInfo> = mutableListOf()
    private val _isLoading = MutableStateFlow(false)
    private val _totalApps = MutableStateFlow(0)
    private val _loadedApps = MutableStateFlow(0)
    private val _appsList = MutableStateFlow<List<AppModel>>(emptyList())
    private val _error = MutableStateFlow<UiString>(UiString.EmptyString)

    override val state: Flow<AppsLoaderStateHolder> = combine(
        flow = _isLoading,
        flow2 = _totalApps,
        flow3 = _loadedApps,
        flow4 = _appsList,
        flow5 = _error
    ) { isLoading, totalApps, loadedApps, appsList, error ->

        AppsLoaderStateHolder(
            isLoading = isLoading,
            totalApps = totalApps,
            loadedApps = loadedApps,
            appsList = appsList,
            error = error
        )

    }

    override suspend fun loadApps() {

        if (!_isLoading.value) {

            // Start loading.
            _isLoading.update { true }

            // Reset the states and get all apps.
            resetStates(); getAllApps()

            var list = mutableListOf<AppModel>()
            withContext(Dispatchers.Default) {

                try {

                    installedPackages.forEach { packageInfo ->

                        val packageName = packageInfo.packageName
                        val appInfo = packageInfo.applicationInfo
                        val appName = appInfo.loadLabel(packageManager).toString()
                        val appIcon = drawableToBitmap(appInfo.loadIcon(packageManager))
                        val activities = mutableListOf<ActivityModel>()

                        packageInfo.activities?.forEach { activityInfo ->

                            val componentName = ComponentName(packageName, activityInfo.name)
                            val enabledState =
                                packageManager.getComponentEnabledSetting(componentName)
                            val activityName = activityInfo.name
                            val activityIcon =
                                drawableToBitmap(activityInfo.loadIcon(packageManager))
                            val isPrivate = isActivityPrivate(activityInfo, enabledState)

                            activities.add(
                                ActivityModel(
                                    name = activityName.withoutPackage(),
                                    packageName = activityName,
                                    icon = activityIcon,
                                    isPrivate = isPrivate
                                )
                            )

                        }

                        if (activities.isNotEmpty()) {
                            list.add(
                                AppModel(
                                    name = appName,
                                    packageName = packageName,
                                    icon = appIcon,
                                    activities = activities
                                )
                            )
                        }

                        // Increase the loaded apps variable by 1.
                        _loadedApps.update { it + 1 }

                    }

                    // Update the apps list with the new list,
                    // clear the temporary list and stop the loading.
                    _appsList.update { list }.also {
                        list = mutableListOf()
                        _isLoading.update { false }
                    }

                } catch (e: Exception) {

                    declareAnError(
                        error = e.localizedMessage?.let { UiString.DynamicString(it) }
                            ?: UiString.StringResource(R.string.error_tasks)
                    )

                }

            }

        }

    }

    private fun resetStates() {
        _error.update { UiString.EmptyString }
        _loadedApps.update { 0 }
        _totalApps.update { 0 }
        _appsList.update { emptyList() }
        installedPackages = mutableListOf()
    }

    private suspend fun getAllApps() {
        try {
            installedPackages = withContext(Dispatchers.IO) {
                packageManager.getInstalledPackages(0 or PackageManager.GET_ACTIVITIES)
                    .filter {
                        it.activities?.isNotEmpty() ?: false
                                && it.packageName != BuildConfig.APPLICATION_ID
                    }
            }.also { packages -> _totalApps.update { packages.size } }.toMutableList()
        } catch (e: Exception) {
            declareAnError(
                error = e.localizedMessage?.let { UiString.DynamicString(it) }
                    ?: UiString.StringResource(R.string.error_tasks)
            )
        }
    }

    private fun declareAnError(error: UiString) {
        _isLoading.update { false }
        resetStates()
        _error.update { error }
    }

    companion object {

        private fun isActivityPrivate(activity: ActivityInfo, enabledState: Int): Boolean {
            return if (!activity.exported) true else when (enabledState) {
                PackageManager.COMPONENT_ENABLED_STATE_DISABLED -> true
                PackageManager.COMPONENT_ENABLED_STATE_ENABLED -> false
                else -> !activity.isEnabled
            }
        }

        private fun String.withoutPackage(): String {
            val packageIndex = this.lastIndexOf('.')
            return if (packageIndex >= 0 && packageIndex < this.length - 1) {
                this.substring(packageIndex + 1)
            } else this
        }

    }

}