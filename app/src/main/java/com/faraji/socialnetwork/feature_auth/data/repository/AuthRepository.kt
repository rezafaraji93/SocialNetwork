package com.faraji.socialnetwork.feature_auth.data.repository

import com.faraji.socialnetwork.core.util.SimpleResource

interface AuthRepository {

    suspend fun register(
        email: String,
        username: String,
        password: String
    ): SimpleResource

}