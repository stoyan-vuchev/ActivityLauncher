package de.szalkowski.activitylauncher.presentation

import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import de.szalkowski.activitylauncher.ActivityLauncherApp.Companion.appModule
import de.szalkowski.activitylauncher.presentation.ext.navigateToScreen
import de.szalkowski.activitylauncher.presentation.ext.viewModelFactory
import de.szalkowski.activitylauncher.presentation.screens.AppScreens
import de.szalkowski.activitylauncher.presentation.screens.activities.ActivitiesScreen
import de.szalkowski.activitylauncher.presentation.screens.activities.ActivitiesScreenUiAction.Navigate
import de.szalkowski.activitylauncher.presentation.screens.activities.ActivitiesScreenViewModel
import de.szalkowski.activitylauncher.presentation.screens.settings.SettingsScreen
import de.szalkowski.activitylauncher.presentation.screens.settings.SettingsScreenUiAction.ChangeLanguageFromSystemSettings
import de.szalkowski.activitylauncher.presentation.screens.settings.SettingsScreenUiAction.NavigateUp
import de.szalkowski.activitylauncher.presentation.screens.settings.SettingsScreenUiAction.OpenUrl
import de.szalkowski.activitylauncher.presentation.screens.settings.SettingsScreenViewModel
import de.szalkowski.activitylauncher.utils.LanguageUtils.changeAppLanguageFromSystemSettings
import de.szalkowski.activitylauncher.utils.SdkUtils
import kotlinx.coroutines.flow.collectLatest

@Composable
fun AppNavigationHost(
    navController: NavHostController
) = NavHost(
    modifier = Modifier.fillMaxSize(),
    navController = navController,
    startDestination = AppScreens.startDestination.route,
    enterTransition = remember { { fadeIn() } },
    exitTransition = remember { { fadeOut() } }
) {

    composable(
        route = AppScreens.Activities.route,
        content = {

            val viewModel = viewModel<ActivitiesScreenViewModel>(
                factory = viewModelFactory {

                    val useCases by lazy { appModule.activitiesScreenUseCases }
                    val appsLoader by lazy { appModule.appsLoader }

                    ActivitiesScreenViewModel(
                        useCases = useCases,
                        appsLoader = appsLoader
                    )

                }
            )

            val screenState by viewModel.state.collectAsStateWithLifecycle()

            LaunchedEffect(viewModel.uiActionFlow) {
                viewModel.uiActionFlow.collectLatest { uiAction ->
                    when (uiAction) {
                        is Navigate -> navController.navigateToScreen(uiAction.screen)
                        else -> Unit
                    }
                }
            }

            ActivitiesScreen(
                screenState = screenState,
                onUiAction = viewModel::onUiAction
            )

        }
    )

    composable(
        route = AppScreens.Settings.route,
        content = {

            val viewModel = viewModel<SettingsScreenViewModel>(
                factory = viewModelFactory {
                    val useCases by lazy { appModule.settingsScreenUseCases }
                    SettingsScreenViewModel(useCases = useCases)
                }
            )

            val screenState by viewModel.state.collectAsStateWithLifecycle()
            val snackbarHostState = remember { SnackbarHostState() }
            val context = LocalContext.current
            val uriHandler = LocalUriHandler.current

            LaunchedEffect(viewModel.uiActionFlow) {
                viewModel.uiActionFlow.collectLatest { uiAction ->
                    when (uiAction) {

                        is NavigateUp -> navController.navigateUp()
                        is OpenUrl -> uriHandler.openUri(uiAction.url)

                        is ChangeLanguageFromSystemSettings -> {
                            if (SdkUtils.supportsPerAppLanguages) {
                                context.changeAppLanguageFromSystemSettings()
                            }
                        }

                        else -> Unit

                    }
                }
            }

            LaunchedEffect(viewModel.snackbarFlow) {
                viewModel.snackbarFlow.collect { msg ->
                    snackbarHostState.showSnackbar(msg.asString(context))
                }
            }

            SettingsScreen(
                screenState = screenState,
                snackbarHostState = snackbarHostState,
                onUiAction = viewModel::onUiAction
            )

        }
    )

}