package de.szalkowski.activitylauncher.presentation.ext

import android.os.Build
import androidx.annotation.StringRes
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.compositionLocalOf
import de.szalkowski.activitylauncher.R
import de.szalkowski.activitylauncher.domain.preferences.theme_mode.ThemeMode

/**
 * CompositionLocal key for providing a [ThemeMode] to the composition.
 **/
val LocalThemeMode = compositionLocalOf { ThemeMode.Default }

/**
 * Returns true if the currently applied [ThemeMode] theme is dark, false otherwise.
 **/
@Composable
fun isDarkModeApplied(): Boolean = when (LocalThemeMode.current) {
    ThemeMode.DARK -> true
    ThemeMode.LIGHT -> false
    else -> if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) isSystemInDarkTheme()
    else true
}

/**
 * Returns the label string resource of the currently applied [ThemeMode].
 **/
val ThemeMode.label: Int
    @StringRes @Composable get() = when (this) {
        ThemeMode.DARK -> R.string.theme_dark
        ThemeMode.LIGHT -> R.string.theme_light
        else -> if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) R.string.theme_default
        else R.string.theme_light
    }