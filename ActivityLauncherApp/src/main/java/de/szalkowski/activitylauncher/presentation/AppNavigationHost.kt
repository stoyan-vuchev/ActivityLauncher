package de.szalkowski.activitylauncher.presentation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import de.szalkowski.activitylauncher.presentation.screens.AppScreens
import de.szalkowski.activitylauncher.presentation.screens.activities.ActivitiesScreen

@Composable
fun AppNavigationHost(
    navController: NavHostController
) = NavHost(
    modifier = Modifier.fillMaxSize(),
    navController = navController,
    startDestination = AppScreens.Activities.route
) {

    composable(
        route = AppScreens.Activities.route,
        content = { ActivitiesScreen() }
    )

    composable(
        route = AppScreens.Settings.route,
        content = {

            // todo

        }
    )

}