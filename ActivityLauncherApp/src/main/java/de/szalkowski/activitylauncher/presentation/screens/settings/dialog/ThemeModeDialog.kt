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
import de.szalkowski.activitylauncher.domain.preferences.theme_mode.ThemeMode
import de.szalkowski.activitylauncher.presentation.common.PreferencesDialog
import de.szalkowski.activitylauncher.presentation.common.RadioButtonItem

@Composable
fun ThemeModeDialog(
    initialThemeMode: ThemeMode,
    onDismissRequest: () -> Unit,
    onSetThemeMode: (ThemeMode) -> Unit
) {

    var themeMode by remember { mutableStateOf(initialThemeMode) }

    PreferencesDialog(
        onDismissRequest = onDismissRequest,
        title = stringResource(id = R.string.settings_theme),
        positiveButton = {

            Button(
                onClick = { onDismissRequest(); onSetThemeMode(themeMode) },
                content = { Text(text = stringResource(id = R.string.dialog_apply)) }
            )

        }
    ) {

        Spacer(modifier = Modifier.height(16.dp))

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            RadioButtonItem(
                selected = themeMode == ThemeMode.SYSTEM,
                onClick = remember { { themeMode = ThemeMode.SYSTEM } },
                label = stringResource(id = R.string.theme_default)
            )
        }

        RadioButtonItem(
            selected = themeMode == ThemeMode.DARK,
            onClick = remember { { themeMode = ThemeMode.DARK } },
            label = stringResource(id = R.string.theme_dark)
        )

        RadioButtonItem(
            selected = themeMode == ThemeMode.LIGHT,
            onClick = remember { { themeMode = ThemeMode.LIGHT } },
            label = stringResource(id = R.string.theme_light)
        )

        Spacer(modifier = Modifier.height(16.dp))

    }

}