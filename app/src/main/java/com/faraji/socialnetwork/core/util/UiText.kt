package com.faraji.socialnetwork.core.util

import androidx.annotation.StringRes
import com.faraji.socialnetwork.R

sealed class UiText {
    data class DynamicString(val value: String) : UiText()
    data class StringResource(@StringRes val id: Int) : UiText()

    companion object {
        fun unknownError(): UiText {
            return StringResource(R.string.unkown_error)
        }
    }
}
