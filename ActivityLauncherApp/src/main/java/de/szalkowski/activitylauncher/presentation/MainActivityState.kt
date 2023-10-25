package de.szalkowski.activitylauncher.presentation

import androidx.compose.runtime.Stable
import de.szalkowski.activitylauncher.domain.color_scheme.ColorScheme
import de.szalkowski.activitylauncher.domain.theme_mode.ThemeMode

@Stable
data class MainActivityState(
    val themeMode: ThemeMode = ThemeMode.Default,
    val colorScheme: ColorScheme = ColorScheme.Default,
    val isDisclaimerAccepted: Boolean? = null
)