package de.szalkowski.activitylauncher.presentation.screens.activities

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material.icons.rounded.ErrorOutline
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import de.szalkowski.activitylauncher.R
import de.szalkowski.activitylauncher.presentation.ext.ListItemShapeType
import de.szalkowski.activitylauncher.presentation.ext.rememberListItemShape
import de.szalkowski.activitylauncher.presentation.screens.AppScreens
import de.szalkowski.activitylauncher.presentation.screens.activities.ActivitiesScreenUiAction.Navigate
import de.szalkowski.activitylauncher.presentation.screens.activities.ActivitiesScreenUiAction.Reload
import de.szalkowski.activitylauncher.utils.UiString

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun ActivitiesScreen(
    screenState: ActivitiesScreenState,
    onUiAction: (ActivitiesScreenUiAction) -> Unit
) {

    val topAppBarScrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
    val activitiesListState = rememberLazyListState()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {

            TopAppBar(
                title = {

                    Text(
                        modifier = Modifier.padding(start = 8.dp),
                        text = stringResource(id = R.string.app_name)
                    )

                },
                actions = {

                    IconButton(onClick = { /*TODO*/ }) {

                        Icon(
                            imageVector = Icons.Rounded.Search,
                            contentDescription = null
                        )

                    }

                    IconButton(
                        modifier = Modifier.padding(end = 4.dp),
                        onClick = remember { { onUiAction(Navigate(AppScreens.Settings)) } }
                    ) {

                        Icon(
                            imageVector = Icons.Outlined.Settings,
                            contentDescription = null
                        )

                    }

                },
                scrollBehavior = topAppBarScrollBehavior
            )

        }
    ) { paddingValues ->

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .nestedScroll(connection = topAppBarScrollBehavior.nestedScrollConnection),
            state = activitiesListState,
            contentPadding = paddingValues
        ) {

            val expandedItemsList = screenState.expandedItemsIndexList

            item(key = "top_spacer") { Spacer(modifier = Modifier.height(16.dp)) }

            screenState.appsList.forEachIndexed { appIndex, app ->

                val expanded = expandedItemsList.find { it == appIndex }?.let { true } == true
                val appLastIndex = screenState.appsList.size - 1

                val activitiesListSize = app.activitiesList.size
                val activitiesLastIndex = activitiesListSize - 1

                item(key = "app_$app") {

                    val appItemShape = rememberListItemShape(
                        index = appIndex,
                        lastIndex = appLastIndex,
                        type = ListItemShapeType.App(expanded = expanded)
                    )

                    ActivitiesScreenItem(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp)
                            .animateItemPlacement(),
                        app = app,
                        shape = appItemShape,
                        index = appIndex,
                        expanded = expanded,
                        onUiAction = onUiAction
                    )

                }

                if (expanded) {

                    itemsIndexed(
                        items = app.activitiesList,
                        key = { _, activity -> "app_${app}_activity_$activity" }
                    ) { activityIndex, activity ->

                        val activityItemShape = rememberListItemShape(
                            index = activityIndex,
                            lastIndex = activitiesLastIndex,
                            type = ListItemShapeType.Activity(
                                appItemIndex = appIndex,
                                appItemLastIndex = appLastIndex
                            )
                        )

                        ActivitiesScreenItemActivityItem(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp)
                                .animateItemPlacement(),
                            index = activityIndex,
                            lastIndex = activitiesLastIndex,
                            activitiesCount = app.activitiesCount,
                            activity = activity,
                            shape = activityItemShape
                        )

                    }

                }

                item(key = "app_${app.packageName}_separator") {
                    Spacer(modifier = Modifier.height(4.dp))
                }

            }

            item(key = "bottom_spacer") { Spacer(modifier = Modifier.height(48.dp)) }

        }

        AnimatedVisibility(
            modifier = Modifier.fillMaxSize(),
            visible = screenState.error != UiString.EmptyString,
            enter = fadeIn(),
            exit = fadeOut()
        ) {

            val error by rememberUpdatedState(screenState.error.asString())

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = MaterialTheme.colorScheme.surface)
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {

                Icon(
                    modifier = Modifier.size(32.dp),
                    imageVector = Icons.Rounded.ErrorOutline,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.error
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    modifier = Modifier.padding(horizontal = 32.dp),
                    text = stringResource(id = R.string.error) + "\n$error",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(32.dp))

                Button(
                    onClick = remember { { onUiAction(Reload) } }
                ) {

                    Text(text = stringResource(id = R.string.error_tasks_reload))

                }

            }

        }

    }

    ActivitiesScreenLoadingDialog(
        visible = screenState.isLoading,
        totalApps = screenState.totalApps,
        loadedApps = screenState.loadedApps
    )

}