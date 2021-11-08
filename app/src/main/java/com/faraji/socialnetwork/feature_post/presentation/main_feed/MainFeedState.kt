package com.faraji.socialnetwork.feature_post.presentation.main_feed

data class MainFeedState(
    val isLoadingFirstTime: Boolean = true,
    val isLoadingNewPosts: Boolean = false,
    val page: Int = 0
)
