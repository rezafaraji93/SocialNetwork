package com.faraji.socialnetwork.feature_profile.domain.repository

import com.faraji.socialnetwork.core.util.Resource
import com.faraji.socialnetwork.feature_profile.domain.model.Profile

interface ProfileRepository {

    suspend fun getProfile(userId: String): Resource<Profile>
}