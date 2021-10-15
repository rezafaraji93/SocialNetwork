package com.faraji.socialnetwork.feature_post.domain.use_case

data class PostUseCases(
    val getPostForFollowsUseCase: GetPostForFollowsUseCase,
    val createPostUseCase: CreatePostUseCase
)