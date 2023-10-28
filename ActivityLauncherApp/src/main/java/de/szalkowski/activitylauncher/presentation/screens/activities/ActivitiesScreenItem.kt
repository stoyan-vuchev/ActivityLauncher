package de.szalkowski.activitylauncher.presentation.screens.activities

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.rounded.KeyboardArrowDown
import androidx.compose.material.icons.rounded.MoreVert
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import de.szalkowski.activitylauncher.domain.model.ActivityModel
import de.szalkowski.activitylauncher.domain.model.AppModel
import de.szalkowski.activitylauncher.presentation.screens.activities.ActivitiesScreenUiAction.ExpandOrCollapseItem

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ActivitiesScreenItem(
    modifier: Modifier = Modifier,
    app: AppModel,
    shape: Shape,
    index: Int,
    expanded: Boolean,
    onUiAction: (ActivitiesScreenUiAction) -> Unit
) {

    val onClickLambda = remember { { onUiAction(ExpandOrCollapseItem(index)) } }

    val arrowRotationZ by animateFloatAsState(
        targetValue = if (!expanded) 0f else 180f,
        label = ""
    )

    val containerColor by animateColorAsState(
        targetValue = MaterialTheme.colorScheme.surfaceColorAtElevation(
            elevation = if (!expanded) 1.dp else 6.dp
        ),
        label = ""
    )

    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(containerColor = containerColor),
        shape = shape
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable(onClick = onClickLambda)
                .padding(horizontal = 4.dp, vertical = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            IconButton(onClick = onClickLambda) {

                Icon(
                    modifier = Modifier.graphicsLayer(rotationZ = arrowRotationZ),
                    imageVector = Icons.Rounded.KeyboardArrowDown,
                    contentDescription = null
                )

            }

            Image(
                modifier = Modifier.size(40.dp),
                bitmap = app.icon.asImageBitmap(),
                contentDescription = null
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column(modifier = Modifier.weight(1f)) {

                Text(
                    modifier = Modifier.basicMarquee(),
                    text = app.name,
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.onSurface,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1
                )

                Spacer(modifier = Modifier.height(2.dp))

                Text(
                    text = app.packageName,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = .8f),
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1
                )

            }

            Spacer(modifier = Modifier.width(8.dp))

            IconButton(
                onClick = remember { { /* TODO */ } }
            ) {

                Icon(
                    imageVector = Icons.Rounded.MoreVert,
                    contentDescription = null
                )

            }

        }

    }

}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ActivitiesScreenItemActivityItem(
    modifier: Modifier = Modifier,
    index: Int,
    lastIndex: Int,
    activitiesCount: Int,
    activity: ActivityModel,
    shape: Shape
    // todo
) {

    val isFirstIndex by rememberUpdatedState(index == 0)

    Column(
        modifier = modifier
            .clip(shape)
            .background(MaterialTheme.colorScheme.surfaceColorAtElevation(6.dp))
    ) {

        if (!isFirstIndex) {
            Divider(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(shape = RoundedCornerShape(100))
                    .padding(horizontal = 24.dp)
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable(onClick = remember { { /* TODO */ } })
                .padding(horizontal = 4.dp, vertical = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Spacer(modifier = Modifier.width(16.dp))

            Image(
                modifier = Modifier.size(32.dp),
                bitmap = activity.icon.asImageBitmap(),
                contentDescription = null
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column(modifier = Modifier.weight(1f)) {

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    if (activity.isPrivate) {

                        Icon(
                            modifier = Modifier.size(16.dp),
                            imageVector = Icons.Outlined.Lock,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.primary
                        )

                        Spacer(modifier = Modifier.width(8.dp))

                    }

                    Text(
                        modifier = Modifier.basicMarquee(),
                        text = activity.name,
                        style = MaterialTheme.typography.titleSmall,
                        color = MaterialTheme.colorScheme.onSurface,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1
                    )

                }

                Spacer(modifier = Modifier.height(2.dp))

                Text(
                    text = activity.packageName,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = .8f),
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1
                )

            }

            Spacer(modifier = Modifier.width(8.dp))

            IconButton(
                onClick = remember { { /* TODO */ } }
            ) {

                Icon(
                    imageVector = Icons.Rounded.MoreVert,
                    contentDescription = null
                )

            }

        }

        if (index == lastIndex && activitiesCount > 10) {

            val remaining by rememberUpdatedState(activitiesCount - 10)

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp, bottom = 24.dp),
                contentAlignment = Alignment.Center
            ) {

                OutlinedButton(onClick = remember { { /* TODO */ } }) {
                    Text(text = "View all ($remaining more)")
                }

            }

        }

    }

}