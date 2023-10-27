package de.szalkowski.activitylauncher.domain.loader

import de.szalkowski.activitylauncher.domain.model.AppModel
import de.szalkowski.activitylauncher.utils.UiString

data class AppsLoaderStateHolder(
    val isLoading: Boolean = false,
    val totalApps: Int = 0,
    val loadedApps: Int = 0,
    val appsList: List<AppModel>,
    val error: UiString = UiString.EmptyString
)