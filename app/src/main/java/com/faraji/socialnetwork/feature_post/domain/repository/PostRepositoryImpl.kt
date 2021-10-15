package com.faraji.socialnetwork.feature_post.domain.repository

import android.net.Uri
import androidx.core.net.toFile
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.faraji.socialnetwork.R
import com.faraji.socialnetwork.core.domain.models.Post
import com.faraji.socialnetwork.core.util.Constants
import com.faraji.socialnetwork.core.util.Resource
import com.faraji.socialnetwork.core.util.SimpleResource
import com.faraji.socialnetwork.core.util.UiText
import com.faraji.socialnetwork.feature_post.data.remote.PostApi
import com.faraji.socialnetwork.feature_post.data.remote.request.CreatePostRequest
import com.faraji.socialnetwork.feature_post.data.remote.paging.PostSource
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okio.IOException
import retrofit2.HttpException

class PostRepositoryImpl(
    private val api: PostApi,
    private val gson: Gson
) : PostRepository {

    override val posts: Flow<PagingData<Post>>
        get() = Pager(PagingConfig(Constants.PAGE_SIZE_POSTS)) {
            PostSource(api)
        }.flow

    override suspend fun createPost(
        description: String,
        imageUri: Uri
    ): SimpleResource {
        val request = CreatePostRequest(description)
        val file = imageUri.toFile()
        return try {
            val response = api.createPost(
                postData = MultipartBody.Part
                    .createFormData(
                        "post_data",
                        gson.toJson(request)
                    ),
                postImage = MultipartBody.Part
                    .createFormData(
                        name = "post_image",
                        filename = file.name,
                        body = file.asRequestBody()
                    )
            )
            if (response.successful) {
                Resource.Success(Unit)
            } else {
                response.message?.let { msg ->
                    Resource.Error(UiText.DynamicString(msg))
                } ?: Resource.Error(UiText.StringResource(R.string.unkown_error))
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