package com.faraji.socialnetwork.feature_post.presentation.main_feed

sealed class MainFeedEvent {

    object LoadMorePosts: MainFeedEvent()
    object LoadedPage: MainFeedEvent()
}
