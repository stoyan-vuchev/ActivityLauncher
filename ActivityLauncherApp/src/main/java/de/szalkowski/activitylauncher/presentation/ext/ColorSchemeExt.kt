package de.szalkowski.activitylauncher.presentation.ext

import android.os.Build
import androidx.annotation.StringRes
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.platform.LocalContext
import de.szalkowski.activitylauncher.R
import de.szalkowski.activitylauncher.domain.preferences.color_scheme.ColorScheme
import de.szalkowski.activitylauncher.presentation.ui.theme.DarkColors
import de.szalkowski.activitylauncher.presentation.ui.theme.LightColors
import de.szalkowski.activitylauncher.presentation.ui.theme.asColorScheme

/**
 * CompositionLocal key for providing a [ColorScheme] to the composition.
 **/
val LocalColorScheme = compositionLocalOf { ColorScheme.Default }

/**
 * Returns a [androidx.compose.material3.ColorScheme] from a [ColorScheme].
 **/
@Stable
@Composable
fun ColorScheme.materialColorScheme(darkTheme: Boolean) = when (this) {

    ColorScheme.APP_DEFAULT -> if (darkTheme) DarkColors.asColorScheme()
    else LightColors.asColorScheme()

    ColorScheme.M3_LAVENDER -> if (darkTheme) darkColorScheme()
    else lightColorScheme()

    else -> if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {

        val context = LocalContext.current

        if (darkTheme) dynamicDarkColorScheme(context)
        else dynamicLightColorScheme(context)

    } else LightColors.asColorScheme()

}

/**
 * Returns the label string resource of the currently applied [ColorScheme].
 **/
val ColorScheme.label: Int
    @StringRes @Composable get() = when (this) {
        ColorScheme.APP_DEFAULT -> R.string.color_scheme_app_default
        ColorScheme.M3_LAVENDER -> R.string.color_scheme_m3_lavender
        else -> if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) R.string.color_scheme_dynamic
        else R.string.color_scheme_app_default
    }