package com.faraji.socialnetwork.feature_profile.domain.repository

import android.net.Uri
import com.faraji.socialnetwork.core.util.Resource
import com.faraji.socialnetwork.core.util.SimpleResource
import com.faraji.socialnetwork.feature_profile.domain.model.Profile
import com.faraji.socialnetwork.feature_profile.domain.model.Skill
import com.faraji.socialnetwork.feature_profile.domain.model.UpdateProfileData

interface ProfileRepository {

    suspend fun getProfile(userId: String): Resource<Profile>

    suspend fun getSkills(): Resource<List<Skill>>

    suspend fun updateProfile(
        updateProfileData: UpdateProfileData,
        bannerImageUri: Uri?,
        profilePictureUri: Uri?
    ): SimpleResource
}