package de.szalkowski.activitylauncher.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import de.szalkowski.activitylauncher.domain.preferences.AppPreferences
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivityViewModel(
    private val preferences: AppPreferences
) : ViewModel() {

    private val _state = MutableStateFlow(MainActivityState())
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            delay(250L)
            getThemeMode()
            getColorScheme()
            getIsDisclaimerAccepted()
        }
    }

    fun onDisclaimerAccepted() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) { preferences.setIsDisclaimerAccepted() }
            _state.update { it.copy(isDisclaimerAccepted = true) }
        }
    }

    private fun getThemeMode() {
        viewModelScope.launch {
            val themeMode = withContext(Dispatchers.IO) { preferences.getThemeMode() }
            _state.update { it.copy(themeMode = themeMode) }
        }
    }

    private fun getColorScheme() {
        viewModelScope.launch {
            val colorScheme = withContext(Dispatchers.IO) { preferences.getColorScheme() }
            _state.update { it.copy(colorScheme = colorScheme) }
        }
    }

    private fun getIsDisclaimerAccepted() {
        viewModelScope.launch {
            val accepted = withContext(Dispatchers.IO) { preferences.getIsDisclaimerAccepted() }
            _state.update { it.copy(isDisclaimerAccepted = accepted) }
        }
    }

}