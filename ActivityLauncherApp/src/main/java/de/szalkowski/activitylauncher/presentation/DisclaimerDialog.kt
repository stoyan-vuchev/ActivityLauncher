package de.szalkowski.activitylauncher.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
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
import androidx.compose.ui.window.DialogProperties
import de.szalkowski.activitylauncher.R

@Composable
fun DisclaimerDialog(
    visible: Boolean,
    onNegativeBtn: () -> Unit,
    onPositiveBtn: () -> Unit
) {

    if (visible) {

        Dialog(
            onDismissRequest = onNegativeBtn,
            properties = DialogProperties(dismissOnClickOutside = false)
        ) {

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(MaterialTheme.shapes.extraLarge)
                    .background(MaterialTheme.colorScheme.surfaceColorAtElevation(6.dp))
                    .padding(20.dp)
            ) {

                Text(
                    modifier = Modifier.padding(horizontal = 8.dp),
                    text = stringResource(id = R.string.title_dialog_disclaimer),
                    style = MaterialTheme.typography.headlineLarge
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    modifier = Modifier.padding(horizontal = 8.dp),
                    text = stringResource(id = R.string.dialog_disclaimer)
                )

                Spacer(modifier = Modifier.height(24.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.End
                ) {

                    OutlinedButton(onClick = onNegativeBtn) {
                        Text(text = stringResource(id = R.string.dialog_disclaimer_negative_btn_text))
                    }

                    Spacer(modifier = Modifier.width(12.dp))

                    Button(onClick = onPositiveBtn) {
                        Text(text = stringResource(id = R.string.dialog_disclaimer_positive_btn_text))
                    }

                }

            }

        }

    }

}