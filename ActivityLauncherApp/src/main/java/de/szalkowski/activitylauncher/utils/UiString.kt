package de.szalkowski.activitylauncher.utils

import android.content.Context
import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource

sealed interface UiString {

    data class DynamicString(val value: String) : UiString
    class StringResource(
        @StringRes val resId: Int,
        vararg val formatArgs: Any
    ) : UiString

    @Composable
    fun asString() = when (this) {
        is DynamicString -> this.value
        is StringResource -> stringResource(this.resId, this.formatArgs)
    }

    fun asString(context: Context) = when (this) {
        is DynamicString -> this.value
        is StringResource -> context.resources.getString(this.resId, this.formatArgs)
    }

    companion object {
        val EmptyString get() = DynamicString("")
    }

}