package com.faraji.socialnetwork.feature_auth.data.data_source.remote

import com.faraji.socialnetwork.core.data.dto.response.BasicApiResponse
import com.faraji.socialnetwork.feature_auth.data.data_source.remote.request.CreateAccountRequest
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {

    @POST("api/user/create")
    suspend fun register(
        @Body request: CreateAccountRequest
    ): BasicApiResponse

    companion object {
        const val BASE_URL = "http://192.168.1.100:8001/"
    }
}