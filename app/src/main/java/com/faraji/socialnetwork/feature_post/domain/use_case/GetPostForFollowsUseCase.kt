package com.faraji.socialnetwork.feature_post.domain.use_case

import androidx.paging.PagingData
import com.faraji.socialnetwork.core.domain.models.Post
import com.faraji.socialnetwork.feature_post.domain.repository.PostRepository
import kotlinx.coroutines.flow.Flow

class GetPostForFollowsUseCase(
    private val repository: PostRepository
) {

    operator fun invoke(): Flow<PagingData<Post>> {
        return repository.posts
    }
}