package com.faraji.socialnetwork.core.presentation.util

import com.faraji.socialnetwork.core.util.UiText

sealed class UiEvent {
    data class ShowSnackbar(val uiText: UiText) : UiEvent()
    data class Navigate(val route: String) : UiEvent()
    object NavigateUp : UiEvent()
}