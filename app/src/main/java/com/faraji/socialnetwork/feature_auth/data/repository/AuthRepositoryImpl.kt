package com.faraji.socialnetwork.feature_auth.data.repository

import com.faraji.socialnetwork.R
import com.faraji.socialnetwork.core.util.Resource
import com.faraji.socialnetwork.core.util.SimpleResource
import com.faraji.socialnetwork.core.util.UiText
import com.faraji.socialnetwork.feature_auth.data.data_source.remote.AuthApi
import com.faraji.socialnetwork.feature_auth.data.data_source.remote.request.CreateAccountRequest
import okio.IOException
import retrofit2.HttpException

class AuthRepositoryImpl(
    private val api: AuthApi
) : AuthRepository {

    override suspend fun register(
        email: String,
        username: String,
        password: String
    ): SimpleResource {
        val request = CreateAccountRequest(email, username, password)
        return try {
            val response = api.register(request)
            if (response.successful) {
                Resource.Success(Unit)
            } else {
                response.message?.let { msg ->
                    Resource.Error(UiText.DynamicString(msg))
                } ?: Resource.Error(UiText.StringResource(R.string.unkown_error))
            }
        } catch (e: IOException) {
            Resource.Error(
                message = UiText.StringResource(R.string.error_couldnt_reach_server)
            )
        } catch (e: HttpException) {
            Resource.Error(
                message = UiText.StringResource(R.string.oops_something_went_wrong)
            )
        }
    }
}