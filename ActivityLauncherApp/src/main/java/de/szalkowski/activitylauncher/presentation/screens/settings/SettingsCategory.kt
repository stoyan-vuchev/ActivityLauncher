package de.szalkowski.activitylauncher.presentation.screens.settings

import androidx.annotation.StringRes
import androidx.compose.runtime.Immutable
import de.szalkowski.activitylauncher.R

@Immutable
sealed class SettingsCategory(@StringRes val label: Int) {

    data object Advanced : SettingsCategory(label = R.string.category_title_advanced)
    data object Personalization : SettingsCategory(label = R.string.category_title_personalization)
    data object About : SettingsCategory(label = R.string.category_title_about)

    companion object {
        val categories: List<SettingsCategory> get() = listOf(Advanced, Personalization, About)
    }

}