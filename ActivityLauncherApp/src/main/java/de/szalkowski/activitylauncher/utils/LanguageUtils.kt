package de.szalkowski.activitylauncher.utils

import android.app.LocaleManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.provider.Settings
import androidx.annotation.RequiresApi
import de.szalkowski.activitylauncher.R
import java.util.Locale

object LanguageUtils {

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    fun Context.changeAppLanguageFromSystemSettings() {
        Intent(Settings.ACTION_APP_LOCALE_SETTINGS)
            .apply { data = Uri.fromParts("package", packageName, null) }
            .also { startActivity(it) }
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    fun Context.currentAppLanguage(): String {
        val default = resources.getString(R.string.settings_language_default)
        return if (SdkUtils.supportsPerAppLanguages) {
            val locales = getSystemService(LocaleManager::class.java).applicationLocales
            if (!locales.isEmpty) Locale.forLanguageTag(locales.toLanguageTags())
                .displayName.replaceFirstChar {
                    if (it.isLowerCase()) it.titlecase(Locale.ROOT)
                    else it.toString()
                }
            else default
        } else Locale.getDefault().displayName.ifBlank { default }
    }

}