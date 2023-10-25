package de.szalkowski.activitylauncher.presentation.ui.theme

import android.os.Build
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.graphics.Color
import com.stoyanvuchev.systemuibarstweaker.ProvideSystemUIBarsTweaker
import com.stoyanvuchev.systemuibarstweaker.rememberSystemUIBarsTweaker
import de.szalkowski.activitylauncher.domain.color_scheme.ColorScheme
import de.szalkowski.activitylauncher.domain.theme_mode.ThemeMode
import de.szalkowski.activitylauncher.presentation.ext.LocalColorScheme
import de.szalkowski.activitylauncher.presentation.ext.LocalThemeMode
import de.szalkowski.activitylauncher.presentation.ext.isDarkModeApplied
import de.szalkowski.activitylauncher.presentation.ext.materialColorScheme

@Composable
fun ActivityLauncherTheme(
    themeMode: ThemeMode = ThemeMode.Default,
    colorScheme: ColorScheme = ColorScheme.Default,
    content: @Composable () -> Unit
) {

    CompositionLocalProvider(
        LocalThemeMode provides themeMode,
        LocalColorScheme provides colorScheme
    ) {

        val systemUIBarsTweaker = rememberSystemUIBarsTweaker()
        val isDarkModeApplied = isDarkModeApplied()
        val colors = LocalColorScheme.current.materialColorScheme(isDarkModeApplied)

        DisposableEffect(
            systemUIBarsTweaker,
            isDarkModeApplied,
            colors
        ) {

            systemUIBarsTweaker.tweakSystemBarsStyle(
                statusBarStyle = systemUIBarsTweaker.statusBarStyle.copy(
                    darkIcons = !isDarkModeApplied
                ),
                navigationBarStyle = systemUIBarsTweaker.navigationBarStyle.copy(
                    darkIcons = !isDarkModeApplied,
                    enforceContrast = false,
                    color = if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) Color.Black
                    else colors.surface.copy(alpha = .9f)
                )
            )

            onDispose {}
        }

        ProvideSystemUIBarsTweaker(
            systemUIBarsTweaker = systemUIBarsTweaker
        ) {

            MaterialTheme(
                colorScheme = colors,
                content = content
            )

        }

    }

}