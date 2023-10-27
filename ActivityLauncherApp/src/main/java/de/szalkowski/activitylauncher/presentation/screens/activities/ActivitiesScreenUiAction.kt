package de.szalkowski.activitylauncher.presentation.screens.activities

import androidx.compose.runtime.Immutable
import de.szalkowski.activitylauncher.presentation.common.ScreenDestination

@Immutable
sealed interface ActivitiesScreenUiAction {
    data class Navigate(val screen: ScreenDestination) : ActivitiesScreenUiAction
    data object Reload : ActivitiesScreenUiAction
}