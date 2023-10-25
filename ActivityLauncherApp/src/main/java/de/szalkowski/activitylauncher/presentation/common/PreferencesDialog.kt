package de.szalkowski.activitylauncher.presentation.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import de.szalkowski.activitylauncher.R

@Composable
fun PreferencesDialog(
    useIntrinsicsMeasurement: Boolean = true,
    onDismissRequest: () -> Unit,
    title: String,
    positiveButton: @Composable () -> Unit,
    content: @Composable ColumnScope.() -> Unit
) = Dialog(
    onDismissRequest = onDismissRequest,
    content = {

        Column(
            modifier = Modifier
                .padding(vertical = 32.dp)
                .fillMaxWidth()
                .then(
                    if (useIntrinsicsMeasurement) {
                        Modifier.height(IntrinsicSize.Min)
                    } else Modifier
                )
                .then(
                    Modifier
                        .clip(MaterialTheme.shapes.extraLarge)
                        .background(MaterialTheme.colorScheme.surfaceColorAtElevation(6.dp))
                        .padding(vertical = 24.dp)
                )
        ) {

            Text(
                modifier = Modifier.padding(horizontal = 32.dp),
                text = title,
                style = MaterialTheme.typography.headlineSmall
            )

            Spacer(modifier = Modifier.height(24.dp))

            Divider()

            content()

            Divider()

            Spacer(modifier = Modifier.height(24.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End
            ) {

                OutlinedButton(
                    onClick = onDismissRequest,
                    content = { Text(text = stringResource(id = R.string.dialog_cancel)) }
                )

                Spacer(modifier = Modifier.width(12.dp))

                positiveButton()

            }

        }

    }
)