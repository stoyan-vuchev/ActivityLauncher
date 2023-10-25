package de.szalkowski.activitylauncher.presentation.screens.settings

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import de.szalkowski.activitylauncher.BuildConfig
import de.szalkowski.activitylauncher.R
import de.szalkowski.activitylauncher.presentation.ext.LocalColorScheme
import de.szalkowski.activitylauncher.presentation.ext.LocalThemeMode
import de.szalkowski.activitylauncher.presentation.ext.label
import de.szalkowski.activitylauncher.presentation.screens.settings.SettingsScreenUiAction.*
import de.szalkowski.activitylauncher.presentation.screens.settings.dialog.SettingsDialogType
import de.szalkowski.activitylauncher.presentation.screens.settings.settings_item.SettingsItem
import de.szalkowski.activitylauncher.utils.LanguageUtils.currentAppLanguage
import de.szalkowski.activitylauncher.utils.SdkUtils

@Composable
internal fun SettingsCategoryToggleableItem(
    title: String,
    description: String,
    checked: Boolean,
    onCheckedChange: ((Boolean) -> Unit)?
) = Row(
    modifier = Modifier
        .fillMaxWidth()
        .clickable(
            role = Role.Switch,
            onClick = {
                if (onCheckedChange != null) {
                    onCheckedChange(!checked)
                }
            }
        )
        .padding(horizontal = 24.dp, vertical = 16.dp),
    verticalAlignment = Alignment.CenterVertically
) {

    Column(
        modifier = Modifier.weight(1f)
    ) {

        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onSurface
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = description,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = .8f)
        )

    }

    Spacer(modifier = Modifier.width(24.dp))

    Switch(
        checked = checked,
        onCheckedChange = onCheckedChange
    )

}

@Composable
internal fun SettingsCategoryClickableItem(
    title: String,
    description: String,
    onClick: () -> Unit
) = Column(
    modifier = Modifier
        .fillMaxWidth()
        .clickable(onClick = onClick)
        .padding(horizontal = 24.dp, vertical = 16.dp)
) {

    Text(
        text = title,
        style = MaterialTheme.typography.titleMedium,
        color = MaterialTheme.colorScheme.onSurface
    )

    Spacer(modifier = Modifier.height(8.dp))

    Text(
        text = description,
        style = MaterialTheme.typography.bodyMedium,
        color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = .8f)
    )

}

@SuppressLint("UnrememberedMutableState")
@Composable
internal fun rememberSettingsScreenItemsList(
    screenState: SettingsScreenState,
    onUiAction: (SettingsScreenUiAction) -> Unit
): State<SnapshotStateList<SettingsItem>> {
    return rememberUpdatedState(
        mutableStateListOf(
            SettingsItem(
                category = SettingsCategory.Advanced,
                content = {

                    SettingsCategoryToggleableItem(
                        title = stringResource(R.string.settings_hide_private),
                        description = stringResource(R.string.settings_summary_hide_private),
                        checked = screenState.arePrivateActivitiesHidden,
                        onCheckedChange = remember {
                            { onUiAction(SetHidePrivateActivities(it)) }
                        }
                    )

                }
            ),
            SettingsItem(
                category = SettingsCategory.Advanced,
                content = {

                    SettingsCategoryToggleableItem(
                        title = stringResource(R.string.settings_allow_root),
                        description = stringResource(R.string.settings_summary_allow_root),
                        checked = screenState.areRootPrivilegesEnabled,
                        onCheckedChange = remember {
                            { onUiAction(SetPrivilegedMode(it)) }
                        }
                    )

                }
            ),
            SettingsItem(
                category = SettingsCategory.Personalization,
                content = {

                    SettingsCategoryClickableItem(
                        title = stringResource(id = R.string.settings_theme),
                        description = stringResource(id = LocalThemeMode.current.label),
                        onClick = remember {
                            { onUiAction(SetDialogType(SettingsDialogType.ThemeMode)) }
                        }
                    )

                }
            ),
            SettingsItem(
                category = SettingsCategory.Personalization,
                content = {

                    SettingsCategoryClickableItem(
                        title = stringResource(id = R.string.color_scheme),
                        description = stringResource(id = LocalColorScheme.current.label),
                        onClick = remember {
                            { onUiAction(SetDialogType(SettingsDialogType.ColorScheme)) }
                        }
                    )

                }
            ),
            SettingsItem(
                category = SettingsCategory.Personalization,
                content = {

                    if (SdkUtils.supportsPerAppLanguages) {

                        SettingsCategoryClickableItem(
                            title = stringResource(id = R.string.settings_language),
                            description = LocalContext.current.currentAppLanguage(),
                            onClick = remember {
                                { onUiAction(ChangeLanguageFromSystemSettings) }
                            }
                        )

                    }

                }
            ),
            SettingsItem(
                category = SettingsCategory.About,
                content = {

                    val url = stringResource(id = R.string.url_source)

                    SettingsCategoryClickableItem(
                        title = stringResource(id = R.string.action_view_source),
                        description = stringResource(id = R.string.summary_action_view_source),
                        onClick = remember { { onUiAction(OpenUrl(url)) } }
                    )

                }
            ),
            SettingsItem(
                category = SettingsCategory.About,
                content = {

                    val url = stringResource(id = R.string.url_translation)

                    SettingsCategoryClickableItem(
                        title = stringResource(id = R.string.action_view_translation),
                        description = stringResource(id = R.string.summary_action_view_translation),
                        onClick = remember { { onUiAction(OpenUrl(url)) } }
                    )

                }
            ),
            SettingsItem(
                category = SettingsCategory.About,
                content = {

                    val url = stringResource(id = R.string.url_bugs)

                    SettingsCategoryClickableItem(
                        title = stringResource(id = R.string.action_view_bugs),
                        description = stringResource(id = R.string.summary_action_view_bugs),
                        onClick = remember { { onUiAction(OpenUrl(url)) } }
                    )

                }
            ),
            SettingsItem(
                category = SettingsCategory.About,
                content = {

                    SettingsCategoryClickableItem(
                        title = stringResource(id = R.string.app_name),
                        description = BuildConfig.VERSION_NAME,
                        onClick = remember { { /* Do nothing for now. */ } }
                    )

                }
            ),
        )
    )
}