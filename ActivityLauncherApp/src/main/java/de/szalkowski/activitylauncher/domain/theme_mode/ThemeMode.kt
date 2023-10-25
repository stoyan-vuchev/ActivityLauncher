package de.szalkowski.activitylauncher.domain.theme_mode

import android.os.Build
import androidx.annotation.RequiresApi

/**
 * Constants for working with the application theme mode.
 **/
enum class ThemeMode {

    /**
     * A [ThemeMode] constant representing the System Theme. (Only for API >= 28).
     **/
    @RequiresApi(Build.VERSION_CODES.P)
    SYSTEM,

    /**
     * A [ThemeMode] constant representing the dark theme.
     **/
    DARK,

    /**
     * A [ThemeMode] constant representing the light theme.
     **/
    LIGHT;

    companion object {

        /**
         * Returns the default [ThemeMode].
         * - For API >= 28: [ThemeMode.SYSTEM]
         * - For API < 28: [ThemeMode.DARK]
         **/
        val Default = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) SYSTEM
        else DARK

    }

}