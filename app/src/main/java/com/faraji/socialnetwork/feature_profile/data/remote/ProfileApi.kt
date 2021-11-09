package com.faraji.socialnetwork.feature_profile.data.remote

import com.faraji.socialnetwork.core.data.dto.response.BasicApiResponse
import com.faraji.socialnetwork.feature_profile.data.remote.response.ProfileResponse
import com.faraji.socialnetwork.feature_profile.data.remote.response.SkillDto
import com.faraji.socialnetwork.feature_profile.domain.model.Skill
import okhttp3.MultipartBody
import retrofit2.http.*

interface ProfileApi {

    @GET("api/user/profile")
    suspend fun getProfile(
        @Query("userId") userId: String
    ): BasicApiResponse<ProfileResponse>

    @GET("api/skills/get")
    suspend fun getSkills(): List<SkillDto>

    @Multipart
    @PUT("api/user/update")
    suspend fun updateProfile(
        @Part bannerImage: MultipartBody.Part?,
        @Part profilePicture: MultipartBody.Part?,
        @Part updateProfileData: MultipartBody.Part
    ): BasicApiResponse<Unit>

    companion object {
        const val BASE_URL = "http://10.0.2.2:8001/"
    }
}