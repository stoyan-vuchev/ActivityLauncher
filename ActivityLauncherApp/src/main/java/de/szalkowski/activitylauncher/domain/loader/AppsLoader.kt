package de.szalkowski.activitylauncher.domain.loader

import kotlinx.coroutines.flow.Flow

interface AppsLoader {

    val state: Flow<AppsLoaderStateHolder>

    suspend fun loadApps()

}