package de.szalkowski.activitylauncher.presentation.ui.theme

import androidx.compose.material3.ColorScheme
import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color

/**
 * The default application light [ThemeColors].
 **/
object LightColors : ThemeColors {

    // Primary colors

    override val primary = Color(0xFF006782)
    override val onPrimary = Color(0xFFFFFFFF)
    override val primaryContainer = Color(0xFFBBE9FF)
    override val onPrimaryContainer = Color(0xFF001F29)

    // Secondary colors

    override val secondary = Color(0xFF4C616B)
    override val onSecondary = Color(0xFFFFFFFF)
    override val secondaryContainer = Color(0xFFCFE6F2)
    override val onSecondaryContainer = Color(0xFF081E27)

    // Tertiary colors

    override val tertiary = Color(0xFF5C5B7D)
    override val onTertiary = Color(0xFFFFFFFF)
    override val tertiaryContainer = Color(0xFFE2DFFF)
    override val onTertiaryContainer = Color(0xFF191837)

    // Error colors

    override val error = Color(0xFFBA1A1A)
    override val onError = Color(0xFFFFFFFF)
    override val errorContainer = Color(0xFFFFDAD6)
    override val onErrorContainer = Color(0xFF410002)

    // Background colors

    override val background = Color(0xFFFBFCFE)
    override val onBackground = Color(0xFF191C1E)

    // Surface colors

    override val surfaceTint = Color(0xFF006782)
    override val surface = Color(0xFFFBFCFE)
    override val onSurface = Color(0xFF191C1E)
    override val surfaceVariant = Color(0xFFDCE4E9)
    override val onSurfaceVariant = Color(0xFF40484C)

    // Outline colors

    override val outline = Color(0xFF70787D)
    override val outlineVariant = Color(0xFFC0C8CC)

    // Inversion colors

    override val inverseOnSurface = Color(0xFFEFF1F3)
    override val inverseSurface = Color(0xFF2E3132)
    override val inversePrimary = Color(0xFF61D4FF)

    // Other colors

    override val scrim = Color(0xFF000000)

}

/**
 * The default application dark [ThemeColors].
 **/
object DarkColors : ThemeColors {

    // Primary colors

    override val primary = Color(0xFF61D4FF)
    override val onPrimary = Color(0xFF003545)
    override val primaryContainer = Color(0xFF004D63)
    override val onPrimaryContainer = Color(0xFFBBE9FF)

    // Secondary colors

    override val secondary = Color(0xFFB4CAD5)
    override val onSecondary = Color(0xFF1E333C)
    override val secondaryContainer = Color(0xFF354A53)
    override val onSecondaryContainer = Color(0xFFCFE6F2)

    // Tertiary colors

    override val tertiary = Color(0xFFC5C3EA)
    override val onTertiary = Color(0xFF2E2D4D)
    override val tertiaryContainer = Color(0xFF444364)
    override val onTertiaryContainer = Color(0xFFE2DFFF)

    // Error colors

    override val error = Color(0xFFFFB4AB)
    override val onError = Color(0xFF690005)
    override val errorContainer = Color(0xFF93000A)
    override val onErrorContainer = Color(0xFFFFDAD6)

    // Background colors

    override val background = Color(0xFF191C1E)
    override val onBackground = Color(0xFFE1E3E4)

    // Surface colors

    override val surfaceTint = Color(0xFF61D4FF)
    override val surface = Color(0xFF191C1E)
    override val onSurface = Color(0xFFE1E3E4)
    override val surfaceVariant = Color(0xFF40484C)
    override val onSurfaceVariant = Color(0xFFC0C8CC)

    // Outline colors

    override val outline = Color(0xFF8A9296)
    override val outlineVariant = Color(0xFF40484C)

    // Inversion colors

    override val inverseOnSurface = Color(0xFF191C1E)
    override val inverseSurface = Color(0xFFE1E3E4)
    override val inversePrimary = Color(0xFF006782)

    // Other colors

    override val scrim = Color(0xFF000000)

}

/**
 * An abstraction with the necessary colors for a particular [ColorScheme].
 **/
@Immutable
interface ThemeColors {

    // Primary colors

    val primary: Color
    val onPrimary: Color
    val primaryContainer: Color
    val onPrimaryContainer: Color

    // Secondary colors

    val secondary: Color
    val onSecondary: Color
    val secondaryContainer: Color
    val onSecondaryContainer: Color

    // Tertiary colors

    val tertiary: Color
    val onTertiary: Color
    val tertiaryContainer: Color
    val onTertiaryContainer: Color

    // Error colors

    val error: Color
    val onError: Color
    val errorContainer: Color
    val onErrorContainer: Color

    // Background colors

    val background: Color
    val onBackground: Color

    // Surface colors

    val surfaceTint: Color
    val surface: Color
    val onSurface: Color
    val surfaceVariant: Color
    val onSurfaceVariant: Color

    // Outline colors

    val outline: Color
    val outlineVariant: Color

    // Inversion colors

    val inverseOnSurface: Color
    val inverseSurface: Color
    val inversePrimary: Color

    // Other colors

    val scrim: Color

}

/**
 * A helper function used for converting [ThemeColors] to a [ColorScheme].
 **/
fun ThemeColors.asColorScheme() = ColorScheme(
    primary = primary,
    onPrimary = onPrimary,
    primaryContainer = primaryContainer,
    onPrimaryContainer = onPrimaryContainer,
    secondary = secondary,
    onSecondary = onSecondary,
    secondaryContainer = secondaryContainer,
    onSecondaryContainer = onSecondaryContainer,
    tertiary = tertiary,
    onTertiary = onTertiary,
    tertiaryContainer = tertiaryContainer,
    onTertiaryContainer = onTertiaryContainer,
    error = error,
    onError = onError,
    errorContainer = errorContainer,
    onErrorContainer = onErrorContainer,
    background = background,
    onBackground = onBackground,
    surfaceTint = surfaceTint,
    surface = surface,
    onSurface = onSurface,
    surfaceVariant = surfaceVariant,
    onSurfaceVariant = onSurfaceVariant,
    outline = outline,
    outlineVariant = outlineVariant,
    inverseOnSurface = inverseOnSurface,
    inverseSurface = inverseSurface,
    inversePrimary = inversePrimary,
    scrim = scrim
)

// The color used to generate the [ColorScheme] on: m3.material.io/theme-builder
// val seed = Color(0xFF5F7B88)