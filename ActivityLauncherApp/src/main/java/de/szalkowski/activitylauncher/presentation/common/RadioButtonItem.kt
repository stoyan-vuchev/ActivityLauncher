package de.szalkowski.activitylauncher.presentation.common

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp

@Composable
fun RadioButtonItem(
    modifier: Modifier = Modifier,
    selected: Boolean,
    onClick: (() -> Unit)?,
    label: String
) = Row(
    modifier = Modifier
        .clickable(
            role = Role.RadioButton,
            onClick = { onClick?.let { it() } }
        )
        .padding(horizontal = 16.dp, vertical = 8.dp)
        .then(modifier.fillMaxWidth()),
    verticalAlignment = Alignment.CenterVertically
) {

    RadioButton(
        selected = selected,
        onClick = onClick
    )

    Spacer(modifier = Modifier.width(16.dp))

    Text(
        text = label,
        style = MaterialTheme.typography.bodyLarge,
        color = MaterialTheme.colorScheme.onSurface
    )

}