package com.faraji.socialnetwork.feature_post.domain.repository

import android.net.Uri
import androidx.paging.PagingData
import com.faraji.socialnetwork.core.domain.models.Post
import com.faraji.socialnetwork.core.util.SimpleResource
import kotlinx.coroutines.flow.Flow

interface PostRepository {

    val posts: Flow<PagingData<Post>>

    suspend fun createPost(
        description: String,
        imageUri: Uri
    ): SimpleResource
}