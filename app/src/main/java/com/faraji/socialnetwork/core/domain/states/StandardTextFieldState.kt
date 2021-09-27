package com.faraji.socialnetwork.core.domain.states

import com.faraji.socialnetwork.core.util.Error

data class StandardTextFieldState(
    val text: String = "",
    val error: Error? = null
)
