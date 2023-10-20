package de.szalkowski.activitylauncher.presentation.ext

import android.os.Build
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import de.szalkowski.activitylauncher.R
import de.szalkowski.activitylauncher.domain.color_scheme.ColorScheme

/**
 * Returns the label string resource of the currently applied [ColorScheme].
 **/
val ColorScheme.label: String
    @Composable get() = stringResource(
        id = when (this) {
            ColorScheme.APP_DEFAULT -> R.string.color_scheme_app_default
            ColorScheme.M3_LAVENDER -> R.string.color_scheme_m3_lavender
            else -> if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) R.string.color_scheme_dynamic
            else R.string.color_scheme_app_default
        }
    )