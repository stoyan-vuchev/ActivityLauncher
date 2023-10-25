package de.szalkowski.activitylauncher.domain.use_case.main_activity

data class MainActivityUseCases(
    val disclaimerUseCases: MainActivityDisclaimerUseCases,
    val observeThemeMode: MainActivityObserveThemeModeUseCase,
    val observeColorScheme: MainActivityObserveColorSchemeUseCase
)