package de.szalkowski.activitylauncher.presentation.screens.activities

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import de.szalkowski.activitylauncher.domain.loader.AppsLoader
import de.szalkowski.activitylauncher.domain.model.AppModel
import de.szalkowski.activitylauncher.domain.use_case.activities.ActivitiesScreenUseCases
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
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

    private val _expandedItemsIndexList = MutableStateFlow(mutableStateListOf<Int>())
    val state = combine(
        flow = useCases.observeArePrivateActivitiesHidden(),
        flow2 = appsLoader.state,
        flow3 = _expandedItemsIndexList
    ) { hidden, appsLoaderState, expandedItemsIndexList ->

        ActivitiesScreenState(
            arePrivateActivitiesHidden = hidden,
            isLoading = appsLoaderState.isLoading,
            loadedApps = appsLoaderState.loadedApps,
            totalApps = appsLoaderState.totalApps,
            appsList = appsLoaderState.appsList.map { app ->

                val activitiesList = app.activitiesList.filter { activity ->
                    if (hidden) !activity.isPrivate
                    else true
                }

                AppModel(
                    name = app.name,
                    packageName = app.packageName,
                    icon = app.icon,
                    activitiesCount = activitiesList.size,
                    activitiesList = activitiesList.take(
                        activitiesList.size.coerceAtMost(10)
                    )
                )

            },
            error = appsLoaderState.error,
            expandedItemsIndexList = expandedItemsIndexList
        )

    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000L),
        initialValue = ActivitiesScreenState()
    )

    fun onUiAction(uiAction: ActivitiesScreenUiAction) = with(uiAction) {
        when (this) {
            is ActivitiesScreenUiAction.Navigate -> sendUiAction(this)
            is ActivitiesScreenUiAction.ExpandOrCollapseItem -> expandOrCollapseItem(index)
            is ActivitiesScreenUiAction.Reload -> loadApps()
        }
    }

    private fun expandOrCollapseItem(index: Int) {
        _expandedItemsIndexList.value.find { it == index }?.let {
            _expandedItemsIndexList.value.remove(it)
        } ?: _expandedItemsIndexList.value.add(index)
    }

    private fun loadApps() {
        viewModelScope.launch { appsLoader.loadApps() }
    }

    private fun sendUiAction(uiAction: ActivitiesScreenUiAction) {
        viewModelScope.launch { uiActionChannel.send(uiAction) }
    }

}