package com.faraji.socialnetwork.feature_profile.data.remote.response

import com.faraji.socialnetwork.feature_profile.domain.model.Skill

data class SkillDto(
    val id: String,
    val name: String,
    val imageUrl: String
) {
    fun toSkill(): Skill {
        return Skill(
            name = name,
            imageUrl = imageUrl
        )
    }
}
