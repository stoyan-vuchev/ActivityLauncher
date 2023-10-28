package de.szalkowski.activitylauncher.presentation.screens.activities

import androidx.compose.runtime.Stable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import de.szalkowski.activitylauncher.domain.model.AppModel
import de.szalkowski.activitylauncher.utils.UiString

@Stable
data class ActivitiesScreenState(
    val arePrivateActivitiesHidden: Boolean = false,
    val isLoading: Boolean = false,
    val loadedApps: Int = 0,
    val totalApps: Int = 0,
    val appsList: List<AppModel> = emptyList(),
    val error: UiString = UiString.EmptyString,
    val expandedItemsIndexList: SnapshotStateList<Int> = mutableStateListOf()
)