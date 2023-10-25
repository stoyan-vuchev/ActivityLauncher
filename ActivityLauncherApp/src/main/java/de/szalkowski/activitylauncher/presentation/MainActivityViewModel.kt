package de.szalkowski.activitylauncher.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import de.szalkowski.activitylauncher.domain.color_scheme.ColorScheme
import de.szalkowski.activitylauncher.domain.theme_mode.ThemeMode
import de.szalkowski.activitylauncher.domain.use_case.main_activity.MainActivityUseCases
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class MainActivityViewModel(
    private val useCases: MainActivityUseCases
) : ViewModel() {

    companion object {
        private const val STOP_TIMEOUT_MILLIS = 5_000L
    }

    val isDisclaimerAccepted: StateFlow<Boolean?> = useCases.disclaimerUseCases.observe()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(STOP_TIMEOUT_MILLIS),
            initialValue = null
        )

    val themeMode: StateFlow<ThemeMode> = useCases.observeThemeMode()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(STOP_TIMEOUT_MILLIS),
            initialValue = ThemeMode.Default
        )

    val colorScheme: StateFlow<ColorScheme> = useCases.observeColorScheme()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(STOP_TIMEOUT_MILLIS),
            initialValue = ColorScheme.Default
        )

    fun onDisclaimerAccepted() {
        viewModelScope.launch { useCases.disclaimerUseCases.accept() }
    }

}