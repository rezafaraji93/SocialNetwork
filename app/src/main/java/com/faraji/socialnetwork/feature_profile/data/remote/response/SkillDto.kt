package com.faraji.socialnetwork.feature_profile.data.remote.response

import com.faraji.socialnetwork.feature_profile.domain.model.Skill
import com.google.gson.annotations.SerializedName

data class SkillDto(
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
