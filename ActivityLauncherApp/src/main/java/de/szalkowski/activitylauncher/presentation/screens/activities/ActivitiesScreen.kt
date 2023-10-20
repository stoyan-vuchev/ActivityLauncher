package de.szalkowski.activitylauncher.presentation.screens.activities

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import com.stoyanvuchev.responsive_scaffold.ResponsiveScaffold
import de.szalkowski.activitylauncher.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ActivitiesScreen() {

    val topAppBarScrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
    val activitiesListState = rememberLazyListState()

    ResponsiveScaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {

            TopAppBar(
                title = { Text(text = stringResource(id = R.string.app_name)) },
                actions = {

                    IconButton(onClick = { /*TODO*/ }) {

                        Icon(
                            imageVector = Icons.Rounded.Search,
                            contentDescription = null
                        )

                    }

                    IconButton(onClick = { /*TODO*/ }) {

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