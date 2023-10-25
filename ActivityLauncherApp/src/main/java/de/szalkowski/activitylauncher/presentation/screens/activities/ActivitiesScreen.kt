package de.szalkowski.activitylauncher.presentation.screens.activities

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.stoyanvuchev.responsive_scaffold.ResponsiveScaffold
import de.szalkowski.activitylauncher.R
import de.szalkowski.activitylauncher.presentation.common.ScreenDestination
import de.szalkowski.activitylauncher.presentation.screens.AppScreens

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ActivitiesScreen(
    onNavigateToScreen: (ScreenDestination) -> Unit
) {

    val topAppBarScrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
    val activitiesListState = rememberLazyListState()

    ResponsiveScaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.surface,
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
                        onClick = remember { { onNavigateToScreen(AppScreens.Settings) } }
                    ) {

                        Icon(
                            imageVector = Icons.Rounded.Settings,
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

            // todo

        }

    }

}