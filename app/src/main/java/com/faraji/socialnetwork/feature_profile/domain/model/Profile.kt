package com.faraji.socialnetwork.feature_profile.domain.model

data class Profile(
    val userId: String,
    val username: String,
    val profilePictureUrl: String,
    val bannerUrl: String,
    val topSkills: List<Skill>,
    val githubUrl: String?,
    val instagramUrl: String?,
    val linkedinUrl: String?,
    val bio: String,
    val followerCount: Int,
    val followingCount: Int,
    val postCount: Int,
    val isOwnProfile: Boolean,
    val isFollowing: Boolean
)
