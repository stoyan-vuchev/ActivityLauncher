package de.szalkowski.activitylauncher.presentation.screens.settings

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import de.szalkowski.activitylauncher.R
import de.szalkowski.activitylauncher.presentation.screens.settings.SettingsScreenUiAction.*
import de.szalkowski.activitylauncher.presentation.screens.settings.dialog.SettingsDialog

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    screenState: SettingsScreenState,
    snackbarHostState: SnackbarHostState,
    onUiAction: (SettingsScreenUiAction) -> Unit
) {

    val topAppBarScrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()
    val settingsItemsListState = rememberLazyListState()
    val settingsItemsList by rememberSettingsScreenItemsList(
        screenState = screenState,
        onUiAction = onUiAction
    )

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {

            LargeTopAppBar(
                title = {

                    Text(
                        modifier = Modifier.padding(start = 8.dp),
                        text = stringResource(id = R.string.activity_settings)
                    )

                },
                navigationIcon = {

                    IconButton(
                        modifier = Modifier.padding(start = 4.dp),
                        onClick = remember { { onUiAction(NavigateUp) } }
                    ) {

                        Icon(
                            imageVector = Icons.Rounded.ArrowBack,
                            contentDescription = null
                        )

                    }

                },
                scrollBehavior = topAppBarScrollBehavior
            )

        },
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    ) { paddingValues ->

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .nestedScroll(connection = topAppBarScrollBehavior.nestedScrollConnection),
            state = settingsItemsListState,
            contentPadding = paddingValues
        ) {

            item(
                key = "settings_top_spacer",
                content = { Spacer(modifier = Modifier.height(16.dp)) }
            )

            SettingsCategory.categories.forEachIndexed { i, category ->

                if (i > 0) {

                    item(
                        key = "settings_category_${category}_separator",
                        content = {
                            Divider(
                                modifier = Modifier
                                    .padding(vertical = 16.dp)
                                    .clip(RoundedCornerShape(100))
                                    .padding(horizontal = 24.dp)
                            )
                        }
                    )

                }

                item(
                    key = "settings_category_label_$category",
                    content = {

                        Text(
                            modifier = Modifier.padding(horizontal = 24.dp, vertical = 16.dp),
                            text = stringResource(id = category.label),
                            style = MaterialTheme.typography.labelLarge,
                            color = MaterialTheme.colorScheme.primary
                        )

                    }
                )

                items(
                    items = settingsItemsList.filter { it.category == category },
                    key = { "settings_category_${category}_item_$it" },
                    itemContent = { it.content() }
                )

            }

            item(
                key = "settings_bottom_spacer",
                content = { Spacer(modifier = Modifier.height(48.dp)) }
            )

        }

    }

    // A composable with the logic to display
    // the currently invoked [PreferenceDialog].
    SettingsDialog(
        dialogType = screenState.settingsDialogType,
        onUiAction = onUiAction
    )

}