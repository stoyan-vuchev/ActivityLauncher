package de.szalkowski.activitylauncher.domain.model

import android.graphics.Bitmap
import androidx.compose.runtime.Stable

@Stable
data class AppModel(
    val name: String,
    val packageName: String,
    val icon: Bitmap,
    val activitiesCount: Int,
    val activitiesList: List<ActivityModel>
)