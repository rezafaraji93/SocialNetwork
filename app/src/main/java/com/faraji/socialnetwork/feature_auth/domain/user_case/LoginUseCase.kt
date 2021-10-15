package com.faraji.socialnetwork.feature_auth.domain.user_case

import com.faraji.socialnetwork.feature_auth.data.repository.AuthRepository
import com.faraji.socialnetwork.feature_auth.domain.models.LoginResult
import com.faraji.socialnetwork.feature_auth.presentation.util.AuthError

class LoginUseCase(
    private val repository: AuthRepository
) {

    suspend operator fun invoke(email: String, password: String): LoginResult {
        val emailError = if (email.isBlank()) AuthError.FieldEmpty else null
        val passwordError = if (password.isBlank()) AuthError.FieldEmpty else null

        if (emailError != null || passwordError != null) {
            return LoginResult(emailError, passwordError)
        }
        val result = repository.login(email, password)
        return LoginResult(
            result = result
        )
    }
}