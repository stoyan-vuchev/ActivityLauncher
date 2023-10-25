package de.szalkowski.activitylauncher.presentation.ext

import androidx.navigation.NavHostController
import de.szalkowski.activitylauncher.presentation.common.ScreenDestination

fun NavHostController.navigateToScreen(screen: ScreenDestination) {
    this.navigate(route = screen.route) {
        launchSingleTop = true
    }
}