package de.szalkowski.activitylauncher.presentation.screens.settings.dialog

import android.os.Build
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import de.szalkowski.activitylauncher.R
import de.szalkowski.activitylauncher.domain.color_scheme.ColorScheme
import de.szalkowski.activitylauncher.presentation.common.PreferencesDialog
import de.szalkowski.activitylauncher.presentation.common.RadioButtonItem

@Composable
fun ColorSchemeDialog(
    initialColorScheme: ColorScheme,
    onDismissRequest: () -> Unit,
    onSetColorScheme: (ColorScheme) -> Unit
) {

    var colorScheme by remember { mutableStateOf(initialColorScheme) }

    PreferencesDialog(
        onDismissRequest = onDismissRequest,
        title = stringResource(id = R.string.color_scheme),
        positiveButton = {

            Button(
                onClick = { onDismissRequest(); onSetColorScheme(colorScheme) },
                content = { Text(text = stringResource(id = R.string.dialog_apply)) }
            )

        }
    ) {

        Spacer(modifier = Modifier.height(16.dp))

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            RadioButtonItem(
                selected = colorScheme == ColorScheme.DYNAMIC,
                onClick = remember { { colorScheme = ColorScheme.DYNAMIC } },
                label = stringResource(id = R.string.color_scheme_dynamic)
            )
        }

        RadioButtonItem(
            selected = colorScheme == ColorScheme.APP_DEFAULT,
            onClick = remember { { colorScheme = ColorScheme.APP_DEFAULT } },
            label = stringResource(id = R.string.color_scheme_app_default)
        )

        RadioButtonItem(
            selected = colorScheme == ColorScheme.M3_LAVENDER,
            onClick = remember { { colorScheme = ColorScheme.M3_LAVENDER } },
            label = stringResource(id = R.string.color_scheme_m3_lavender)
        )

        Spacer(modifier = Modifier.height(16.dp))

    }

}