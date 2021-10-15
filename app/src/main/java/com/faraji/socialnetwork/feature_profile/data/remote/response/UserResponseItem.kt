package com.faraji.socialnetwork.feature_profile.data.remote.response

import com.faraji.socialnetwork.feature_profile.domain.model.UserItem

data class UserResponseItem(
    val userId: String,
    val userName: String,
    val profilePictureUrl: String,
    val bio: String,
    val isFollowing: Boolean
) {
    fun toUserItem(): UserItem {
        return UserItem(
            userId, userName, profilePictureUrl, bio, isFollowing
        )
    }
}
