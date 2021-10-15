package com.faraji.socialnetwork.feature_auth.domain.models

import com.faraji.socialnetwork.core.util.SimpleResource
import com.faraji.socialnetwork.feature_auth.presentation.util.AuthError

data class RegisterResult(
    val usernameError: AuthError? = null,
    val emailError: AuthError? = null,
    val passwordError: AuthError? = null,
    val result: SimpleResource? = null
)