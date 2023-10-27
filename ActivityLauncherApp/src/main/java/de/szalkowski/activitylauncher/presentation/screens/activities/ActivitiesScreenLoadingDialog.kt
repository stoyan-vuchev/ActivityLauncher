package de.szalkowski.activitylauncher.presentation.screens.activities

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import de.szalkowski.activitylauncher.R

@Composable
fun ActivitiesScreenLoadingDialog(
    visible: Boolean,
    totalApps: Int,
    loadedApps: Int
) {

    if (visible) {

        val progressFraction by remember(totalApps, loadedApps) {
            derivedStateOf { (loadedApps.toDouble() / totalApps.toDouble()).toFloat() }
        }

        val progressInteger by remember(progressFraction) {
            derivedStateOf { (progressFraction * 100).toInt() }
        }

        Dialog(
            onDismissRequest = { /* Do nothing. */ },
            properties = DialogProperties(
                dismissOnBackPress = false,
                dismissOnClickOutside = false
            )
        ) {

            Column(
                modifier = Modifier
                    .widthIn(min = 320.dp, max = 480.dp)
                    .clip(shape = MaterialTheme.shapes.extraLarge)
                    .background(color = MaterialTheme.colorScheme.surfaceColorAtElevation(6.dp))
                    .padding(all = 24.dp)
            ) {

                Text(
                    text = stringResource(id = R.string.dialog_progress_loading),
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onSurface
                )

                Spacer(modifier = Modifier.height(24.dp))

                LinearProgressIndicator(
                    modifier = Modifier.fillMaxWidth(),
                    progress = progressFraction
                )

                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Text(
                        text = "$progressInteger%",
                        style = MaterialTheme.typography.labelLarge,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )

                    Text(
                        text = "$loadedApps / $totalApps",
                        style = MaterialTheme.typography.labelLarge,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )

                }

            }

        }

    }

}