package de.szalkowski.activitylauncher.presentation.screens.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import de.szalkowski.activitylauncher.R
import de.szalkowski.activitylauncher.domain.color_scheme.ColorScheme
import de.szalkowski.activitylauncher.domain.theme_mode.ThemeMode
import de.szalkowski.activitylauncher.domain.use_case.settings.SettingsUseCases
import de.szalkowski.activitylauncher.presentation.screens.settings.SettingsScreenUiAction.SetColorScheme
import de.szalkowski.activitylauncher.presentation.screens.settings.SettingsScreenUiAction.SetDialogType
import de.szalkowski.activitylauncher.presentation.screens.settings.SettingsScreenUiAction.SetHidePrivateActivities
import de.szalkowski.activitylauncher.presentation.screens.settings.SettingsScreenUiAction.SetPrivilegedMode
import de.szalkowski.activitylauncher.presentation.screens.settings.SettingsScreenUiAction.SetThemeMode
import de.szalkowski.activitylauncher.presentation.screens.settings.dialog.SettingsDialogType
import de.szalkowski.activitylauncher.utils.RootUtils
import de.szalkowski.activitylauncher.utils.UiString
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SettingsScreenViewModel(
    private val useCases: SettingsUseCases
) : ViewModel() {

    private val _state = MutableStateFlow(SettingsScreenState())
    val state = _state.asStateFlow()

    private val _uiActionChannel = Channel<SettingsScreenUiAction>()
    val uiActionFlow = _uiActionChannel.receiveAsFlow()

    private val _snackbarChannel = Channel<UiString>()
    val snackbarFlow = _snackbarChannel.receiveAsFlow()

    private var hidePrivateActivitiesDebounceJob: Job? = null
    private var rootPrivilegesDebounceJob: Job? = null
    private var themeModeDebounceJob: Job? = null
    private var colorSchemeDebounceJob: Job? = null

    companion object {
        private const val ARTIFICIAL_DELAY_MILLIS = 100L
        private const val DEBOUNCE_DELAY_MILLIS = 360L
    }

    init {
        viewModelScope.launch {
            delay(ARTIFICIAL_DELAY_MILLIS)
            getArePrivateActivitiesHidden()
            getAreRootPrivilegesEnabled()
        }
    }

    fun onUiAction(uiAction: SettingsScreenUiAction) = with(uiAction) {
        when (this) {
            is SetHidePrivateActivities -> setArePrivateActivitiesHidden(hidden)
            is SetPrivilegedMode -> setAreRootPrivilegesEnabled(enabled)
            is SetDialogType -> setDialogType(dialogType)
            is SetThemeMode -> setThemeMode(themeMode)
            is SetColorScheme -> setColorScheme(colorScheme)
            else -> sendUiAction(this)
        }
    }

    private fun setArePrivateActivitiesHidden(hidden: Boolean) {
        _state.update { it.copy(arePrivateActivitiesHidden = hidden) }
        viewModelScope.launch {
            hidePrivateActivitiesDebounceJob?.cancel()
            hidePrivateActivitiesDebounceJob = this.launch {
                delay(DEBOUNCE_DELAY_MILLIS)
                useCases.privateActivitiesUseCases.setArePrivateActivitiesHidden(hidden)
                hidePrivateActivitiesDebounceJob?.ensureActive()
            }
        }
    }

    private fun setAreRootPrivilegesEnabled(enabled: Boolean) {

        val hasSU = RootUtils.hasSU()
        if (enabled && !hasSU) {
            showSnackbar(UiString.StringResource(R.string.warning_root_check))
            return
        }

        _state.update { it.copy(areRootPrivilegesEnabled = enabled) }
        viewModelScope.launch {
            rootPrivilegesDebounceJob?.cancel()
            rootPrivilegesDebounceJob = this.launch {
                delay(DEBOUNCE_DELAY_MILLIS)
                useCases.privilegedModeUseCases.setPrivilegedMode(enabled)
                rootPrivilegesDebounceJob?.ensureActive()
            }
        }

    }

    private fun setDialogType(dialogType: SettingsDialogType) {
        _state.update { it.copy(settingsDialogType = dialogType) }
    }

    private fun setThemeMode(themeMode: ThemeMode) {
        viewModelScope.launch {
            themeModeDebounceJob?.cancel()
            themeModeDebounceJob = this.launch {
                delay(DEBOUNCE_DELAY_MILLIS)
                useCases.setThemeModeUseCase(themeMode)
                themeModeDebounceJob?.ensureActive()
            }
        }
    }

    private fun setColorScheme(colorScheme: ColorScheme) {
        viewModelScope.launch {
            colorSchemeDebounceJob?.cancel()
            colorSchemeDebounceJob = this.launch {
                delay(DEBOUNCE_DELAY_MILLIS)
                useCases.setColorSchemeUseCase(colorScheme)
                colorSchemeDebounceJob?.ensureActive()
            }
        }
    }

    private suspend fun getArePrivateActivitiesHidden() {
        val hidden = withContext(Dispatchers.IO) {
            useCases.privateActivitiesUseCases.getArePrivateActivitiesHidden()
        }
        _state.update { it.copy(arePrivateActivitiesHidden = hidden) }
    }

    private suspend fun getAreRootPrivilegesEnabled() {
        val enabled = withContext(Dispatchers.IO) {
            useCases.privilegedModeUseCases.getIsPrivilegedModeEnabled()
        }
        _state.update { it.copy(areRootPrivilegesEnabled = enabled) }
    }

    private fun sendUiAction(uiAction: SettingsScreenUiAction) {
        viewModelScope.launch { _uiActionChannel.send(uiAction) }
    }

    private fun showSnackbar(msg: UiString) {
        viewModelScope.launch { _snackbarChannel.send(msg) }
    }

}