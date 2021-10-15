package com.faraji.socialnetwork.feature_auth.domain.user_case

import com.faraji.socialnetwork.core.util.SimpleResource
import com.faraji.socialnetwork.feature_auth.data.repository.AuthRepository

class AuthenticateUseCase(
    private val repository: AuthRepository
) {

    suspend operator fun invoke(): SimpleResource {
        return repository.authenticate()
    }
}