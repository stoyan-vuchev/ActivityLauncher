package de.szalkowski.activitylauncher.domain.model

import android.graphics.Bitmap
import androidx.compose.runtime.Stable

@Stable
data class ActivityModel(
    val name: String,
    val packageName: String,
    val icon: Bitmap,
    val isPrivate: Boolean
)