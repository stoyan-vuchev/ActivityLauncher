package de.szalkowski.activitylauncher.presentation

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import de.szalkowski.activitylauncher.R

@Composable
fun DisclaimerDialog(
    visible: Boolean,
    onNegativeBtn: () -> Unit,
    onPositiveBtn: () -> Unit
) {

    if (visible) {

        AlertDialog(
            onDismissRequest = onNegativeBtn,
            confirmButton = {

                Button(
                    onClick = onPositiveBtn,
                    content = {

                        Text(
                            text = stringResource(
                                id = R.string.dialog_disclaimer_positive_btn_text
                            )
                        )

                    }
                )

            },
            dismissButton = {

                OutlinedButton(
                    modifier = Modifier.padding(end = 4.dp),
                    onClick = onNegativeBtn,
                    content = {
                        Text(
                            text = stringResource(
                                id = R.string.dialog_disclaimer_negative_btn_text
                            )
                        )
                    }
                )

            },
            title = { Text(text = stringResource(id = R.string.title_dialog_disclaimer)) },
            text = { Text(text = stringResource(id = R.string.dialog_disclaimer)) },
            icon = {

                Icon(
                    modifier = Modifier.size(32.dp),
                    imageVector = Icons.Outlined.Info,
                    contentDescription = null
                )

            },
            properties = DialogProperties(dismissOnClickOutside = false)
        )

    }

}