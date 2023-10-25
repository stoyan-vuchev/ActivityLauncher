package de.szalkowski.activitylauncher.utils

import android.os.Build

object SdkUtils {

    val supportsPerAppLanguages: Boolean
        get() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU

}