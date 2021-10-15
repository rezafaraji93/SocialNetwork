package com.faraji.socialnetwork.feature_profile.domain.model

data class UserItem(
    val userId: String,
    val userName: String,
    val profilePictureUrl: String,
    val bio: String,
    val isFollowing: Boolean
)
