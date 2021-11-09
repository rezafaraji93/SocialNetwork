package com.faraji.socialnetwork.feature_profile.data.repository

import android.net.Uri
import androidx.core.net.toFile
import com.faraji.socialnetwork.R
import com.faraji.socialnetwork.core.util.Resource
import com.faraji.socialnetwork.core.util.SimpleResource
import com.faraji.socialnetwork.core.util.UiText
import com.faraji.socialnetwork.feature_profile.data.remote.ProfileApi
import com.faraji.socialnetwork.feature_profile.domain.model.Profile
import com.faraji.socialnetwork.feature_profile.domain.model.Skill
import com.faraji.socialnetwork.feature_profile.domain.model.UpdateProfileData
import com.faraji.socialnetwork.feature_profile.domain.repository.ProfileRepository
import com.google.gson.Gson
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okio.IOException
import retrofit2.HttpException

class ProfileRepositoryImpl(
    private val api: ProfileApi,
    private val gson: Gson
) : ProfileRepository {
    override suspend fun getProfile(userId: String): Resource<Profile> {
        return try {
            val response = api.getProfile(userId)
            if (response.successful) {
                Resource.Success(response.data?.toProfile())
            } else {
                response.message?.let { msg ->
                    Resource.Error(UiText.DynamicString(msg))
                } ?: Resource.Error(UiText.unknownError())

            }
        } catch (e: IOException) {
            Resource.Error(
                uiText = UiText.StringResource(R.string.error_couldnt_reach_server)
            )
        } catch (e: HttpException) {
            Resource.Error(
                uiText = UiText.StringResource(R.string.oops_something_went_wrong)
            )
        }
    }

    override suspend fun getSkills(): Resource<List<Skill>> {
        return try {
            val response = api.getSkills()
            Resource.Success(
                data = response.map { it.toSkill() }
            )
        } catch (e: IOException) {
            Resource.Error(
                uiText = UiText.StringResource(R.string.error_couldnt_reach_server)
            )
        } catch (e: HttpException) {
            Resource.Error(
                uiText = UiText.StringResource(R.string.oops_something_went_wrong)
            )
        }
    }

    override suspend fun updateProfile(
        updateProfileData: UpdateProfileData,
        bannerImageUri: Uri?,
        profilePictureUri: Uri?
    ): SimpleResource {
        val bannerImageFile = bannerImageUri?.toFile()
        val profilePictureFile = profilePictureUri?.toFile()
        return try {
            val response = api.updateProfile(
                bannerImage = bannerImageFile?.let {
                    MultipartBody.Part
                        .createFormData(
                            "banner_image",
                            it.name,
                            it.asRequestBody()
                        )
                },
                profilePicture = profilePictureFile?.let {
                    MultipartBody.Part
                        .createFormData(
                            "profile_picture",
                            it.name,
                            it.asRequestBody()
                        )
                },
                updateProfileData = MultipartBody.Part
                    .createFormData(
                        "update_profile_data",
                        gson.toJson(updateProfileData)
                    )
            )
            if (response.successful) {
                Resource.Success(Unit)
            } else {
                response.message?.let { msg ->
                    Resource.Error(UiText.DynamicString(msg))
                } ?: Resource.Error(UiText.unknownError())

            }
        } catch (e: IOException) {
            Resource.Error(
                uiText = UiText.StringResource(R.string.error_couldnt_reach_server)
            )
        } catch (e: HttpException) {
            Resource.Error(
                uiText = UiText.StringResource(R.string.oops_something_went_wrong)
            )
        }
    }
}