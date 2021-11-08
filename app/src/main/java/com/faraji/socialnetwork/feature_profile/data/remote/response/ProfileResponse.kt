package com.faraji.socialnetwork.feature_profile.data.remote.response

import com.faraji.socialnetwork.feature_profile.domain.model.Profile

data class ProfileResponse(
    val userId: String,
    val username: String,
    val profilePictureUrl: String,
    val bannerUrl: String,
    val topSkills: List<SkillDto>,
    val githubUrl: String?,
    val instagramUrl: String?,
    val linkedinUrl: String?,
    val bio: String,
    val followerCount: Int,
    val followingCount: Int,
    val postCount: Int,
    val isOwnProfile: Boolean,
    val isFollowing: Boolean
) {
    fun toProfile(): Profile {
        return Profile(
            userId = userId,
            username = username,
            bio = bio,
            profilePictureUrl = profilePictureUrl,
            bannerUrl = bannerUrl,
            topSkills = topSkills.map { it.toSkill() },
            githubUrl = githubUrl,
            instagramUrl = instagramUrl,
            linkedinUrl = linkedinUrl,
            followerCount = followerCount,
            followingCount = followingCount,
            postCount = postCount,
            isOwnProfile = isOwnProfile,
            isFollowing = isFollowing
        )
    }
}