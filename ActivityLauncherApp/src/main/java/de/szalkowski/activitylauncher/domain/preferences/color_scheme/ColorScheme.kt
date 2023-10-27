package de.szalkowski.activitylauncher.domain.preferences.color_scheme

import android.os.Build
import androidx.annotation.RequiresApi

/**
 * Constants for working with the application color scheme.
 **/
enum class ColorScheme {

    /**
     * A [ColorScheme] constant representing the System Dynamic Colors (Only for API >= 31).
     **/
    @RequiresApi(Build.VERSION_CODES.S)
    DYNAMIC,

    /**
     * A [ColorScheme] constant representing the default application colors.
     **/
    APP_DEFAULT,

    /**
     * A [ColorScheme] constant representing the Material3 lavender colors.
     **/
    M3_LAVENDER;

    companion object {

        /**
         * Returns the default [ColorScheme].
         * - For API >= 31: [ColorScheme.DYNAMIC]
         * - For API < 31: [ColorScheme.APP_DEFAULT]
         **/
        val Default = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) DYNAMIC
        else APP_DEFAULT

    }

}