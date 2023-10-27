package de.szalkowski.activitylauncher.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import de.szalkowski.activitylauncher.ActivityLauncherApp.Companion.appModule
import de.szalkowski.activitylauncher.presentation.ext.viewModelFactory
import de.szalkowski.activitylauncher.presentation.ui.theme.ActivityLauncherTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            val viewModel = viewModel<MainActivityViewModel>(
                factory = viewModelFactory {
                    val useCases by lazy { appModule.mainActivityUseCases }
                    MainActivityViewModel(useCases = useCases)
                }
            )

            val isDisclaimerAccepted by viewModel.isDisclaimerAccepted.collectAsStateWithLifecycle()
            val themeMode by viewModel.themeMode.collectAsStateWithLifecycle()
            val colorScheme by viewModel.colorScheme.collectAsStateWithLifecycle()
            val navController = rememberNavController()

            ActivityLauncherTheme(
                themeMode = themeMode,
                colorScheme = colorScheme
            ) {

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.surface
                ) {

                    AnimatedVisibility(
                        visible = isDisclaimerAccepted == true,
                        enter = fadeIn(),
                        exit = ExitTransition.None
                    ) { AppNavigationHost(navController = navController) }

                    DisclaimerDialog(
                        visible = isDisclaimerAccepted?.let { !it } ?: false,
                        onPositiveBtn = viewModel::onDisclaimerAccepted,
                        onNegativeBtn = { this.finishAffinity() }
                    )

                }

            }

        }
    }

}