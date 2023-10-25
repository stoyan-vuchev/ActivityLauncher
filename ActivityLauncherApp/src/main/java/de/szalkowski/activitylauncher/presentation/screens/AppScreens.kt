package de.szalkowski.activitylauncher.presentation.screens

import de.szalkowski.activitylauncher.presentation.common.ScreenDestination

object AppScreens {

    data object Activities : ScreenDestination(route = "activities_screen_route")
    data object Settings : ScreenDestination(route = "settings_screen_route")

    val startDestination: ScreenDestination get() = Activities

}