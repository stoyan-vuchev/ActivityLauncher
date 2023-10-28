package de.szalkowski.activitylauncher.presentation.ext

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun rememberListItemShape(
    index: Int,
    lastIndex: Int,
    type: ListItemShapeType,
): Shape {

    val largeRadius = 18.dp
    val smallRadius = 6.dp
    val zeroRadius = 0.dp

    val topCorners: Dp
    val bottomCorners: Dp

    run {

        with(type) {

            topCorners = when (this) {
                is ListItemShapeType.App -> if (index == 0) largeRadius else smallRadius
                is ListItemShapeType.Activity -> zeroRadius
            }

            bottomCorners = when (this) {

                is ListItemShapeType.App -> if (expanded) zeroRadius
                else if (index == lastIndex) largeRadius else smallRadius

                is ListItemShapeType.Activity -> if (index == lastIndex)
                    (if (appItemIndex == appItemLastIndex) largeRadius
                    else smallRadius) else zeroRadius

            }
        }

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

@Immutable
sealed interface ListItemShapeType {

    data class App(val expanded: Boolean) : ListItemShapeType

    data class Activity(
        val appItemIndex: Int,
        val appItemLastIndex: Int,
    ) : ListItemShapeType

}