package de.szalkowski.activitylauncher.presentation.ext

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun rememberListItemShape(
    index: Int,
    lastIndex: Int
): Shape {

    val largeRadius = 18.dp
    val smallRadius = 6.dp

    val topCorners: Dp
    val bottomCorners: Dp

    run {
        topCorners = if (index == 0) largeRadius else smallRadius
        bottomCorners = if (index == lastIndex) largeRadius else smallRadius
    }

    return remember(topCorners, bottomCorners) {
        RoundedCornerShape(
            topStart = topCorners,
            topEnd = topCorners,
            bottomStart = bottomCorners,
            bottomEnd = bottomCorners
        )
    }

}