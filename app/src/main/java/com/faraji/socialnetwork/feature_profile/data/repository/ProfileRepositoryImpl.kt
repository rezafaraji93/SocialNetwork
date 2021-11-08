package com.faraji.socialnetwork.feature_profile.data.repository

import com.faraji.socialnetwork.R
import com.faraji.socialnetwork.core.util.Resource
import com.faraji.socialnetwork.core.util.UiText
import com.faraji.socialnetwork.feature_profile.data.remote.ProfileApi
import com.faraji.socialnetwork.feature_profile.domain.model.Profile
import com.faraji.socialnetwork.feature_profile.domain.model.Skill
import com.faraji.socialnetwork.feature_profile.domain.repository.ProfileRepository
import okio.IOException
import retrofit2.HttpException

class ProfileRepositoryImpl(
    private val api: ProfileApi
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
}