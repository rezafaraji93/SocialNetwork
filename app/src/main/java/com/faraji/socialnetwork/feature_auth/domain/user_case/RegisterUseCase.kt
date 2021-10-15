package com.faraji.socialnetwork.feature_auth.domain.user_case

import com.faraji.socialnetwork.core.domain.util.ValidationUtil
import com.faraji.socialnetwork.feature_auth.data.repository.AuthRepository
import com.faraji.socialnetwork.feature_auth.domain.models.RegisterResult
import javax.inject.Inject

class RegisterUseCase @Inject constructor(
    private val repository: AuthRepository
) {

    suspend operator fun invoke(
        email: String,
        username: String,
        password: String
    ): RegisterResult {

        val emailError = ValidationUtil.validateEmail(email)
        val usernameError = ValidationUtil.validateUsername(username)
        val passwordError = ValidationUtil.validatePassword(password)

        if (emailError != null || usernameError != null || passwordError != null) {
            return RegisterResult(
                emailError = emailError,
                usernameError = usernameError,
                passwordError = passwordError
            )
        }

        val result = repository.register(email, username, password)

        return RegisterResult(
            result = result
        )

    }
}