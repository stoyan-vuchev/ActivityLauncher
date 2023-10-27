package de.szalkowski.activitylauncher.presentation.screens.activities

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import de.szalkowski.activitylauncher.domain.loader.AppsLoader
import de.szalkowski.activitylauncher.domain.model.AppModel
import de.szalkowski.activitylauncher.domain.use_case.activities.ActivitiesScreenUseCases
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ActivitiesScreenViewModel(
    useCases: ActivitiesScreenUseCases,
    private val appsLoader: AppsLoader
) : ViewModel() {

    init {
        // Load the apps list as soon
        // as the view model is initialized.
        loadApps()
    }

    private val uiActionChannel = Channel<ActivitiesScreenUiAction>()
    val uiActionFlow = uiActionChannel.receiveAsFlow()

    val state = combine(
        flow = useCases.observeArePrivateActivitiesHidden(),
        flow2 = appsLoader.state
    ) { hidden, appsLoaderState ->

        ActivitiesScreenState(
            arePrivateActivitiesHidden = hidden,
            isLoading = appsLoaderState.isLoading,
            loadedApps = appsLoaderState.loadedApps,
            totalApps = appsLoaderState.totalApps,
            appsList = appsLoaderState.appsList.map { app ->

                AppModel(
                    name = app.name,
                    packageName = app.packageName,
                    icon = app.icon,
                    activities = app.activities.filter { activity ->
                        if (hidden) !activity.isPrivate
                        else true
                    }
                )

            },
            error = appsLoaderState.error
        )

    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000L),
        initialValue = ActivitiesScreenState()
    )

    fun onUiAction(uiAction: ActivitiesScreenUiAction) = with(uiAction) {
        when (this) {
            is ActivitiesScreenUiAction.Navigate -> sendUiAction(this)
            is ActivitiesScreenUiAction.Reload -> loadApps()
        }
    }

    private fun loadApps() {
        viewModelScope.launch { appsLoader.loadApps() }
    }

    private fun sendUiAction(uiAction: ActivitiesScreenUiAction) {
        viewModelScope.launch { uiActionChannel.send(uiAction) }
    }

}