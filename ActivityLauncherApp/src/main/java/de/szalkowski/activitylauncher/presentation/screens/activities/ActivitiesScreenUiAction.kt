package de.szalkowski.activitylauncher.presentation.screens.activities

import androidx.compose.runtime.Immutable
import de.szalkowski.activitylauncher.presentation.common.ScreenDestination

@Immutable
sealed interface ActivitiesScreenUiAction {
    data class Navigate(val screen: ScreenDestination) : ActivitiesScreenUiAction
    data class ExpandOrCollapseItem(val index: Int) : ActivitiesScreenUiAction
    data object Reload : ActivitiesScreenUiAction
}