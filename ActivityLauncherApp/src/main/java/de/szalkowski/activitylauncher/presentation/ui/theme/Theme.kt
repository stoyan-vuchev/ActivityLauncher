package de.szalkowski.activitylauncher.presentation.ui.theme

import android.os.Build
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.platform.LocalContext
import com.stoyanvuchev.systemuibarstweaker.ProvideSystemUIBarsTweaker
import com.stoyanvuchev.systemuibarstweaker.SystemBarStyle
import com.stoyanvuchev.systemuibarstweaker.SystemUIBarsConfiguration
import de.szalkowski.activitylauncher.domain.color_scheme.ColorScheme
import de.szalkowski.activitylauncher.domain.theme_mode.ThemeMode
import de.szalkowski.activitylauncher.presentation.ext.LocalThemeMode
import de.szalkowski.activitylauncher.presentation.ext.isDarkModeApplied

@Composable
fun ActivityLauncherTheme(
    themeMode: ThemeMode = ThemeMode.Default,
    colorScheme: ColorScheme = ColorScheme.Default,
    content: @Composable () -> Unit
) {

    CompositionLocalProvider(LocalThemeMode provides themeMode) {

        val isDarkModeApplied = isDarkModeApplied()
        val colors = when (colorScheme) {

            ColorScheme.APP_DEFAULT -> if (isDarkModeApplied) DarkColors.asColorScheme()
            else LightColors.asColorScheme()

            ColorScheme.M3_LAVENDER -> if (isDarkModeApplied) darkColorScheme()
            else lightColorScheme()

            else -> if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {

                val context = LocalContext.current

                if (isDarkModeApplied) dynamicDarkColorScheme(context)
                else dynamicLightColorScheme(context)

            } else LightColors.asColorScheme()

        }

        ProvideSystemUIBarsTweaker(
            initialConfiguration = SystemUIBarsConfiguration.default(
                statusBarStyle = SystemBarStyle.defaultStatusBarStyle(
                    darkIcons = !isDarkModeApplied
                ),
                navigationBarStyle = SystemBarStyle.defaultNavigationBarStyle(
                    darkIcons = !isDarkModeApplied
                )
            )
        ) {

            MaterialTheme(
                colorScheme = colors,
                typography = Typography,
                content = content
            )

        }

    }

}