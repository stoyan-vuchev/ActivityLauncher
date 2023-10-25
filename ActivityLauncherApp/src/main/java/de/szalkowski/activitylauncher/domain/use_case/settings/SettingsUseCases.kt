package de.szalkowski.activitylauncher.domain.use_case.settings

data class SettingsUseCases(
    val privateActivitiesUseCases: SettingsHidePrivateActivitiesUseCases,
    val privilegedModeUseCases: SettingsEnablePrivilegedModeUseCases,
    val setThemeModeUseCase: SettingsSetThemeModeUseCase,
    val setColorSchemeUseCase: SettingsSetColorSchemeUseCase
)